package com.whb.cloud.entity.order;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import lombok.Data;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-03-09 10:07:22
 */
@Data
@TableName("cbc_order")
public class CbcOrderEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(type = IdType.AUTO)
	private Integer orderId;
	/**
	 * 
	 */
	private Integer userId;
	/**
	 * 
	 */
	private Integer storeId;
	/**
	 * 
	 */
	private String enterTime;
	/**
	 * 
	 */
	private String outTime;
	/**
	 * 
	 */
	private Integer luggageNum;
	/**
	 * 
	 */
	private Integer bagNum;
	/**
	 * 
	 */
	private Integer orderMoney;
	/**
	 * 
	 */
	private String orderCode;
	/**
	 * 1：已存 2：已取
	 */
	private Integer orderState;

}
