package com.whb.cloud.controller.account;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.whb.cloud.dao.score.CbcScoreDao;
import com.whb.cloud.entity.account.CbcAccountUserEntity;
import com.whb.cloud.entity.info.CbcUserEntity;
import com.whb.cloud.service.account.CbcAccountUserService;
import com.whb.cloud.service.info.CbcUserService;
import com.whb.cloud.utils.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;


/**
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-03-09 10:07:22
 */
@CrossOrigin
@RestController
@RequestMapping("/cbcaccountuser")
@Api(tags = "用户账户相关API")
public class CbcAccountUserController {

    private static Logger logger = Logger.getLogger(CbcAccountAdminController.class);

    public static HashMap<String, String> map = new HashMap<String, String>();

    @Autowired
    private CbcAccountUserService cbcAccountUserService;

    @Autowired
    private CbcUserService cbcUserService;




    /**
     * @Author: wanghanbin
     * @Description: 用户登录
     * @Date: 0:54 2021/3/14
     * @Param: [username, password]
     * @return: com.whb.cloud.utils.Result
     **/

    @RequestMapping("/login")
    @ApiOperation(value = "登录", notes = "登录", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "username", dataType = "String", required = true, value = "用户账户"),
            @ApiImplicitParam(paramType = "query", name = "password", dataType = "String", required = true, value = "用户密码"),
            @ApiImplicitParam(paramType = "query", name = "ver", dataType = "String", required = true, value = "验证码")
    })
    public Result userlogin(@RequestParam String username, @RequestParam String password, @RequestParam String ver) {

        CbcAccountUserEntity userAccount = null;

        if (StringUtils.isNotBlank(ver)) {
            //获取验证码
            String code = map.get(ver.toLowerCase());
            //判断验证码是否正确
            if (StringUtils.isNotBlank(code) && ver.equalsIgnoreCase(code)) {
                try {
                    //查询账号密码
                    userAccount = cbcAccountUserService.userlogin(username, password);
                    if (userAccount != null) {
                        //通过账号查询管理员id
                        Integer userId = cbcAccountUserService.findUserId(userAccount.getAccUserId());
                        String token = JWT.sign(userAccount, ConfigUtils.getValueInt("jwt_max_age"));
                        userAccount.setToken(token);//将加密的信息存入对象中并发送到前端
                        return Result.success(userAccount, "登录成功", userId);
                    } else {//错误的
                        return Result.fail("帐号或密码错误");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.error(e.getMessage());
                }
            } else {
                return Result.fail("验证码有误");
            }
        }
        return null;
    }


    /**
     * 列表
     */
    @RequestMapping("/list")
    @ApiOperation(value = "查询账户列表", notes = "分页查询", httpMethod = "GET")
    public Result list(@RequestParam Map<String, Object> params) {
        PageUtils page = cbcAccountUserService.queryPage(params);
        return Result.success().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{accUserId}")
    @ApiOperation(value = "单条数据查询", notes = "通过accUserId来查询", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "accUserId", dataType = "Int", required = true, value = "用户账户ID")
    })
    public Result info(@PathVariable("accUserId") Integer accUserId) {
        CbcAccountUserEntity cbcAccountUser = null;
        try {
            cbcAccountUser = cbcAccountUserService.getById(accUserId);
            return Result.success().put("cbcAccountUser", cbcAccountUser);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * @Author: wanghanbin
     * @Description: 密码验证
     * @Date: 11:46 2021/4/19
     * @Param: [accUserName]
     * @return: com.whb.cloud.utils.Result
     **/
    @RequestMapping("/infoByName/{accUserName}")
    @ApiOperation(value = "密码验证", notes = "通过acc_user_name来验证", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "accUserName", dataType = "String", required = true, value = "用户账户")
    })
    public Result infoByName(@PathVariable("accUserName") String accUserName) {
        String accUserPwd = null;
        try {
            accUserPwd = cbcAccountUserService.infoPassword(accUserName);
            return Result.success(accUserPwd);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return Result.fail();
    }




    /**
     * 保存
     */
    @RequestMapping("/save")
    public Result save(@RequestBody CbcAccountUserEntity cbcAccountUser) {
        cbcAccountUserService.save(cbcAccountUser);

        return Result.success();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public Result update(@RequestBody CbcAccountUserEntity cbcAccountUser) {
        cbcAccountUserService.updateById(cbcAccountUser);

        return Result.success();
    }

    /**
     * @Author: wanghanbin
     * @Description: 修改密码
     * @Date: 11:32 2021/4/19
     * @Param: [accUserName, accUserPwd]
     * @return: com.whb.cloud.utils.Result
     **/
    @ApiOperation( value = "修改密码" ,notes = "通过账户修改密码",httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "accUserName",required = true,dataType = "String",value = "账户"),
            @ApiImplicitParam(paramType = "query",name = "accUserPwd",required = true,dataType = "String",value = "密码")
    })
    @RequestMapping("/updatePassword")
    public Result updatePassword(@RequestParam String accUserName,@RequestParam String accUserPwd) {
        try {
            cbcAccountUserService.updatePassword(accUserName, accUserPwd);
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return Result.fail();
    }


    /**
     * @Author: wanghanbin
     * @Description: 用户注册
     * @Date: 20:56 2021/5/23
     * @Param: [accUserName]
     * @return: com.whb.cloud.utils.Result
     **/
    @ApiOperation( value = "用户注册" ,notes = "用户注册",httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "accUserName",required = true,dataType = "String",value = "账号")
    })
    @RequestMapping("/register")
    public Result updatePassword(@RequestParam String accUserName) {

        CbcAccountUserEntity cbcAccountUser = new CbcAccountUserEntity();
        CbcUserEntity cbcUser = new CbcUserEntity();
        int acc_user_id = 0;

        try {
            if(StringUtils.isNotBlank(accUserName)){
                acc_user_id = cbcAccountUserService.findUserNameAlready(accUserName);
                if (acc_user_id == 0){
                    //添加用户账户表
                    cbcAccountUser.setAccUserName(accUserName);
                    cbcAccountUser.setAccUserPwd("123456");
                    cbcAccountUser.setAccUserState(1);
                    cbcAccountUserService.save(cbcAccountUser);

                    //添加用户信息表
                    cbcUser.setAccUserId(cbcAccountUserService.findUserName(accUserName));
                    cbcUser.setUserName("存不存用户"+cbcAccountUserService.findUserName(accUserName));
                    cbcUser.setUserPhone(accUserName);
                    cbcUser.setUserEmail("***********@qq.com");
                    cbcUser.setUserMoney(0);
                    cbcUser.setUserScore(0);
                    cbcUserService.save(cbcUser);

                    return Result.success("注册成功！初始密码：123456",1);

                }else {
                    return Result.fail("手机号码已被使用");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return Result.fail();
    }


    /**
     * 删除
     */
    @RequestMapping("/delete")
    public Result delete(@RequestBody Integer[] accUserIds) {
        cbcAccountUserService.removeByIds(Arrays.asList(accUserIds));

        return Result.success();
    }


    /**
     * @Author: wanghanbin
     * @Description: 获取验证码
     * @Date: 14:42
     * @Param: [response]
     * @return: com.whb.cloud.utils.Result
     **/
    @RequestMapping("/image")
    @ApiOperation(value = "获取验证码", notes = "获取验证码", httpMethod = "GET")
    public Result image(HttpServletResponse response) throws IOException {

        String verifyCode = randomNumber();
        //生成图片
        int w = 200, h = 80;
        try {
            System.out.println("imageCode： " + verifyCode.toLowerCase());
            VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public HashMap<String, String> imageCode(String verifyCode) {
        map.put(verifyCode.toLowerCase(), verifyCode.toLowerCase());
        return map;
    }

    /**
     * 生成随机验证码并产生图片
     *
     * @return
     */
    public String randomNumber() {
        //生成随机字串
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
        map.put(verifyCode.toLowerCase(), verifyCode);
        return verifyCode;
    }


}
