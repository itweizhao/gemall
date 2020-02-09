package com.atguigu.gmall.pms.service.impl;

import com.atguigu.gmall.pms.dao.*;
import com.atguigu.gmall.pms.entity.*;
import com.atguigu.gmall.pms.feign.GmallSmsClient;
import com.atguigu.gmall.pms.service.ProductAttrValueService;
import com.atguigu.gmall.pms.service.SkuImagesService;
import com.atguigu.gmall.pms.service.SkuSaleAttrValueService;
import com.atguigu.gmall.pms.vo.BaseAttrValueVO;
import com.atguigu.gmall.pms.vo.SkuInfoVO;
import com.atguigu.gmall.pms.vo.SpuInfoVO;
import com.atguigu.gmall.sms.vo.SaleVO;
import io.seata.spring.annotation.GlobalTransactional;
import org.apache.commons.lang.StringUtils;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.core.bean.PageVo;
import com.atguigu.core.bean.Query;
import com.atguigu.core.bean.QueryCondition;

import com.atguigu.gmall.pms.service.SpuInfoService;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service("spuInfoService")
public class SpuInfoServiceImpl extends ServiceImpl<SpuInfoDao, SpuInfoEntity> implements SpuInfoService {


    @Autowired
    private GmallSmsClient smsClient;

    @Autowired
    private SpuInfoDescDao descDao;

    @Autowired
    private ProductAttrValueService attrValueService;

    @Autowired
    private SkuInfoDao skuInfoDao;

    @Autowired
    private SkuImagesService imagesService;

    @Autowired
    private SkuSaleAttrValueService saleAttrValueService;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    public PageVo queryPage(QueryCondition params) {
        IPage<SpuInfoEntity> page = this.page(
                new Query<SpuInfoEntity>().getPage(params),
                new QueryWrapper<SpuInfoEntity>()
        );

        return new PageVo(page);
    }

    @Override
    public PageVo querySpusByCatIdOrPage(QueryCondition queryCondition, long catId) {

        QueryWrapper<SpuInfoEntity> wrapper = new QueryWrapper<>();
        //判断查全站，差本类
        if (catId !=0){
            wrapper.eq("catalog_id",catId);
        }
        String key = queryCondition.getKey();
        if (StringUtils.isNotBlank(key)){
            wrapper.and(t ->t.eq("id",key)).or().like("spu_name",key);
        }
        IPage<SpuInfoEntity> page = this.page(
                new Query<SpuInfoEntity>().getPage(queryCondition),
                wrapper
        );

        return new PageVo(page);
    }

    @GlobalTransactional
    @Override
    public void allSave(SpuInfoVO spuInfoVO) {
        //1.保存spu相关信息
        //spuInfo
        spuInfoVO.setCreateTime(new Date());
        spuInfoVO.setUodateTime(spuInfoVO.getCreateTime());
        save(spuInfoVO);
        Long spuId = spuInfoVO.getId();
        //spuInfoDesc
        List<String> spuImages = spuInfoVO.getSpuImages();
        if (!CollectionUtils.isEmpty(spuImages)){
            SpuInfoDescEntity descEntity = new SpuInfoDescEntity();
            descEntity.setSpuId(spuId);
            descEntity.setDecript(StringUtils.join(spuImages,","));
            descDao.insert(descEntity);
        }
        //基础属性相关信息
        List<BaseAttrValueVO> baseAttrs = spuInfoVO.getBaseAttrs();
        if (!CollectionUtils.isEmpty(baseAttrs)){
            List<ProductAttrValueEntity> attrValues = baseAttrs.stream().map(baseAttrValueVO -> {
                ProductAttrValueEntity attrValueEntity = new ProductAttrValueEntity();
                attrValueEntity.setSpuId(spuId);
                attrValueEntity.setAttrSort(0);
                attrValueEntity.setQuickShow(0);
                attrValueEntity.setAttrName(baseAttrValueVO.getAttrName());
                attrValueEntity.setAttrValue(baseAttrValueVO.getAttrValue());
                return attrValueEntity;
            }).collect(Collectors.toList());
            attrValueService.saveBatch(attrValues);
        }
        //2.sku相关信息
        List<SkuInfoVO> skus = spuInfoVO.getSkus();
        if (!CollectionUtils.isEmpty(skus)){
            skus.forEach(sku -> {
                //skuInfo
                SkuInfoEntity skuInfoEntity = new SkuInfoEntity();
                skuInfoEntity.setSpuId(spuId);
                List<String> images = sku.getImages();
                if (!CollectionUtils.isEmpty(images)){
                    skuInfoEntity.setSkuDefaultImg(skuInfoEntity.getSkuDefaultImg()==null?images.get(0):skuInfoEntity.getSkuDefaultImg());
                }
                skuInfoEntity.setSkuCode(UUID.randomUUID().toString());
                skuInfoEntity.setCatalogId(spuInfoVO.getCatalogId());
                skuInfoEntity.setBrandId(spuInfoVO.getBrandId());
                skuInfoEntity.setPrice(sku.getPrice());
                skuInfoEntity.setSkuDesc(sku.getSkuDesc());
                skuInfoEntity.setSkuTitle(sku.getSkuTitle());
                skuInfoEntity.setSkuName(sku.getSkuName());
                skuInfoEntity.setSkuSubtitle(sku.getSkuSubtitle());
                skuInfoEntity.setWeight(sku.getWeight());
                skuInfoDao.insert(skuInfoEntity);
                Long skuId = skuInfoEntity.getSkuId();
                //skuInfoImages
                if (!CollectionUtils.isEmpty(images)){
                    List<SkuImagesEntity> skuImagesEntities = images.stream().map(image -> {
                        SkuImagesEntity imagesEntity = new SkuImagesEntity();
                        imagesEntity.setImgSort(0);
                        imagesEntity.setSkuId(skuId);
                        imagesEntity.setDefaultImg(StringUtils.equals(image, skuInfoEntity.getSkuDefaultImg())? 1 : 0);
                        imagesEntity.setImgUrl(image);
                        return imagesEntity;
                    }).collect(Collectors.toList());
                    imagesService.saveBatch(skuImagesEntities);
                }
                //skuSaleAttrValue
                List<SkuSaleAttrValueEntity> saleAttrs = sku.getSaleAttrs();
                if (!CollectionUtils.isEmpty(saleAttrs)){
                    saleAttrs.forEach(skuSaleAttrValueEntity -> {
                        skuSaleAttrValueEntity.setAttrSort(0);
                        skuSaleAttrValueEntity.setSkuId(skuId);
                    });
                    saleAttrValueService.saveBatch(saleAttrs);
                }
                //3.营销相关信息
                SaleVO saleVO = new SaleVO();
                sku.setSkuId(skuId);
                BeanUtils.copyProperties(sku,saleVO);
                //saleVO.setSkuId(skuId);
                smsClient.saveSales(saleVO);
            });

        }
        sendMsg(spuId,"insert");
    }

    private void sendMsg(Long spuId, String type){
        amqpTemplate.convertAndSend("GMALL-PMS-EXCHANGE","item."+type,spuId);
    }

}