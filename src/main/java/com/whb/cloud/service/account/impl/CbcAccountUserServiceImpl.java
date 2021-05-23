package com.whb.cloud.service.account.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.whb.cloud.dao.account.CbcAccountUserDao;
import com.whb.cloud.entity.account.CbcAccountAdminEntity;
import com.whb.cloud.entity.account.CbcAccountUserEntity;
import com.whb.cloud.service.account.CbcAccountUserService;
import com.whb.cloud.utils.PageUtils;
import com.whb.cloud.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


@Service("cbcAccountUserService")
public class CbcAccountUserServiceImpl extends ServiceImpl<CbcAccountUserDao, CbcAccountUserEntity> implements CbcAccountUserService {

    @Autowired
    CbcAccountUserDao cbcAccountUserDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CbcAccountUserEntity> page = this.page(
                new Query<CbcAccountUserEntity>().getPage(params),
                new QueryWrapper<CbcAccountUserEntity>()
        );

        return new PageUtils(page);
    }

    /**
     * @Author: wanghanbin
     * @Description: 登录
     * @Date: 23:46 2021/3/13
     * @Param: [username, password]
     * @return: com.whb.cloud.entity.account.CbcAccountUserEntity
     **/
    @Override
    public CbcAccountUserEntity userlogin(String username, String password) {
        try {
            CbcAccountUserEntity userEntity =  cbcAccountUserDao.userlogin(username,password);
            return userEntity;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Author: wanghanbin
     * @Description: 通过账户id查询用户id
     * @Date: 11:01 2021/4/5
     * @Param: [acc_user_id]
     * @return: java.lang.String
     **/
    @Override
    public Integer findUserId(Integer acc_user_id) {

        Integer userId = null;
        try {
            userId =  cbcAccountUserDao.findUserId(acc_user_id);
            return userId;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer findUserNameAlready(String accUserName) {
        try {
            return cbcAccountUserDao.findUserNameAlready(accUserName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer findUserName(String accUserName) {
        try {
            return cbcAccountUserDao.findUserName(accUserName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Author: wanghanbin
     * @Description: 修改密码
     * @Date: 11:05 2021/4/19
     * @Param: [accUserName, accUserPwd]
     * @return: java.lang.Integer
     **/
    @Override
    public Integer updatePassword(String accUserName, String accUserPwd) {
        try {
            return cbcAccountUserDao.updatePassword(accUserName, accUserPwd);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Author: wanghanbin
     * @Description: 密码验证
     * @Date: 11:48 2021/4/19
     * @Param: [accUserName]
     * @return: java.lang.String
     **/
    @Override
    public String infoPassword(String accUserName) {
        try {
            return cbcAccountUserDao.infoPassword(accUserName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}