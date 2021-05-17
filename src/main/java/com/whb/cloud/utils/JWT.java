package com.whb.cloud.utils;
import com.auth0.jwt.JWTSigner;
import com.auth0.jwt.JWTVerifier;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

/**
 * JWT验证工具包
 */
public class JWT {

    //密钥
    private static final String SECRET = "hanbinisshuaige";
    //key
    private static final String EXP = "exp";//expire
    //key
    private static final String PAYLOAD = "payload";

    /**
     * get jwt String of object 取得签名
     * @param object
     *            the POJO object 存放的对象
     * @param maxAge
     *            the milliseconds of life time  生命周期
     * @return the jwt token
     */
    public static <T> String sign(T object, long maxAge) {
        try {
            //创建签名对象
            final JWTSigner signer = new JWTSigner(SECRET);
            //创建一个载体对象
            final Map<String, Object> claims = new HashMap<String, Object>();
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(object);
            //载荷数据【存放我们需要的对象】
            claims.put(PAYLOAD, jsonString);
            //设置有效期
            claims.put(EXP, System.currentTimeMillis() + maxAge);
            return signer.sign(claims);
        } catch(Exception e) {
            return null;
        }
    }


    /**
     * 解开签名
     * get the object of jwt if not expired
     * @param jwt
     * @return POJO object
     */
    public static<T> T unsign(String jwt, Class<T> classT) {
        //创建验证对象
        final JWTVerifier verifier = new JWTVerifier(SECRET);
        try {
            //解析出内容
            final Map<String,Object> claims= verifier.verify(jwt);
            if (claims.containsKey(EXP) && claims.containsKey(PAYLOAD)) {
                //找到超时时间
                long exp = (Long)claims.get(EXP);
                long currentTimeMillis = System.currentTimeMillis();
                //是否过期 如果大于当前时间则失效
                if (exp > currentTimeMillis) {
                    //取得载体
                    String json = (String)claims.get(PAYLOAD);
                    //返回载体数据
                    ObjectMapper objectMapper = new ObjectMapper();
                    return objectMapper.readValue(json, classT);
                }
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }
}
