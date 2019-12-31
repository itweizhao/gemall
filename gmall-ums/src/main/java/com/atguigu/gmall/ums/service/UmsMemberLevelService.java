package com.atguigu.gmall.ums.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.atguigu.gmall.ums.entity.UmsMemberLevelEntity;
import com.atguigu.core.bean.PageVo;
import com.atguigu.core.bean.QueryCondition;


/**
 * 会员等级
 *
 * @author weizhao
 * @email 1792926301@qq.com
 * @date 2019-12-31 16:41:27
 */
public interface UmsMemberLevelService extends IService<UmsMemberLevelEntity> {

    PageVo queryPage(QueryCondition params);
}

