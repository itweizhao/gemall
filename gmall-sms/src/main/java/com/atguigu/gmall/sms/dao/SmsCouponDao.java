package com.atguigu.gmall.sms.dao;

import com.atguigu.gmall.sms.entity.SmsCouponEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券信息
 * 
 * @author weizhao
 * @email 1792926301@qq.com
 * @date 2019-12-31 16:48:23
 */
@Mapper
public interface SmsCouponDao extends BaseMapper<SmsCouponEntity> {
	
}
