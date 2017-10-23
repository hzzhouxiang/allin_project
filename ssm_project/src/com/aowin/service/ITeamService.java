package com.aowin.service;

import com.aowin.model.Team;

public interface ITeamService {
	//添加战队
	public int addTeamService(Team team);
	//查询队长的电话号码是否重复
	public boolean haveleaderPhoneService(String leaderphone);
	//查询战队信息
	public Team teamMsgService(String leaderphone); 
	//根据战队内置id查找战队信息
	public Team idSteamMsgService(int teamid);
	//修改战队信息
	public boolean modifyteamMsgService(Team team);
	//删除战队信息
	public boolean deleteteamMsgService(String leaderphone);
	
}
