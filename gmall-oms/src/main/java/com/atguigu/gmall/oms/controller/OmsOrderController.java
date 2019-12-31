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

import com.atguigu.gmall.oms.entity.OmsOrderEntity;
import com.atguigu.gmall.oms.service.OmsOrderService;




/**
 * 订单
 *
 * @author weizhao
 * @email 1792926301@qq.com
 * @date 2019-12-31 16:56:14
 */
@Api(tags = "订单 管理")
@RestController
@RequestMapping("oms/omsorder")
public class OmsOrderController {
    @Autowired
    private OmsOrderService omsOrderService;

    /**
     * 列表
     */
    @ApiOperation("分页查询(排序)")
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('oms:omsorder:list')")
    public Resp<PageVo> list(QueryCondition queryCondition) {
        PageVo page = omsOrderService.queryPage(queryCondition);

        return Resp.ok(page);
    }


    /**
     * 信息
     */
    @ApiOperation("详情查询")
    @GetMapping("/info/{id}")
    @PreAuthorize("hasAuthority('oms:omsorder:info')")
    public Resp<OmsOrderEntity> info(@PathVariable("id") Long id){
		OmsOrderEntity omsOrder = omsOrderService.getById(id);

        return Resp.ok(omsOrder);
    }

    /**
     * 保存
     */
    @ApiOperation("保存")
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('oms:omsorder:save')")
    public Resp<Object> save(@RequestBody OmsOrderEntity omsOrder){
		omsOrderService.save(omsOrder);

        return Resp.ok(null);
    }

    /**
     * 修改
     */
    @ApiOperation("修改")
    @PostMapping("/update")
    @PreAuthorize("hasAuthority('oms:omsorder:update')")
    public Resp<Object> update(@RequestBody OmsOrderEntity omsOrder){
		omsOrderService.updateById(omsOrder);

        return Resp.ok(null);
    }

    /**
     * 删除
     */
    @ApiOperation("删除")
    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('oms:omsorder:delete')")
    public Resp<Object> delete(@RequestBody Long[] ids){
		omsOrderService.removeByIds(Arrays.asList(ids));

        return Resp.ok(null);
    }

}
