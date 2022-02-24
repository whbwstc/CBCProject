package com.whb.cloud.dao.store;

import com.whb.cloud.entity.store.CbcStoreEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-03-18 15:02:29
 */
@Mapper
public interface CbcStoreDao extends BaseMapper<CbcStoreEntity> {

    /**
     * @Author: cshnefu
     * @Description: 列表查询
     * @Date: 9:40 2021/3/25
     * @Param: [startRow, pageSize]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.String>>
     **/
    List<Map<String,String>> findStoreAll(Integer startRow, Integer pageSize);

    /**
     * @Author: cshnefu
     * @Description: 按所属城市查询寄存点
     * @Date: 10:28 2021/5/5
     * @Param: [store_city]
     * @return: java.util.List<com.whb.cloud.entity.store.CbcStoreEntity>
     **/
    List<CbcStoreEntity> getStoreByCity(String store_city);

    /**
     * @Author: cshnefu
     * @Description: 修改寄存点状态
     * @Date: 14:46 2021/3/28
     * @Param: [state, store_id]
     * @return: int
     **/
    int updateStateById(String state,Integer store_id);


    /**
     * @Author: cshnefu
     * @Description: 寄存点背包和行李箱数量更新
     * @Date: 17:45 2021/4/13
     * @Param: [luggage_num, bag_num, store_id]
     * @return: int
     **/
    int updateSumById(Integer luggage_num,Integer bag_num ,Integer store_id);
}
