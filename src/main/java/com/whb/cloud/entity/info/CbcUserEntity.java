package com.whb.cloud.entity.info;

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
 * @date 2021-03-11 23:01:25
 */
@Data
@TableName("cbc_user")
public class CbcUserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer userId;
	/**
	 * 
	 */
	private String userName;
	/**
	 * 
	 */
	private String userPhone;
	/**
	 * 
	 */
	private String userEmail;
	/**
	 * 
	 */
	private Integer userMoney;
	/**
	 *
	 */
	private Integer userScore;
	/**
	 * 
	 */
	private Integer accUserId;

}
