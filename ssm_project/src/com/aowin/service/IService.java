package com.aowin.service;


import com.aowin.model.User;

public interface IService {
	/**添加用户方法*/
	public int addUserService(User user);
	/**添加查询电话号码是否重复*/
	public boolean havePhoneService(String userphone);
	/**查询身份证是否重复*/
	public boolean havaIdcardService(String useridcard);
	/**用户登录*/
	public boolean userLoginService(String userphone,String password);
	/**查询用户信息（返回用户信息）*/
	public User  userMsgService(String userphone);
	/**修改用户信息(不能修改手机号码)*/
	public boolean modifyuserMsgService(User user);
	/**修改用户手机号码*/
	public boolean modifyuserphoneService(int userid);
	/**根据手机号码 查找内置id*/
	public int selectuseridService(String userphone);
	/**用户报名比赛*/
	public void enterForGameService(String userphone);
	/**用户参加战队*/
	public void userJoinTeamService(User user);
}
