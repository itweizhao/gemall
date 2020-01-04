package com.atguigu.gmall.pms.service;

import com.atguigu.gmall.pms.vo.AttrVO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.atguigu.gmall.pms.entity.AttrEntity;
import com.atguigu.core.bean.PageVo;
import com.atguigu.core.bean.QueryCondition;


/**
 * 商品属性
 *
 * @author weizhao
 * @email 1792926301@qq.com
 * @date 2020-01-02 12:59:44
 */
public interface AttrService extends IService<AttrEntity> {

    PageVo queryPage(QueryCondition params);

    PageVo queryAttrByCidOrTypePage(QueryCondition queryCondition, long cid, Integer type);

    void saveAttrVO(AttrVO attrVO);
}

