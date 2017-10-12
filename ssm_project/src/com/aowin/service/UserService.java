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

	public UserMapper getUserMapper() {
		return userMapper;
	}

	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	//添加查询重复电话号码方法,有则返回true
	@Override
	public boolean havePhoneService(String userphone) {
		boolean havephone = userMapper.havePhone(userphone);
		return havephone;
	}

	@Override
	public boolean userLoginService(String userphone, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User userMsgService(String userphone) {
		// TODO Auto-generated method stub
		return null;
	}



}
