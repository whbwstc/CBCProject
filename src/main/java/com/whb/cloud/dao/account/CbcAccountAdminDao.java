package com.whb.cloud.dao.account;

import com.whb.cloud.entity.account.CbcAccountAdminEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.whb.cloud.entity.info.CbcAdminEntity;
import com.whb.cloud.service.account.CbcAccountAdminService;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: wanghanbin
 * @Description:
 * @Date: 23:46 2021/3/13
 **/

@Mapper
public interface CbcAccountAdminDao extends BaseMapper<CbcAccountAdminEntity> {

    /**
     * @Description: 登录
     * @Param: [username, password]
     **/
     CbcAccountAdminEntity adminlogin(String username, String password);

    /**
     * @Description: 通过管理员邮箱查询管理员id
     * @Param: email
     **/
     String findAdminIdByEmail(String email);


}
