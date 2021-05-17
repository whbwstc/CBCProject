package com.whb.cloud.controller.order;

import java.text.SimpleDateFormat;
import java.util.*;

import com.whb.cloud.entity.info.CbcUserEntity;
import com.whb.cloud.entity.judge.CbcJudgeEntity;
import com.whb.cloud.entity.order.CbcOrderEntity;
import com.whb.cloud.entity.score.CbcScoreEntity;
import com.whb.cloud.entity.store.CbcStoreEntity;
import com.whb.cloud.service.info.CbcUserService;
import com.whb.cloud.service.judge.CbcJudgeService;
import com.whb.cloud.service.order.CbcOrderService;
import com.whb.cloud.service.score.CbcScoreService;
import com.whb.cloud.service.store.CbcStoreService;
import com.whb.cloud.utils.PageUtils;
import com.whb.cloud.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-03-09 10:07:22
 */
@CrossOrigin
@RestController
@RequestMapping("/cbcorder")
@Api(tags = "订单信息相关API")
public class CbcOrderController {


    private static Logger logger = Logger.getLogger(CbcOrderController.class);

    @Autowired
    private CbcOrderService cbcOrderService;

    @Autowired
    private CbcStoreService cbcStoreService;

    @Autowired
    private CbcUserService cbcUserService;

    @Autowired
    private CbcJudgeService cbcJudgeService;

    @Autowired
    private CbcScoreService cbcScoreService;


