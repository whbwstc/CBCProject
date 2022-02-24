package com.whb.cloud.controller.account;

import com.whb.cloud.entity.account.CbcAccountAdminEntity;
import com.whb.cloud.service.account.CbcAccountAdminService;
import com.whb.cloud.utils.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: cshnefu
 * @Date: 22:41 2021/3/13
 **/

@CrossOrigin
@RestController
@RequestMapping("/cbcaccountadmin")
@Api(tags = "管理员账户相关API")
public class CbcAccountAdminController {

    @Autowired
    private CbcAccountAdminService cbcAccountAdminService;

    private static Logger logger = Logger.getLogger(CbcAccountAdminController.class);

    public static HashMap<String,String> map = new HashMap<String,String>();

    /**
     * @Author: cshnefu
     * @Description: 管理员登录
     * @Date: 0:54 2021/3/14
     * @Param: [username, password]
     * @return: com.whb.cloud.utils.Result
     **/

    @ApiOperation(value = "登录", notes = "登录", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "username", dataType = "String", required = true, value = "管理员账户"),
            @ApiImplicitParam(paramType = "query", name = "password", dataType = "String", required = true, value = "管理员密码")
    })
    @RequestMapping("/login")
    public Result adminlogin(@RequestParam String username,@RequestParam String password) {

        CbcAccountAdminEntity adminAccount = null;
        try {
            //查询账号密码
            adminAccount = cbcAccountAdminService.adminlogin(username, password);
            //通过账号查询管理员id
            String  admin_id = cbcAccountAdminService.findAdminIdByEmail(username);

            if (adminAccount != null) {
                String token = JWT.sign(adminAccount, 86400000);
                adminAccount.setToken(token);//将加密的信息存入对象中并发送到前端
                return Result.success(adminAccount, "登录成功", admin_id);
            } else {//错误的
                return Result.fail("帐号或密码错误");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }


    /**
     * 列表
     * 2021年5月17日16:46:23
     */
    @ApiOperation(value = "查询账户列表", notes = "分页查询", httpMethod = "GET")
    @RequestMapping("/list")
    public Result list(@RequestParam Map<String, Object> params) {
        PageUtils page = null;
        try {
            page = cbcAccountAdminService.queryPage(params);
            return Result.success().put("page", page);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }


    /**
     * 信息
     */
    @ApiOperation(value = "单条数据查询", notes = "通过accAdId来查询", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "accAdId", dataType = "Int", required = true, value = "管理员账户ID")
    })
    @RequestMapping("/info/{accAdId}")
    public Result info(@PathVariable("accAdId") Integer accAdId) {
        CbcAccountAdminEntity cbcAccountAdmin = null;
        try {
            cbcAccountAdmin = cbcAccountAdminService.getById(accAdId);
            return Result.success().put("cbcAccountAdmin", cbcAccountAdmin);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 保存
     */
    @ApiOperation(value = "新增", notes = "新增管理员账户", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", name = "cbcAccountAdmin", dataType = "body", required = true, value = "管理员账户")
    })
    @RequestMapping("/save")
    public Result save(@RequestBody CbcAccountAdminEntity cbcAccountAdmin) {

        try {
            cbcAccountAdminService.save(cbcAccountAdmin);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Result.success();
    }

    /**
     * 修改
     */
    @ApiOperation(value = "更新账户", notes = "更新账户", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", name = "cbcAccountAdmin", dataType = "body", required = true, value = "管理员账户")
    })
    @RequestMapping("/update")
    public Result update(@RequestBody CbcAccountAdminEntity cbcAccountAdmin) {
        cbcAccountAdminService.updateById(cbcAccountAdmin);
        return Result.success();
    }

    /**
     * 删除
     */

    @ApiOperation(value = "删除账户", notes = "删除账户", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", name = "accAdIds", dataType = "body", required = true, value = "管理员账户ID")
    })
    @RequestMapping("/delete")
    public Result delete(@RequestBody Integer[] accAdIds) {
        cbcAccountAdminService.removeByIds(Arrays.asList(accAdIds));

        return Result.success();
    }


    /**
     * @Author: cshnefu
     * @Description: 获取验证码
     * @Date: 14:42
     * @Param: [response]
     * @return: com.whb.cloud.utils.Result
     **/
    @ApiOperation(value = "获取验证码", notes = "获取验证码", httpMethod = "GET")
    @RequestMapping("/image")
    public Result image(HttpServletResponse response) throws IOException {

        String verifyCode = randomNumber();
        //生成图片
        int w = 200, h = 80;
        try {
            VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 生成随机验证码并产生图片
     * @return
     */
    public String randomNumber(){
        //生成随机字串
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
        map.put(verifyCode.toLowerCase(),verifyCode);
        return verifyCode;
    }

}
