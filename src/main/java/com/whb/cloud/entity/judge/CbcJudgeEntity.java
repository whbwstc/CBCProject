package com.whb.cloud.entity.judge;

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
@TableName("cbc_judge")
public class CbcJudgeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(type = IdType.AUTO)
	private Integer judgeId;
	/**
	 * 
	 */
	private Integer userId;
	/**
	 * 
	 */
	private Integer orderId;

	/**
	 * @Author: wanghanbin
	 * @Description: 评价等级
	 * @Date: 19:05 2021/4/18
	 **/
	private Integer judgeGrade;
	/**
	 * 
	 */
	private String judgeText;
	/**
	 * 
	 */
	private String judgeTime;
	/**
	 * 1:未评价 2:已评价
	 */
	private Integer judgeState;

}
