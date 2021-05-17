package com.whb.cloud.service.account.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.whb.cloud.dao.account.CbcAccountAdminDao;
import com.whb.cloud.entity.account.CbcAccountAdminEntity;
import com.whb.cloud.service.account.CbcAccountAdminService;
import com.whb.cloud.utils.PageUtils;
import com.whb.cloud.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Author: wanghanbin
 * @Description:
 * @Date: 23:46 2021/3/13
 **/

@Service("cbcAccountAdminService")
public class CbcAccountAdminServiceImpl extends ServiceImpl<CbcAccountAdminDao, CbcAccountAdminEntity> implements CbcAccountAdminService {

    @Autowired
    CbcAccountAdminDao accountAdminDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CbcAccountAdminEntity> page = this.page(
                new Query<CbcAccountAdminEntity>().getPage(params),
                new QueryWrapper<CbcAccountAdminEntity>()
        );

        return new PageUtils(page);
    }

    /**
     * @Author: wanghanbin
     * @Description: 登录
     * @Date: 23:46 2021/3/13
     * @Param: [username, password]
     * @return: com.whb.cloud.entity.account.CbcAccountAdminEntity
     **/
    @Override
    public CbcAccountAdminEntity adminlogin(String username, String password) {
        try {
            CbcAccountAdminEntity adminEntity =  accountAdminDao.adminlogin(username,password);
            return adminEntity;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Author: wanghanbin
     * @Description: 通过管理员邮箱查询管理员id
     * @Date: 0:04 2021/3/14
     * @Param: [email]
     * @return: com.whb.cloud.entity.info.CbcAdminEntity
     **/
    @Override
    public String findAdminIdByEmail(String email) {
        String admin_id = null;
        try {
            admin_id = accountAdminDao.findAdminIdByEmail(email);
            return admin_id;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}