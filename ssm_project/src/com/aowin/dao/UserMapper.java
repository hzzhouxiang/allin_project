package com.aowin.dao;

import java.util.List;

import com.aowin.model.User;

public interface UserMapper {
	//添加用户
	public int addUser(User user);
	//查询电话号码是否重复
	public boolean havePhone(String userphone);
	//用户登陆@param(“userName”)Stringname,@param(“userArea”)String area
	public boolean userLogin(String userphone,String password);
	//查询用户信息
	public User userMsg(String userphone); 
	//修改用户信息
	public User modifyuserMsg(String userphone);
	
}
