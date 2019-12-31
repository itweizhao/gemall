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

import com.atguigu.gmall.oms.entity.OmsOrderItemEntity;
import com.atguigu.gmall.oms.service.OmsOrderItemService;




/**
 * 订单项信息
 *
 * @author weizhao
 * @email 1792926301@qq.com
 * @date 2019-12-31 16:56:14
 */
@Api(tags = "订单项信息 管理")
@RestController
@RequestMapping("oms/omsorderitem")
public class OmsOrderItemController {
    @Autowired
    private OmsOrderItemService omsOrderItemService;

    /**
     * 列表
     */
    @ApiOperation("分页查询(排序)")
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('oms:omsorderitem:list')")
    public Resp<PageVo> list(QueryCondition queryCondition) {
        PageVo page = omsOrderItemService.queryPage(queryCondition);

        return Resp.ok(page);
    }


    /**
     * 信息
     */
    @ApiOperation("详情查询")
    @GetMapping("/info/{id}")
    @PreAuthorize("hasAuthority('oms:omsorderitem:info')")
    public Resp<OmsOrderItemEntity> info(@PathVariable("id") Long id){
		OmsOrderItemEntity omsOrderItem = omsOrderItemService.getById(id);

        return Resp.ok(omsOrderItem);
    }

    /**
     * 保存
     */
    @ApiOperation("保存")
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('oms:omsorderitem:save')")
    public Resp<Object> save(@RequestBody OmsOrderItemEntity omsOrderItem){
		omsOrderItemService.save(omsOrderItem);

        return Resp.ok(null);
    }

    /**
     * 修改
     */
    @ApiOperation("修改")
    @PostMapping("/update")
    @PreAuthorize("hasAuthority('oms:omsorderitem:update')")
    public Resp<Object> update(@RequestBody OmsOrderItemEntity omsOrderItem){
		omsOrderItemService.updateById(omsOrderItem);

        return Resp.ok(null);
    }

    /**
     * 删除
     */
    @ApiOperation("删除")
    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('oms:omsorderitem:delete')")
    public Resp<Object> delete(@RequestBody Long[] ids){
		omsOrderItemService.removeByIds(Arrays.asList(ids));

        return Resp.ok(null);
    }

}
