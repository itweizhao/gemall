package com.atguigu.gmall.sms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.atguigu.gmall.sms.entity.SmsSkuBoundsEntity;
import com.atguigu.core.bean.PageVo;
import com.atguigu.core.bean.QueryCondition;


/**
 * 商品sku积分设置
 *
 * @author weizhao
 * @email 1792926301@qq.com
 * @date 2019-12-31 16:48:23
 */
public interface SmsSkuBoundsService extends IService<SmsSkuBoundsEntity> {

    PageVo queryPage(QueryCondition params);
}

