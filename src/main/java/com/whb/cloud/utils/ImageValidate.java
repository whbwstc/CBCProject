package com.whb.cloud.utils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * 生成图片验证码
 * 并将验证码的字符串 作为字符串rand存到session中
 * @author admin
 */
@WebServlet("/image.do")
public class ImageValidate extends HttpServlet {

   public static HashMap<String,String> map = new HashMap<String,String>();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //清除网页缓存
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");

        String verifyCode = randomNumber();

        System.out.println("imageCode： "+verifyCode.toLowerCase());
        //生成图片
        int w = 200, h = 80;
        VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode);
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    public HashMap<String,String> imageCode(String verifyCode){
        map.put(verifyCode.toLowerCase(),verifyCode.toLowerCase());
        return map;
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
