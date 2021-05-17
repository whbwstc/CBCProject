package com.whb.cloud.entity.account;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-03-09 10:07:22
 */
@Data
@TableName("cbc_account_user")
public class CbcAccountUserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer accUserId;
	/**
	 * 
	 */
	private String accUserName;
	/**
	 * 
	 */
	private String accUserPwd;
	/**
	 * 1：启用 2：禁用
	 */
	private Integer accUserState;

	//用于前端与后端的交互
	private String token;

}
