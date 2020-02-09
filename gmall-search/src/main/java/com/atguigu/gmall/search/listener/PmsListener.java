package com.atguigu.gmall.search.listener;

import com.atguigu.core.bean.Resp;
import com.atguigu.gmall.pms.entity.*;
import com.atguigu.gmall.search.feign.GmallPmsClient;
import com.atguigu.gmall.search.feign.GmallWmsClient;
import com.atguigu.gmall.search.pojo.Goods;
import com.atguigu.gmall.search.pojo.SearchAttrValue;
import com.atguigu.gmall.search.repository.GoodsRepository;
import com.atguigu.gmall.wms.entity.WareSkuEntity;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author shkstart
 * @create 2020-02-08 19:39
 */
@Component
public class PmsListener {

    @Autowired
    private GmallPmsClient pmsClient;

    @Autowired
    private GmallWmsClient wmsClient;

    @Autowired
    private GoodsRepository goodsRepository;


    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "GMALL-SEARCH-QUEUE", durable = "true"),
            exchange = @Exchange(value = "GMALL-PMS-EXCHANGE", ignoreDeclarationExceptions = "true", type = ExchangeTypes.TOPIC),
            key = {"item.insert"}
    ))
    public void listener(Long spuId) {
        Resp<List<SkuInfoEntity>> resp = pmsClient.querySkusBySpuId(spuId);
        List<SkuInfoEntity> skuInfoEntities = resp.getData();
        if (!CollectionUtils.isEmpty(skuInfoEntities)) {
            List<Goods> goodsList = skuInfoEntities.stream().map(skuInfoEntity -> {
                Goods goods = new Goods();

                goods.setBrandId(skuInfoEntity.getBrandId());
                //查询品牌信息
                if (skuInfoEntity.getBrandId() != null) {
                    Resp<BrandEntity> brandEntityResp = pmsClient.queryBrandById(skuInfoEntity.getBrandId());
                    BrandEntity brandEntity = brandEntityResp.getData();
                    goods.setBrandName(brandEntity.getName());
                    if (brandEntity != null) {
                        goods.setCategoryId(skuInfoEntity.getCatalogId());
                    }
                }

                //查询分类信息
                if (skuInfoEntity.getCatalogId() != null) {
                    Resp<CategoryEntity> categoryEntityResp = pmsClient.queryCategoryById(skuInfoEntity.getCatalogId());
                    CategoryEntity categoryEntity = categoryEntityResp.getData();
                    if (categoryEntity != null) {
                        goods.setCategoryName(categoryEntity.getName());
                    }
                }

                Resp<SpuInfoEntity> spuResp = pmsClient.querySpuById(spuId);
                SpuInfoEntity spuInfoEntity = spuResp.getData();
                goods.setCreateTime(spuInfoEntity.getCreateTime());
                goods.setDefaultImage(skuInfoEntity.getSkuDefaultImg());
                goods.setPrice(skuInfoEntity.getPrice().doubleValue());
                goods.setSale(10l);
                goods.setSkuId(skuInfoEntity.getSkuId());
                goods.setSkuSubTitle(skuInfoEntity.getSkuSubtitle());
                goods.setSkuTitle(skuInfoEntity.getSkuTitle());
                //查询库存信息
                Resp<List<WareSkuEntity>> wareSkuResp = wmsClient.queryWareskusBySkuId(skuInfoEntity.getSkuId());
                List<WareSkuEntity> wareSkuEntities = wareSkuResp.getData();
                goods.setStore(wareSkuEntities.stream().anyMatch(wareSkuEntity -> wareSkuEntity.getStock() > 0));
                //查询聚合信息
                Resp<List<ProductAttrValueEntity>> attrValueResp = pmsClient.querySearchAttrValue(spuId);
                List<ProductAttrValueEntity> attrValueEntities = attrValueResp.getData();
                List<SearchAttrValue> searchAttrValues = attrValueEntities.stream().map(attrValueEntitity -> {
                    SearchAttrValue searchAttrValue = new SearchAttrValue();
                    searchAttrValue.setAttrId(attrValueEntitity.getAttrId());
                    searchAttrValue.setAttrName(attrValueEntitity.getAttrName());
                    searchAttrValue.setAttrValue(attrValueEntitity.getAttrValue());
                    return searchAttrValue;
                }).collect(Collectors.toList());


                goods.setAttrs(searchAttrValues);

                return goods;
            }).collect(Collectors.toList());
            goodsRepository.saveAll(goodsList);
        }
    }
}
