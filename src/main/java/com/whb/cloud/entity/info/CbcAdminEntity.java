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
 * @date 2021-03-09 10:07:22
 */
@Data
@TableName("cbc_admin")
public class CbcAdminEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer adminId;
	/**
	 * 
	 */
	private Integer accAdId;
	/**
	 * 
	 */
	private String adminName;
	/**
	 * 
	 */
	private String adminPhone;
	/**
	 * 
	 */
	private String adminEmail;

}
