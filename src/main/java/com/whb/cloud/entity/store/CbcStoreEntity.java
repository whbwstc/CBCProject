package com.whb.cloud.entity.store;

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
 * @date 2021-03-18 15:02:29
 */
@Data
@TableName("cbc_store")
public class CbcStoreEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(type = IdType.AUTO)
	private Integer storeId;
	/**
	 * 
	 */
	private String storeName;

	private String storeAddr;
	/**
	 * 
	 */
	private String storeCity;
	/**
	 * 
	 */
	private Integer luggageNum;
	/**
	 * 
	 */
	private Integer bagNum;
	/**
	 * 1：营业 2：非营业
	 */
	private Integer storeState;

}
