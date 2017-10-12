package com.aowin.service;


import com.aowin.model.User;

public interface IService {
	//添加用户方法
	public int addUserService(User user);
	//添加查询电话号码是否重复
	public boolean havePhoneService(String userphone);
	//用户登录
	public boolean userLoginService(String userphone,String password);
	//查询用户信息（返回用户信息）
	public User  userMsgService(String userphone);
	
}
