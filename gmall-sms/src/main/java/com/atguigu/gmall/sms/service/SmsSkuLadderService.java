package com.atguigu.gmall.sms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.atguigu.gmall.sms.entity.SmsSkuLadderEntity;
import com.atguigu.core.bean.PageVo;
import com.atguigu.core.bean.QueryCondition;


/**
 * 商品阶梯价格
 *
 * @author weizhao
 * @email 1792926301@qq.com
 * @date 2019-12-31 16:48:23
 */
public interface SmsSkuLadderService extends IService<SmsSkuLadderEntity> {

    PageVo queryPage(QueryCondition params);
}

