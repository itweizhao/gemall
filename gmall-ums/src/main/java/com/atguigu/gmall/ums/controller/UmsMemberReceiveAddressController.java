package com.atguigu.gmall.ums.controller;

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

import com.atguigu.gmall.ums.entity.UmsMemberReceiveAddressEntity;
import com.atguigu.gmall.ums.service.UmsMemberReceiveAddressService;




/**
 * 会员收货地址
 *
 * @author weizhao
 * @email 1792926301@qq.com
 * @date 2019-12-31 16:41:27
 */
@Api(tags = "会员收货地址 管理")
@RestController
@RequestMapping("ums/umsmemberreceiveaddress")
public class UmsMemberReceiveAddressController {
    @Autowired
    private UmsMemberReceiveAddressService umsMemberReceiveAddressService;

    /**
     * 列表
     */
    @ApiOperation("分页查询(排序)")
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('ums:umsmemberreceiveaddress:list')")
    public Resp<PageVo> list(QueryCondition queryCondition) {
        PageVo page = umsMemberReceiveAddressService.queryPage(queryCondition);

        return Resp.ok(page);
    }


    /**
     * 信息
     */
    @ApiOperation("详情查询")
    @GetMapping("/info/{id}")
    @PreAuthorize("hasAuthority('ums:umsmemberreceiveaddress:info')")
    public Resp<UmsMemberReceiveAddressEntity> info(@PathVariable("id") Long id){
		UmsMemberReceiveAddressEntity umsMemberReceiveAddress = umsMemberReceiveAddressService.getById(id);

        return Resp.ok(umsMemberReceiveAddress);
    }

    /**
     * 保存
     */
    @ApiOperation("保存")
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('ums:umsmemberreceiveaddress:save')")
    public Resp<Object> save(@RequestBody UmsMemberReceiveAddressEntity umsMemberReceiveAddress){
		umsMemberReceiveAddressService.save(umsMemberReceiveAddress);

        return Resp.ok(null);
    }

    /**
     * 修改
     */
    @ApiOperation("修改")
    @PostMapping("/update")
    @PreAuthorize("hasAuthority('ums:umsmemberreceiveaddress:update')")
    public Resp<Object> update(@RequestBody UmsMemberReceiveAddressEntity umsMemberReceiveAddress){
		umsMemberReceiveAddressService.updateById(umsMemberReceiveAddress);

        return Resp.ok(null);
    }

    /**
     * 删除
     */
    @ApiOperation("删除")
    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('ums:umsmemberreceiveaddress:delete')")
    public Resp<Object> delete(@RequestBody Long[] ids){
		umsMemberReceiveAddressService.removeByIds(Arrays.asList(ids));

        return Resp.ok(null);
    }

}
