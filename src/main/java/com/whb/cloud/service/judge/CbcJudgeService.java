package com.whb.cloud.service.judge;

import com.baomidou.mybatisplus.extension.service.IService;
import com.whb.cloud.entity.judge.CbcJudgeEntity;
import com.whb.cloud.utils.PageUtils;
import java.util.Map;

/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-03-09 10:07:22
 */
public interface CbcJudgeService extends IService<CbcJudgeEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * @Author: wanghanbin
     * @Description:  查找当前订单评价状态
     * @Date: 15:32 2021/4/14
     * @Param: [order_id]
     * @return: int
     **/
    int getJudgeStateById(Integer order_id);

    /**
     * @Author: wanghanbin
     * @Description: 根据订单id评价
     * @Date: 23:56 2021/4/18
     * @Param: [judgeGrade, judgeText, judgeTime, order_id]
     * @return: int
     **/
    int updateByOrderId(Integer judgeGrade,String judgeText,String judgeTime,Integer order_id);
}

