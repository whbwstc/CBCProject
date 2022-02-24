package com.whb.cloud.dao.order;

import com.whb.cloud.entity.order.CbcOrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-03-09 10:07:22
 */
@Mapper
public interface CbcOrderDao extends BaseMapper<CbcOrderEntity> {

    /**
     * @Author: cshnefu
     * @Description: 列表分页查询
     * @Date: 14:45 2021/3/17
     * @Param: [startRow, pageSize]
     * @return: java.util.List<com.whb.cloud.entity.order.CbcOrderEntity>
     **/
    List<Map<String,String>> findOrderAll(Integer startRow, Integer pageSize);


    /**
     * @Author: cshnefu
     * @Description: 用户订单查询
     * @Date: 10:45 2021/4/14
     * @Param: [userId]
     * @return: java.util.List<com.whb.cloud.entity.order.CbcOrderEntity>
     **/
    List<Map<String,String>> getUserOrderById(Integer startRow, Integer pageSize,Integer user_id);

    /**
     * @Author: cshnefu
     * @Description: 用户订单不分页查询
     * @Date: 18:09 2021/4/30
     * @Param: [user_id]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.String>>
     **/
    List<Map<String,String>> getUserOrderByIdNoPage(Integer user_id);

    /**
     * @Author: cshnefu
     * @Description: 寄存点订单查询
     * @Date: 17:34 2021/4/20
     * @Param: [startRow, pageSize, store_id]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.String>>
     **/
    List<Map<String,String>> getStoreOrderById(Integer startRow, Integer pageSize,Integer store_id);

    /**
     * @Author: cshnefu
     * @Description: 用户寄存点订单查询
     * @Date: 21:43 2021/4/27
     * @Param: [store_id, order_code]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.String>>
     **/
    List<Map<String,String>> getUserStoreOrderById(Integer store_id,String order_code);




    /**
     * @Author: cshnefu
     * @Description: 订单详细查询
     * @Date: 15:05 2021/5/1
     * @Param: [order_id]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.String>>
     **/
    Map<String,String> getUserOrderInfoById(Integer order_id);

    /**
     * @Author: cshnefu
     * @Description: 根据订单编号查询订单ID
     * @Date: 16:15 2021/4/15
     * @Param:
     * @return:
     **/
    int getOrderIdByCode(Integer order_code);

    /**
     * @Author: cshnefu
     * @Description:  查找当前订单状态
     * @Date: 15:32 2021/4/14
     * @Param: [order_id]
     * @return: int
     **/
    Map<String,String> getOrderStateById(Integer order_id);

    /**
     * @Author: cshnefu
     * @Description: 用户订单总数
     * @Date: 11:31 2021/4/14
     * @Param: [userId]
     * @return: int
     **/
    int countUserOrder(Integer userId);

    /**
     * @Author: cshnefu
     * @Description: 寄存点订单总数
     * @Date: 2:59 2021/5/6
     * @Param: [store_id]
     * @return: int
     **/
    int countStoreOrder(Integer store_id);

    /**
     * @Author: cshnefu
     * @Description: 取件
     * @Date: 22:43 2021/4/27
     * @Param: [order_id]
     * @return: int
     **/
    int updateOrderState(Integer order_id);

}
