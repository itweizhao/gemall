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

import com.atguigu.gmall.oms.entity.OmsOrderReturnReasonEntity;
import com.atguigu.gmall.oms.service.OmsOrderReturnReasonService;




/**
 * 退货原因
 *
 * @author weizhao
 * @email 1792926301@qq.com
 * @date 2019-12-31 16:56:14
 */
@Api(tags = "退货原因 管理")
@RestController
@RequestMapping("oms/omsorderreturnreason")
public class OmsOrderReturnReasonController {
    @Autowired
    private OmsOrderReturnReasonService omsOrderReturnReasonService;

    /**
     * 列表
     */
    @ApiOperation("分页查询(排序)")
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('oms:omsorderreturnreason:list')")
    public Resp<PageVo> list(QueryCondition queryCondition) {
        PageVo page = omsOrderReturnReasonService.queryPage(queryCondition);

        return Resp.ok(page);
    }


    /**
     * 信息
     */
    @ApiOperation("详情查询")
    @GetMapping("/info/{id}")
    @PreAuthorize("hasAuthority('oms:omsorderreturnreason:info')")
    public Resp<OmsOrderReturnReasonEntity> info(@PathVariable("id") Long id){
		OmsOrderReturnReasonEntity omsOrderReturnReason = omsOrderReturnReasonService.getById(id);

        return Resp.ok(omsOrderReturnReason);
    }

    /**
     * 保存
     */
    @ApiOperation("保存")
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('oms:omsorderreturnreason:save')")
    public Resp<Object> save(@RequestBody OmsOrderReturnReasonEntity omsOrderReturnReason){
		omsOrderReturnReasonService.save(omsOrderReturnReason);

        return Resp.ok(null);
    }

    /**
     * 修改
     */
    @ApiOperation("修改")
    @PostMapping("/update")
    @PreAuthorize("hasAuthority('oms:omsorderreturnreason:update')")
    public Resp<Object> update(@RequestBody OmsOrderReturnReasonEntity omsOrderReturnReason){
		omsOrderReturnReasonService.updateById(omsOrderReturnReason);

        return Resp.ok(null);
    }

    /**
     * 删除
     */
    @ApiOperation("删除")
    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('oms:omsorderreturnreason:delete')")
    public Resp<Object> delete(@RequestBody Long[] ids){
		omsOrderReturnReasonService.removeByIds(Arrays.asList(ids));

        return Resp.ok(null);
    }

}
