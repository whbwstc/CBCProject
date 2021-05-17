package com.whb.cloud.entity.score;

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
@TableName("cbc_score")
public class CbcScoreEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(type = IdType.AUTO)
	private Integer scoreId;
	/**
	 * 
	 */
	private Integer userId;
	/**
	 * 
	 */
	private Integer orderId;
	/**
	 * 
	 */
	private Integer scoreNumber;
	/**
	 * 
	 */
	private String scoreTime;

}
