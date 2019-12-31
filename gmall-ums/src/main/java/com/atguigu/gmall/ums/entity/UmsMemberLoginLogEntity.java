package com.atguigu.gmall.ums.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 会员登录记录
 * 
 * @author weizhao
 * @email 1792926301@qq.com
 * @date 2019-12-31 16:41:27
 */
@ApiModel
@Data
@TableName("ums_member_login_log")
public class UmsMemberLoginLogEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	@ApiModelProperty(name = "id",value = "id")
	private Long id;
	/**
	 * member_id
	 */
	@ApiModelProperty(name = "memberId",value = "member_id")
	private Long memberId;
	/**
	 * 创建时间
	 */
	@ApiModelProperty(name = "createTime",value = "创建时间")
	private Date createTime;
	/**
	 * ip
	 */
	@ApiModelProperty(name = "ip",value = "ip")
	private String ip;
	/**
	 * city
	 */
	@ApiModelProperty(name = "city",value = "city")
	private String city;
	/**
	 * 登录类型[1-web，2-app]
	 */
	@ApiModelProperty(name = "loginType",value = "登录类型[1-web，2-app]")
	private Integer loginType;

}
