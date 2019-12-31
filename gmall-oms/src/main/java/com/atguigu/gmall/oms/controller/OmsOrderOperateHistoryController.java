package com.atguigu.gmall.oms.controller;

import java.util.Arrays;
import java.util.Map;


import com.atguigu.core.bean.PageVo;
import com.atguigu.core.bean.QueryCondition;
import com.atguigu.core.bean.Resp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.atguigu.gmall.oms.entity.OmsOrderOperateHistoryEntity;
import com.atguigu.gmall.oms.service.OmsOrderOperateHistoryService;




/**
 * 订单操作历史记录
 *
 * @author weizhao
 * @email 1792926301@qq.com
 * @date 2019-12-31 16:56:14
 */
@Api(tags = "订单操作历史记录 管理")
@RestController
@RequestMapping("oms/omsorderoperatehistory")
public class OmsOrderOperateHistoryController {
    @Autowired
    private OmsOrderOperateHistoryService omsOrderOperateHistoryService;

    /**
     * 列表
     */
    @ApiOperation("分页查询(排序)")
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('oms:omsorderoperatehistory:list')")
    public Resp<PageVo> list(QueryCondition queryCondition) {
        PageVo page = omsOrderOperateHistoryService.queryPage(queryCondition);

        return Resp.ok(page);
    }


    /**
     * 信息
     */
    @ApiOperation("详情查询")
    @GetMapping("/info/{id}")
    @PreAuthorize("hasAuthority('oms:omsorderoperatehistory:info')")
    public Resp<OmsOrderOperateHistoryEntity> info(@PathVariable("id") Long id){
		OmsOrderOperateHistoryEntity omsOrderOperateHistory = omsOrderOperateHistoryService.getById(id);

        return Resp.ok(omsOrderOperateHistory);
    }

    /**
     * 保存
     */
    @ApiOperation("保存")
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('oms:omsorderoperatehistory:save')")
    public Resp<Object> save(@RequestBody OmsOrderOperateHistoryEntity omsOrderOperateHistory){
		omsOrderOperateHistoryService.save(omsOrderOperateHistory);

        return Resp.ok(null);
    }

    /**
     * 修改
     */
    @ApiOperation("修改")
    @PostMapping("/update")
    @PreAuthorize("hasAuthority('oms:omsorderoperatehistory:update')")
    public Resp<Object> update(@RequestBody OmsOrderOperateHistoryEntity omsOrderOperateHistory){
		omsOrderOperateHistoryService.updateById(omsOrderOperateHistory);

        return Resp.ok(null);
    }

    /**
     * 删除
     */
    @ApiOperation("删除")
    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('oms:omsorderoperatehistory:delete')")
    public Resp<Object> delete(@RequestBody Long[] ids){
		omsOrderOperateHistoryService.removeByIds(Arrays.asList(ids));

        return Resp.ok(null);
    }

}
