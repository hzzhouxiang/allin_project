package com.aowin.service;


import com.aowin.model.Leader;

public interface ILeaderService {
	//添加队长方法
	public int addLeaderService(Leader leader);
	//添加查询电话号码是否重复
	public boolean haveLeaderPhoneService(String leaderphone);
	//查询队长信息（返回队长信息）
	public Leader  LeaderMsgService(String leaderphone);
	//修改队长信息(不能修改手机号码)
	public boolean modifyLeaderMsgService(String leaderphone);
	//修改队长手机号码
	public boolean modifyLeaderphoneService(int leaderid);
	//根据手机号码 查找内置id
	public int selectLeaderidService(String leaderphone);
}
