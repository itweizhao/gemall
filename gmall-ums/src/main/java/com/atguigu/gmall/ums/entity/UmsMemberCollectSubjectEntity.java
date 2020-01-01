package com.atguigu.gmall.ums.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 会员收藏的专题活动
 * 
 * @author weizhao
 * @email 1792926301@qq.com
 * @date 2019-12-31 16:41:27
 */
@ApiModel
@Data
@TableName("ums_member_collect_subject")
public class UmsMemberCollectSubjectEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	@ApiModelProperty(name = "id",value = "id")
	private Long id;
	/**
	 * subject_id
	 */
	@ApiModelProperty(name = "subjectId",value = "subject_id")
	private Long subjectId;
	/**
	 * subject_name
	 */
	@ApiModelProperty(name = "subjectName",value = "subject_name")
	private String subjectName;
	/**
	 * subject_img
	 */
	@ApiModelProperty(name = "subjectImg",value = "subject_img")
	private String subjectImg;
	/**
	 * 活动url
	 */
	@ApiModelProperty(name = "subjectUrll",value = "活动url")
	private String subjectUrll;

}