    /**
     * @Author: wanghanbin
     * @Description: 
     * @Date: 13:55 列表分页查询
     * @Param: [params] 
     * @return: com.whb.cloud.utils.Result
     **/
    @ApiOperation(value = "列表分页查询", notes = "列表分页查询", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "page", dataType = "Integer", required = true, value = "页码"),
            @ApiImplicitParam(paramType = "query", name = "limit",dataType = "Integer", required = true, value = "每页数量")
    })
    @RequestMapping("/list")
    public Result list(@RequestParam Integer page,@RequestParam Integer limit){
        //起始行
        Integer startRow = null;
        //查询获取数据
        List<Map<String,String>> list = null;
        Integer count = null;
        try {
            startRow = (page-1)*limit;
            list = cbcOrderService.findOrderAll(startRow,limit);
            //获取数据总数
            count = cbcOrderService.count();
            return Result.success(list,"",count);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return null;
    }


    /**
     * @Author: wanghanbin
     * @Description: 寄存点订单分页查询
     * @Date: 14:17 2021/5/1
     * @Param: [page, limit, store_id]
     * @return: com.whb.cloud.utils.Result
     **/
    @ApiOperation(value = "寄存点订单分页查询",notes = "寄存点订单分页查询",httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "page", dataType = "Integer", required = true, value = "页码"),
            @ApiImplicitParam(paramType = "query", name = "limit",dataType = "Integer", required = true, value = "每页数量"),
            @ApiImplicitParam(paramType = "query",name = "store_id",dataType = "Integer",required = true,value = "寄存点ID")
    })
    @RequestMapping("/storeOrder")
    public Result storeOrder(@RequestParam Integer page,@RequestParam Integer limit,@RequestParam Integer store_id){
        //起始行
        Integer startRow = null;
        //查询获取数据
        List<Map<String,String>> list = null;
        Integer count = null;
        try {
            startRow = (page-1)*limit;
            list = cbcOrderService.getStoreOrderById(startRow, limit, store_id);
            //获取数据总数
            count = cbcOrderService.countStoreOrder(store_id);
            return Result.success(list,"",count);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return null;
    }

    
    /**
     * @Author: wanghanbin
     * @Description: 用户寄存点订单查询
     * @Date: 14:18 2021/5/1
     * @Param: [store_id, order_code] 
     * @return: com.whb.cloud.utils.Result
     **/
    @ApiOperation(value = "用户寄存点订单查询",notes = "用户寄存点订单查询",httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "store_id",dataType = "Integer",required = false,value = "寄存点ID"),
            @ApiImplicitParam(paramType = "query",name = "order_code",dataType = "String",required = false,value = "订单编号")
    })
    @RequestMapping("/userStoreOrder")
    public Result userStoreOrder(@RequestParam Integer store_id,@RequestParam String order_code){
        //查询获取数据
        List<Map<String,String>> list = null;
        try {
            list = cbcOrderService.getUserStoreOrderById(store_id,order_code);
            return Result.success(list);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return null;
    }


    /**
     * @Author: wanghanbin
     * @Description: 用户订单分页查询
     * @Date: 14:19 2021/5/1
     * @Param: [page, limit, user_id] 
     * @return: com.whb.cloud.utils.Result
     **/
    @ApiOperation(value = "用户订单分页查询",notes = "用户订单分页查询",httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "page", dataType = "Integer", required = true, value = "页码"),
            @ApiImplicitParam(paramType = "query", name = "limit",dataType = "Integer", required = true, value = "每页数量"),
            @ApiImplicitParam(paramType = "query",name = "user_id",dataType = "Integer",required = true,value = "用户ID")
    })
    @RequestMapping("/orderInfo")
    public Result userOrder(@RequestParam Integer page,@RequestParam Integer limit,@RequestParam Integer user_id){
        //起始行
        Integer startRow = null;
        //查询获取数据
        List<Map<String,String>> list = null;
        Integer count = null;
        try {
            if (page != null && limit != null){
                //分页查询
                startRow = (page-1)*limit;
                list = cbcOrderService.getUserOrderById(startRow,limit,user_id);
                //获取数据总数
                count = cbcOrderService.countUserOrder(user_id);
                return Result.success(list,"分页查询成功",count);
            }else {
                //不分页查询
                list = cbcOrderService.getUserOrderByIdNoPage(user_id);
                //获取数据总数
                count = cbcOrderService.countUserOrder(user_id);
                return Result.success(list,"不分页查询成功",count);
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return null;
    }


    /**
     * 列表
     */
    @ApiOperation(value = "查询账户列表", notes = "分页查询", httpMethod = "GET")
    @RequestMapping("/zdylist")
    public Result list(@RequestParam Map<String, Object> params) {
        PageUtils page = null;
        try {
            page = cbcOrderService.queryPage(params);
            return Result.success().put("page", page);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }


    /**
     * @Author: wanghanbin
     * @Description:  查询订单状态和评价状态
     * @Date: 17:13 2021/4/15
     * @Param: [order_id]
     * @return: com.whb.cloud.utils.Result
     **/
    @ApiOperation(value = "查询订单状态和评价状态",notes = "查询订单状态和评价状态",httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "order_id", dataType = "Integer", required = true, value = "订单ID")
    })
    @RequestMapping("/orderState/{order_id}")
    public Result orderState(@PathVariable("order_id") Integer order_id){

        Map<String,String> map = null;
        try {
            map = cbcOrderService.getOrderStateById(order_id);
            return Result.success(map);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * @Author: wanghanbin
     * @Description: 订单详细查询
     * @Date: 14:21 2021/5/1
     * @Param: [orderId]
     * @return: com.whb.cloud.utils.Result
     **/
    @ApiOperation(value = "订单详细查询",notes = "订单详细查询",httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "order_id", dataType = "Integer", required = true, value = "订单ID")
    })
    @RequestMapping("/info/{order_id}")
    public Result info(@PathVariable("order_id") Integer order_id){
        Map<String,String> map = null;
        try {
            map = cbcOrderService.getUserOrderInfoById(order_id);
            return Result.success(map);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return null;
    }


    /**
     * @Author: wanghanbin
     * @Description: 新增订单
     * @Date: 22:31 2021/4/27
     * @Param: [cbcOrder]
     * @return: com.whb.cloud.utils.Result
     **/
    @ApiOperation(value = "新增订单", notes = "新增订单", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", name = "cbcOrder", dataType = "body", required = true, value = "订单信息")
    })
    @RequestMapping("/save")
    public Result save(@RequestBody CbcOrderEntity cbcOrder){
        boolean flag = false;
        CbcUserEntity cbcUser = null;
        CbcStoreEntity cbcStore = null;
        CbcJudgeEntity cbcJudge = new CbcJudgeEntity();
        CbcScoreEntity cbcScore = new CbcScoreEntity();
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Integer user_score;
        Integer user_money;
        Integer luggage_num;
        Integer bag_num;
        Integer order_id;
        try {
            //获取该用户的余额
            cbcUser = cbcUserService.getById(cbcOrder.getUserId());

            //判断用户余额是否充足
            if(cbcUser.getUserMoney() >= cbcOrder.getOrderMoney()){
                //新增订单
                flag = cbcOrderService.save(cbcOrder);
                //新增订单成功
                if(flag){
                    //获取当前订单的id
                    order_id = cbcOrderService.getOrderIdByCode(Integer.valueOf(cbcOrder.getOrderCode()));

                    //新增评价信息
                    cbcJudge.setUserId(cbcOrder.getUserId());
                    cbcJudge.setOrderId(order_id);
                    cbcJudge.setJudgeState(1);
                    cbcJudgeService.save(cbcJudge);

                    //新增积分信息
                    cbcScore.setUserId(cbcOrder.getUserId());
                    cbcScore.setOrderId(order_id);
                    cbcScore.setScoreNumber(cbcOrder.getOrderMoney());
                    cbcScore.setScoreTime(sdf.format(date));
                    cbcScoreService.save(cbcScore);

                    //更新用户积分信息
                    user_score = cbcUser.getUserScore()+cbcOrder.getOrderMoney();
                    cbcUserService.updateUserScore(user_score,cbcOrder.getUserId());

                    //用户余额更新
                    user_money = cbcUser.getUserMoney()-cbcOrder.getOrderMoney();
                    cbcUserService.updateUserMoney(user_money,cbcOrder.getUserId());

                    //获取寄存点的剩余数量
                    cbcStore =  cbcStoreService.getById(cbcOrder.getStoreId());
                    luggage_num = cbcStore.getLuggageNum()-cbcOrder.getLuggageNum();
                    bag_num = cbcStore.getBagNum()-cbcOrder.getBagNum();

                    //寄存点背包和行李箱数量更新
                    cbcStoreService.updateSumById(luggage_num,bag_num,cbcOrder.getStoreId());
                    return Result.success(flag);
                }else{
                    return Result.fail("新增订单失败");
                }
            }else {
                return Result.fail("余额不足");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public Result update(@RequestBody CbcOrderEntity cbcOrder){
		cbcOrderService.updateById(cbcOrder);

        return Result. success();
    }



    /**
     * @Author: wanghanbin
     * @Description: 取件
     * @Date: 22:31 2021/4/27
     * @Param: [cbcOrder]
     * @return: com.whb.cloud.utils.Result
     **/
    @ApiOperation(value = "取件",notes = "取件",httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType ="body",name = "cbcOrder",dataType = "body",required = true,value = "订单")
    })
    @RequestMapping("/updateOrderState")
    public Result updateOrderState(@RequestBody CbcOrderEntity cbcOrder){
        CbcStoreEntity cbcStore = null;
        Integer luggage_num = 0;
        Integer bag_num = 0;
        try {
            cbcOrderService.updateOrderState(cbcOrder.getOrderId());
            cbcStore = cbcStoreService.getById(cbcOrder.getStoreId());
            //寄存点背包和行李箱数量更新
            luggage_num = cbcStore.getLuggageNum() + cbcOrder.getLuggageNum();
            bag_num = cbcStore.getBagNum() + cbcOrder.getBagNum();
            cbcStoreService.updateSumById(luggage_num,bag_num,cbcOrder.getStoreId());
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return null;
    }


    /**
     * 删除
     */
    @RequestMapping("/delete")
    public Result delete(@RequestBody Integer[] orderIds){
		cbcOrderService.removeByIds(Arrays.asList(orderIds));

        return Result. success();
    }

    /**
     * @Author: wanghanbin
     * @Description: 计算所需寄存天数
     * @Date: 21:03 2021/4/7
     * @Param: [enter_time1, out_time1]
     * @return: int
     **/
    @ApiOperation(value = "计算所需寄存天数",notes = "计算所需寄存天数",httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "enter_time", dataType = "String",required = true,value = "寄存开始时间"),
            @ApiImplicitParam(paramType = "query",name = "out_time", dataType = "String",required = true,value = "寄存结束时间")
    })
    @RequestMapping("/countDayPC")
    private Result countDayPC(@RequestParam String enter_time,@RequestParam String out_time ) {
        Date enter_time1=null;
        Date out_time1=null;
        //寄存天数差
        int days=0;
        try {
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
            //获取并转换  寄存时间
            enter_time1=sdf.parse(enter_time);
            //获取并转换  取出时间
            out_time1=sdf.parse(out_time);

            Calendar cal=Calendar.getInstance();
            cal.setTime(enter_time1);
            long time1=cal.getTimeInMillis();
            cal.setTime(out_time1);
            long time2=cal.getTimeInMillis();
            if(time1<=time2){
                long between_days=(time2-time1)/(1000*3600*24);
                days=Integer.parseInt(String.valueOf(between_days));
                return Result.success(days);
            }else {
                return Result.fail("寄存时间必须小于取件时间");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }

}
