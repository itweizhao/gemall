package com.atguigu.gmall.sms.controller;

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

import com.atguigu.gmall.sms.entity.SmsSkuFullReductionEntity;
import com.atguigu.gmall.sms.service.SmsSkuFullReductionService;




/**
 * 商品满减信息
 *
 * @author weizhao
 * @email 1792926301@qq.com
 * @date 2019-12-31 16:48:23
 */
@Api(tags = "商品满减信息 管理")
@RestController
@RequestMapping("sms/smsskufullreduction")
public class SmsSkuFullReductionController {
    @Autowired
    private SmsSkuFullReductionService smsSkuFullReductionService;

    /**
     * 列表
     */
    @ApiOperation("分页查询(排序)")
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('sms:smsskufullreduction:list')")
    public Resp<PageVo> list(QueryCondition queryCondition) {
        PageVo page = smsSkuFullReductionService.queryPage(queryCondition);

        return Resp.ok(page);
    }


    /**
     * 信息
     */
    @ApiOperation("详情查询")
    @GetMapping("/info/{id}")
    @PreAuthorize("hasAuthority('sms:smsskufullreduction:info')")
    public Resp<SmsSkuFullReductionEntity> info(@PathVariable("id") Long id){
		SmsSkuFullReductionEntity smsSkuFullReduction = smsSkuFullReductionService.getById(id);

        return Resp.ok(smsSkuFullReduction);
    }

    /**
     * 保存
     */
    @ApiOperation("保存")
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('sms:smsskufullreduction:save')")
    public Resp<Object> save(@RequestBody SmsSkuFullReductionEntity smsSkuFullReduction){
		smsSkuFullReductionService.save(smsSkuFullReduction);

        return Resp.ok(null);
    }

    /**
     * 修改
     */
    @ApiOperation("修改")
    @PostMapping("/update")
    @PreAuthorize("hasAuthority('sms:smsskufullreduction:update')")
    public Resp<Object> update(@RequestBody SmsSkuFullReductionEntity smsSkuFullReduction){
		smsSkuFullReductionService.updateById(smsSkuFullReduction);

        return Resp.ok(null);
    }

    /**
     * 删除
     */
    @ApiOperation("删除")
    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('sms:smsskufullreduction:delete')")
    public Resp<Object> delete(@RequestBody Long[] ids){
		smsSkuFullReductionService.removeByIds(Arrays.asList(ids));

        return Resp.ok(null);
    }

}
