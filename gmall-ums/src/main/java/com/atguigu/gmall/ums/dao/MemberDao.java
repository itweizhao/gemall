package com.atguigu.gmall.ums.dao;

import com.atguigu.gmall.ums.entity.MemberEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员
 * 
 * @author weizhao
 * @email 1792926301@qq.com
 * @date 2020-01-02 13:41:32
 */
@Mapper
public interface MemberDao extends BaseMapper<MemberEntity> {
	
}
