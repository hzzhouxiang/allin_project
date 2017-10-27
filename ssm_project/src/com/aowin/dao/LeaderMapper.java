package com.aowin.dao;


import com.aowin.model.Leader;

public interface LeaderMapper {
	//添加队长
	public int addLeader(Leader leader);
	//查询队长电话号码是否重复
	public boolean haveLeaderPhone(String leaderphone);
	//查询队长信息
	public Leader LeaderMsg(String leaderphone); 
	//修改队长信息(不能修改手机号码)
	public boolean modifyLeaderMsg(String leaderphone);
	//修改队长手机号码
	public boolean modifyLeaderphone(int leaderid);
	//根据手机号码 查找内置id
	public int selectLeaderId(String Leaderphone);
	//删除队长信息
	public boolean delLeader(String leaderphone);
	//根据队伍id查询队长信息
	public Leader TidSLeader(int teamid);
	//修改队长信息中的 战队id与战队名
	public boolean modifyLeaderTid(int teamid,String teamname,String leaderphone);
}
