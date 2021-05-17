package com.whb.cloud.service.score;

import com.baomidou.mybatisplus.extension.service.IService;
import com.whb.cloud.entity.score.CbcScoreEntity;
import com.whb.cloud.utils.PageUtils;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-03-09 10:07:22
 */
public interface CbcScoreService extends IService<CbcScoreEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * @Author: wanghanbin
     * @Description: 积分查询
     * @Date: 18:09 2021/4/30
     * @Param: [user_id]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.String>>
     **/
    List<Map<String,String>> getUserScoreByIdNoPage(Integer user_id);

    /**
     * @Author: wanghanbin
     * @Description: 积分总数
     * @Date: 16:06 2021/5/5
     * @Param: [userId]
     * @return: int
     **/
    int countUserScore(Integer user_id);
}

