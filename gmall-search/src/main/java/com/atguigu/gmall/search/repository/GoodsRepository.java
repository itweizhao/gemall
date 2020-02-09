package com.atguigu.gmall.search.repository;

import com.atguigu.gmall.search.pojo.Goods;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author shkstart
 * @create 2020-01-30 14:09
 */
public interface GoodsRepository extends ElasticsearchRepository<Goods,Long> {
}
