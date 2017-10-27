package com.aowin.service;

import javax.annotation.Resource;

import com.aowin.dao.UserMapper;
//import com.aowin.dao.UserRoleMapper;
import com.aowin.model.User;

public class UserService implements IService {

	@Resource
	private UserMapper userMapper;
//	@Resource
//	private UserRoleMapper UserRoleMapper;
	
	public UserMapper getUserMapper() {
		return userMapper;
	}

	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}
	
	//添加用户方法
	@Override
	public int addUserService(User user) {

		int userNum = userMapper.addUser(user);

//		String username = user.getUsername();

//		System.out.println("size " + user.getRoleList().size());

//		for (int i = 0; i < user.getRoleList().size(); i++) {
//
//			user.getRoleList().get(i).setRoleId(Integer.valueOf(userId));
//		}
		//int roleNum = UserRoleMapper.addUserRole(user.getRoleList());
		//return userNum + roleNum;
		return userNum;
	}



	//添加查询重复电话号码方法,有则返回true
	@Override
	public boolean havePhoneService(String userphone) {
		boolean havephone = userMapper.havePhone(userphone);
		return havephone;
	}
	
	//查询身份证是否重复 
	public boolean havaIdcardService(String useridcard) {
		boolean haveidcard = userMapper.havaIdcard(useridcard);
		return haveidcard;
	}

	//用户登陆
	@Override
	public boolean userLoginService(String userphone, String password) {
		boolean userlogin = userMapper.userLogin(userphone, password);
		return userlogin;
	}

	//查询用户信息
	@Override
	public User userMsgService(String userphone) {
		User user = userMapper.userMsg(userphone);
		return user;
	}

	//修改用户信息（不能修改电话号码）
	@Override
	public boolean modifyuserMsgService(User user) {
		boolean modifyuserMsg = userMapper.modifyuserMsg(user);
		
		return modifyuserMsg;
	}

	//修改用户手机号码
	@Override
	public boolean modifyuserphoneService(int userid) {
		boolean modifyuserphone = userMapper.modifyuserphone(userid);
		return modifyuserphone;
	}

	//根据手机号码 查找内置id 用于用户修改手机号
	@Override
	public int selectuseridService(String userphone) {
		int userid = userMapper.selectuserId(userphone);
		return userid;
	}

	//用户报名参赛
	@Override
	public void enterForGameService(String userphone) {
		userMapper.enterForGame(userphone);
	}
	
	//用户参加战队
	@Override
	public void userJoinTeamService(User user) {
		userMapper.userJoinTeam(user);
	}



	

}
