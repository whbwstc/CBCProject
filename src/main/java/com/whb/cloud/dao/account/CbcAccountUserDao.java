package com.whb.cloud.dao.account;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.whb.cloud.entity.account.CbcAccountAdminEntity;
import com.whb.cloud.entity.account.CbcAccountUserEntity;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-03-09 10:07:22
 */
@Mapper
public interface CbcAccountUserDao extends BaseMapper<CbcAccountUserEntity> {
    /**
     * @Description: 登录
     * @Param: [username, password]
     **/
    CbcAccountUserEntity userlogin(String username, String password);

    /**
     * @Author: wanghanbin
     * @Description: 通过账户id查询用户id
     * @Date: 10:57 2021/4/5
     * @Param: [email]
     * @return: java.lang.String
     **/
    Integer findUserId(Integer acc_user_id);

    /**
     * @Author: wanghanbin
     * @Description: 修改密码
     * @Date: 11:05 2021/4/19
     * @Param: [accUserName, accUserPwd]
     * @return: java.lang.Integer
     **/
    Integer updatePassword(String accUserName,String accUserPwd);

    /**
     * @Author: wanghanbin
     * @Description: 密码验证
     * @Date: 11:46 2021/4/19
     * @Param: [accUserName]
     * @return: java.lang.String
     **/
    String infoPassword(String accUserName);

}
