package com.whb.cloud.service.account;

import com.baomidou.mybatisplus.extension.service.IService;
import com.whb.cloud.entity.account.CbcAccountAdminEntity;
import com.whb.cloud.entity.info.CbcAdminEntity;
import com.whb.cloud.utils.PageUtils;

import java.util.Map;

/**
 * @Author: cshnefu
 * @Description:
 * @Date: 23:46 2021/3/13
 **/
public interface CbcAccountAdminService extends IService<CbcAccountAdminEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * @Description: 登录
     * @Param: [username, password]
     **/
    public CbcAccountAdminEntity adminlogin(String username, String password);

    /**
     * @Description: 通过管理员邮箱查询管理员id
     * @Param: email
     **/
    public String findAdminIdByEmail(String email);
}

