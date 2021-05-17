package com.whb.cloud.dao.info;

import com.whb.cloud.entity.info.CbcUserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-03-11 23:01:25
 */
@Mapper
public interface CbcUserDao extends BaseMapper<CbcUserEntity> {

    /**
     * @Author: wanghanbin
     * @Description: 查询列表
     * @Date: 14:45 2021/3/17
     * @Param: [startRow, pageSize]
     * @return: java.util.List<com.whb.cloud.entity.order.CbcOrderEntity>
     **/
    List<Map<String,String>> findUserAll(Integer startRow, Integer pageSize);

    /**
     * @Author: wanghanbin
     * @Description: 更新用户余额
     * @Date: 17:54 2021/4/13
     * @Param: [user_money, user_id]
     * @return: int
     **/
    int updateUserMoney(int user_money,int user_id);

    /**
     * @Author: wanghanbin
     * @Description: 更新用户积分
     * @Date: 11:43 2021/4/30
     * @Param: [user_score, user_id]
     * @return: int
     **/
    int updateUserScore(int user_score,int user_id);
	
}
