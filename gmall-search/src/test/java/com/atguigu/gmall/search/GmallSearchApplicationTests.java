package com.atguigu.gmall.search;


import com.atguigu.core.bean.QueryCondition;
import com.atguigu.core.bean.Resp;
import com.atguigu.gmall.pms.entity.*;
import com.atguigu.gmall.search.feign.GmallPmsClient;
import com.atguigu.gmall.search.feign.GmallWmsClient;
import com.atguigu.gmall.search.pojo.Goods;
import com.atguigu.gmall.search.pojo.SearchAttrValue;
import com.atguigu.gmall.search.repository.GoodsRepository;
import com.atguigu.gmall.wms.entity.WareSkuEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
class GmallSearchApplicationTests {

    @Autowired
    private ElasticsearchRestTemplate restTemplate;

    @Autowired
    private GoodsRepository goodsRepository;

    @Autowired
    private GmallPmsClient pmsClient;

    @Autowired
    private GmallWmsClient wmsClient;

    @Test
    void contextLoads() {
        restTemplate.createIndex(Goods.class);
        restTemplate.putMapping(Goods.class);
    }


    @Test
    void importData() {
        Long pageNum = 1l;
        Long pageSize = 100l;
        do {
            QueryCondition queryCondition = new QueryCondition();
            queryCondition.setPage(pageNum);
            queryCondition.setLimit(pageSize);
            //分页查询spu
            Resp<List<SpuInfoEntity>> listResp = pmsClient.querySpuByPage(queryCondition);
            List<SpuInfoEntity> spuInfoEntitys = listResp.getData();
            //判断spu是否为空
            if (CollectionUtils.isEmpty(spuInfoEntitys)){
                System.out.println("12345678941316554654");
                return;
            }
            //遍历spu, 获取spu导入es
            spuInfoEntitys.forEach(spuInfoEntity -> {
                Resp<List<SkuInfoEntity>> resp = pmsClient.querySkusBySpuId(spuInfoEntity.getId());
                List<SkuInfoEntity> skuInfoEntities = resp.getData();
                if (!CollectionUtils.isEmpty(skuInfoEntities)){
                    List<Goods> goodsList = skuInfoEntities.stream().map(skuInfoEntity -> {
                        Goods goods = new Goods();

                        goods.setBrandId(skuInfoEntity.getBrandId());
                        //查询品牌信息
                        if (skuInfoEntity.getBrandId() != null){
                            Resp<BrandEntity> brandEntityResp = pmsClient.queryBrandById(skuInfoEntity.getBrandId());
                            BrandEntity brandEntity = brandEntityResp.getData();
                            goods.setBrandName(brandEntity.getName());
                            if (brandEntity != null){
                                goods.setCategoryId(skuInfoEntity.getCatalogId());
                            }
                        }

                        //查询分类信息
                        if (skuInfoEntity.getCatalogId() != null){
                            Resp<CategoryEntity> categoryEntityResp = pmsClient.queryCategoryById(skuInfoEntity.getCatalogId());
                            CategoryEntity categoryEntity = categoryEntityResp.getData();
                            if (categoryEntity != null){
                                goods.setCategoryName(categoryEntity.getName());
                            }
                        }

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
                        Resp<List<ProductAttrValueEntity>> attrValueResp = pmsClient.querySearchAttrValue(spuInfoEntity.getId());
                        List<ProductAttrValueEntity> attrValueEntities= attrValueResp.getData();
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
            });
            pageSize= (long)spuInfoEntitys.size();
            pageNum++;
            System.out.println(pageNum);
        }while (pageSize == 100);
    }
}
