package com.whb.cloud.entity.advice;

import com.baomidou.mybatisplus.annotation.IdType;
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
@TableName("cbc_advice")
public class CbcAdviceEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(type = IdType.AUTO)
	private Integer adviceId;
	/**
	 * 
	 */
	private Integer userId;
	/**
	 * 
	 */
	private String adviceText;
	/**
	 * 
	 */
	private String adviceTime;

}
