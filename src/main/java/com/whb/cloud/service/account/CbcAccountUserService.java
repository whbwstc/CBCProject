package com.whb.cloud.service.account;

import com.baomidou.mybatisplus.extension.service.IService;
import com.whb.cloud.entity.account.CbcAccountAdminEntity;
import com.whb.cloud.entity.account.CbcAccountUserEntity;
import com.whb.cloud.utils.PageUtils;
import io.swagger.models.auth.In;

import java.util.Map;

/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-03-09 10:07:22
 */
public interface CbcAccountUserService extends IService<CbcAccountUserEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * @Description: 登录
     * @Param: [username, password]
     **/
    CbcAccountUserEntity userlogin(String username, String password);

    /**
     * @Author: cshnefu
     * @Description: 通过账户id查询用户id
     * @Date: 10:57 2021/4/5
     * @Param: [email]
     * @return: java.lang.String
     **/
    Integer findUserId(Integer acc_user_id);

    /**
     * @Author: cshnefu
     * @Description: 查询该手机号码是否被使用
     * @Date: 21:15 2021/5/23
     * @Param: [accUserName]
     * @return: java.lang.Integer
     **/
    Integer findUserNameAlready(String accUserName);

    /**
     * @Author: cshnefu
     * @Description: 查询该手机号码是否被使用（查询id）
     * @Date: 21:15 2021/5/23
     * @Param: [accUserName]
     * @return: java.lang.Integer
     **/
    Integer findUserName(String accUserName);

    /**
     * @Author: cshnefu
     * @Description: 修改密码
     * @Date: 11:05 2021/4/19
     * @Param: [accUserName, accUserPwd]
     * @return: java.lang.Integer
     **/
    Integer updatePassword(String accUserName,String accUserPwd);

    /**
     * @Author: cshnefu
     * @Description: 密码验证
     * @Date: 11:46 2021/4/19
     * @Param: [accUserName]
     * @return: java.lang.String
     **/
    String infoPassword(String accUserName);
}

