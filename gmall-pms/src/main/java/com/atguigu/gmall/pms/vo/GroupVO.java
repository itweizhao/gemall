package com.atguigu.gmall.pms.vo;

import com.atguigu.gmall.pms.entity.AttrAttrgroupRelationEntity;
import com.atguigu.gmall.pms.entity.AttrEntity;
import com.atguigu.gmall.pms.entity.AttrGroupEntity;
import lombok.Data;

import java.util.List;

/**
 * @author shkstart
 * @data 2020/1/3-21:23
 */
@Data
public class GroupVO extends AttrGroupEntity {

    private List<AttrEntity> attrEntities;


    private List<AttrAttrgroupRelationEntity> relations;
}
