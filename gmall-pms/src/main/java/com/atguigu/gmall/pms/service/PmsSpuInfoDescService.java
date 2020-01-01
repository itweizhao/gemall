package com.atguigu.gmall.pms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.atguigu.gmall.pms.entity.PmsSpuInfoDescEntity;
import com.atguigu.core.bean.PageVo;
import com.atguigu.core.bean.QueryCondition;


/**
 * spu信息介绍
 *
 * @author weizhao
 * @email 1792926301@qq.com
 * @date 2020-01-01 15:23:08
 */
public interface PmsSpuInfoDescService extends IService<PmsSpuInfoDescEntity> {

    PageVo queryPage(QueryCondition params);
}

