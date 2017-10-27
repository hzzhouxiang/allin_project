package com.aowin.service;

import javax.annotation.Resource;

import com.aowin.dao.TeamMapper;
import com.aowin.model.Team;

public class TeamService implements ITeamService{

	@Resource
	TeamMapper teamMapper;
	
	public TeamMapper getTeamMapper() {
		return teamMapper;
	}
	public void setTeamMapper(TeamMapper teamMapper) {
		this.teamMapper = teamMapper;
	}
		//添加用户方法
		public int addTeamService(Team team) {
			int addTeam =  teamMapper.addTeam(team);
			return addTeam;
		}
		//查询队长的电话号码是否重复
		public boolean haveleaderPhoneService(String leaderphone){
			boolean haveleaderphone = teamMapper.haveleaderPhone(leaderphone);
			return haveleaderphone;
		}
		//查询战队信息
		public Team teamMsgService(String leaderphone){
			Team team = teamMapper.teamMsg(leaderphone);
			return team;
		}
		//修改战队信息
		public boolean modifyteamMsgService(Team team){
			boolean modifyteamMsg = teamMapper.modifyteamMsg(team);
			return modifyteamMsg;
		}
		//删除战队信息
		public boolean deleteteamMsgService(String leaderphone){
			boolean deleteteamMsg = teamMapper.deleteteamMsg(leaderphone);
			return deleteteamMsg;
		}
		//根据战队内置id查找战队信息
		public Team idSteamMsgService(int teamid) {
			Team team = teamMapper.idSteamMsg(teamid);
			return team;
		}
		//查询队伍名是否重复
		public boolean haveTnameService(String teamname) {
			boolean havetname = teamMapper.haveTname(teamname);
			return havetname;
		}
		/*根据战队名称查找战队id*/
		@Override
		public int tnameStidService(String teamname) {
			int teamid = teamMapper.tnameStid(teamname);
			return teamid;
		}
	

		
}
