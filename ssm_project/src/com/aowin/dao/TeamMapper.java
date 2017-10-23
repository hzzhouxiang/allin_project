package com.aowin.dao;

import java.util.List;

import com.aowin.model.Team;

public interface TeamMapper {
	//添加战队
	public int addTeam(Team team);
	//查询队长的电话号码是否重复
	public boolean haveleaderPhone(String leaderphone);
	//根据队长手机查询战队信息
	public Team teamMsg(String leaderphone); 
	//根据战队内置id查找战队信息
	public Team idSteamMsg(int teamid);
	//修改战队信息
	public boolean modifyteamMsg(Team team);
	//删除战队信息（按钮）
	public boolean deleteteamMsg(String leaderphone);
	
}
