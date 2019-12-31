package com.atguigu.gmall.ums.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.core.bean.PageVo;
import com.atguigu.core.bean.Query;
import com.atguigu.core.bean.QueryCondition;

import com.atguigu.gmall.ums.dao.UmsIntegrationChangeHistoryDao;
import com.atguigu.gmall.ums.entity.UmsIntegrationChangeHistoryEntity;
import com.atguigu.gmall.ums.service.UmsIntegrationChangeHistoryService;


@Service("umsIntegrationChangeHistoryService")
public class UmsIntegrationChangeHistoryServiceImpl extends ServiceImpl<UmsIntegrationChangeHistoryDao, UmsIntegrationChangeHistoryEntity> implements UmsIntegrationChangeHistoryService {

    @Override
    public PageVo queryPage(QueryCondition params) {
        IPage<UmsIntegrationChangeHistoryEntity> page = this.page(
                new Query<UmsIntegrationChangeHistoryEntity>().getPage(params),
                new QueryWrapper<UmsIntegrationChangeHistoryEntity>()
        );

        return new PageVo(page);
    }

}