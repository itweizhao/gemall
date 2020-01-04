package com.atguigu.gmall.pms.service.impl;

import com.atguigu.gmall.pms.dao.AttrAttrgroupRelationDao;
import com.atguigu.gmall.pms.dao.AttrDao;
import com.atguigu.gmall.pms.entity.AttrAttrgroupRelationEntity;
import com.atguigu.gmall.pms.entity.AttrEntity;
import com.atguigu.gmall.pms.vo.GroupVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.core.bean.PageVo;
import com.atguigu.core.bean.Query;
import com.atguigu.core.bean.QueryCondition;

import com.atguigu.gmall.pms.dao.AttrGroupDao;
import com.atguigu.gmall.pms.entity.AttrGroupEntity;
import com.atguigu.gmall.pms.service.AttrGroupService;
import org.springframework.util.CollectionUtils;


@Service("attrGroupService")
public class AttrGroupServiceImpl extends ServiceImpl<AttrGroupDao, AttrGroupEntity> implements AttrGroupService {



    @Autowired
    private AttrAttrgroupRelationDao attrAttrgroupRelationDao;
    
    @Autowired
    private AttrDao attrDao;

    @Override
    public PageVo queryPage(QueryCondition params) {
        IPage<AttrGroupEntity> page = this.page(
                new Query<AttrGroupEntity>().getPage(params),
                new QueryWrapper<AttrGroupEntity>()
        );

        return new PageVo(page);
    }



    @Override
    public PageVo queryGroupsByCidPage(QueryCondition queryCondition, Long catId) {
        IPage<AttrGroupEntity> page = this.page(
                new Query<AttrGroupEntity>().getPage(queryCondition),
                new QueryWrapper<AttrGroupEntity>().eq("catelog_id",catId)
        );
        return new PageVo(page);
    }

    @Override
    public GroupVO queryGroupVOByGid(long gid) {

        GroupVO groupVO = new GroupVO();
        //1.根据gid查询组
        AttrGroupEntity groupEntity = getById(gid);
        BeanUtils.copyProperties(groupEntity,groupVO);
        //2.查询中间表
        List<AttrAttrgroupRelationEntity> relationEntities= attrAttrgroupRelationDao.selectList(new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_group_id", gid));
        groupVO.setRelations(relationEntities);

        //判断中间表是否为空
        if (CollectionUtils.isEmpty(relationEntities)){
            return groupVO;
        }
        //3.获取规格参数的id集合
        List<Long> attrIds = relationEntities.stream().map(relationEntitie -> relationEntitie.getAttrId()).collect(Collectors.toList());
        //4.查询规格参数
        List<AttrEntity> attrEntities = attrDao.selectBatchIds(attrIds);
        groupVO.setAttrEntities(attrEntities);
        return groupVO;
    }

}