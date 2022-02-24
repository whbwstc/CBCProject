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
@TableName("cbc_account_admin")
public class CbcAccountAdminEntity implements Serializable {


	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer accAdId;

	/**
	 * @Author: cshnefu
	 * @Description: 寄存点id
	 * @Date: 22:06 2021/4/19
	 * @Param:
	 * @return:
	 **/
	private Integer accStoreId;
	/**
	 * 
	 */
	private String accAdName;
	/**
	 * 
	 */
	private String accAdPwd;
	/**
	 * 1：启用 2：锁定
	 */
	private Integer accAdState;

	//用于前端与后端的交互
	private String token;

}
