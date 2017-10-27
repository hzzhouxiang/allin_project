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
	//查询身份证是否重复
	public boolean havaIdcard(String useridcard);
	//查询用户信息
	public User userMsg(String userphone); 
	//修改用户信息(不能修改手机号码)
	public boolean modifyuserMsg(User user);
	//修改用户手机号码
	public boolean modifyuserphone(int userid);
	//根据手机号码 查找内置id
	public int selectuserId(String userphone);
	//用户报名参赛
	public void enterForGame(String userphone);
	//用户参加战队
	public void userJoinTeam(User user);
	
}
