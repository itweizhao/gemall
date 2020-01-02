package com.atguigu.gmall.pms.dao;

import com.atguigu.gmall.pms.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品三级分类
 * 
 * @author weizhao
 * @email 1792926301@qq.com
 * @date 2020-01-02 12:59:44
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {
	
}
