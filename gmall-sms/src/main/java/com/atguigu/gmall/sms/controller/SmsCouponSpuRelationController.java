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

import com.atguigu.gmall.sms.entity.SmsCouponSpuRelationEntity;
import com.atguigu.gmall.sms.service.SmsCouponSpuRelationService;




/**
 * 优惠券与产品关联
 *
 * @author weizhao
 * @email 1792926301@qq.com
 * @date 2019-12-31 16:48:23
 */
@Api(tags = "优惠券与产品关联 管理")
@RestController
@RequestMapping("sms/smscouponspurelation")
public class SmsCouponSpuRelationController {
    @Autowired
    private SmsCouponSpuRelationService smsCouponSpuRelationService;

    /**
     * 列表
     */
    @ApiOperation("分页查询(排序)")
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('sms:smscouponspurelation:list')")
    public Resp<PageVo> list(QueryCondition queryCondition) {
        PageVo page = smsCouponSpuRelationService.queryPage(queryCondition);

        return Resp.ok(page);
    }


    /**
     * 信息
     */
    @ApiOperation("详情查询")
    @GetMapping("/info/{id}")
    @PreAuthorize("hasAuthority('sms:smscouponspurelation:info')")
    public Resp<SmsCouponSpuRelationEntity> info(@PathVariable("id") Long id){
		SmsCouponSpuRelationEntity smsCouponSpuRelation = smsCouponSpuRelationService.getById(id);

        return Resp.ok(smsCouponSpuRelation);
    }

    /**
     * 保存
     */
    @ApiOperation("保存")
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('sms:smscouponspurelation:save')")
    public Resp<Object> save(@RequestBody SmsCouponSpuRelationEntity smsCouponSpuRelation){
		smsCouponSpuRelationService.save(smsCouponSpuRelation);

        return Resp.ok(null);
    }

    /**
     * 修改
     */
    @ApiOperation("修改")
    @PostMapping("/update")
    @PreAuthorize("hasAuthority('sms:smscouponspurelation:update')")
    public Resp<Object> update(@RequestBody SmsCouponSpuRelationEntity smsCouponSpuRelation){
		smsCouponSpuRelationService.updateById(smsCouponSpuRelation);

        return Resp.ok(null);
    }

    /**
     * 删除
     */
    @ApiOperation("删除")
    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('sms:smscouponspurelation:delete')")
    public Resp<Object> delete(@RequestBody Long[] ids){
		smsCouponSpuRelationService.removeByIds(Arrays.asList(ids));

        return Resp.ok(null);
    }

}
