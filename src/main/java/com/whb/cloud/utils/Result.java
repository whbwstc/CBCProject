package com.whb.cloud.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回数据
 *
 * @author cshnefu
 * @date 2021年3月9日10:35:23
 */

public class Result extends HashMap<String, Object> {
	private static final long serialVersionUID = 1L;

	//成功标志
	public static final int SUCCESS=0;
	//失败标志
	public static final int FAIL=1;
	//未登录
	public static final int NOT_LOGIN=2;

	public static final int LOCK=3;

	//状态
	int code;
	//数据
	Object data;
	//消息
	String msg;
	//扩展参数
	Object arg1;

	/**
	 * 清空
	 */
	public void reset(){
		this.code=-1;
		this.data=null;
		this.msg=null;
	}

	/**
	 * 返回成功标志
	 */
	public static Result success(){
		return success1(SUCCESS);
	}

	/**
	 * 返回成功标志
	 * @param data 数据
	 */
	public static Result success(Object data){
		return  success1(SUCCESS,data);
	}

	/**
	 * 返回成功标志
	 * @param data 数据
	 * @param msg 消息
	 */
	public static Result success(String msg,Object data){
		return  success1(SUCCESS,msg,data);
	}



	/**
	 * 返回成功标志
	 * @param data 数据
	 * @param msg 消息
	 * @param arg1 参数
	 */
	public static Result success(Object data,String msg,Object arg1){
		return  success1(SUCCESS,data,msg,arg1);
	}

	/**
	 * 返回错误标志
	 */
	public static Result fail() {
		return fail1(FAIL);
	}

	/**
	 * 返回错误标志
	 * @param msg 错误消息
	 */
	public static Result fail(String msg) {
		return fail1(FAIL, msg);
	}



	public static Result fail1(int code) {
		Result r = new Result();
		r.put("code", code);
		return r;
	}

	public static Result fail1(int code, String msg) {
		Result r = new Result();
		r.put("code", code);
		r.put("msg", msg);
		return r;
	}

	public static Result success1(int code) {
		Result r = new Result();
		r.put("code", code);
		return r;
	}

	public static Result success1(int code,String msg) {
		Result r = new Result();
		r.put("code", code);
		r.put("msg",msg);
		return r;
	}

	public static Result success1(int code, Object data) {
		Result r = new Result();
		r.put("code", code);
		r.put("data",data);
		return r;
	}

	public static Result success1(Map<String, Object> map) {
		Result r = new Result();
		r.putAll(map);
		return r;
	}
	public static Result success1(int code, String msg,Object data) {
		Result r = new Result();
		r.put("code", code);
		r.put("msg", msg);
		r.put("data",data);
		return r;
	}

	public static Result success1(int code, Object data, String msg, Object arg1) {
		Result r = new Result();
		r.put("code", code);
		r.put("msg", msg);
		r.put("data",data);
		r.put("arg1",arg1);
		return r;
	}


	@Override
	public Result put(String key, Object value) {
		super.put(key, value);
		return this;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Object getArg1() {
		return arg1;
	}

	public void setArg1(Object arg1) {
		this.arg1 = arg1;
	}
}
