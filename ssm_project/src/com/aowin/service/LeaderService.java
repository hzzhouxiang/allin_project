package com.aowin.service;

import javax.annotation.Resource;

import com.aowin.dao.LeaderMapper;
//import com.aowin.dao.LeaderRoleMapper;
import com.aowin.model.Leader;

public class LeaderService implements ILeaderService {

	@Resource
	private LeaderMapper leaderMapper;
	
	public LeaderMapper getLeaderMapper() {
		return leaderMapper;
	}

	public void setLeaderMapper(LeaderMapper leaderMapper) {
		this.leaderMapper = leaderMapper;
	}
	
	
	//添加队长方法
	@Override
	public int addLeaderService(Leader leader) {

		int leaderNum = leaderMapper.addLeader(leader);
		return leaderNum;
	}



	//添加查询重复电话号码方法,有则返回true
	@Override
	public boolean haveLeaderPhoneService(String leaderphone) {
		boolean haveleaderphone = leaderMapper.haveLeaderPhone(leaderphone);
		return haveleaderphone;
	}
	


	//查询队长信息
	@Override
	public Leader LeaderMsgService(String leaderphone) {
		Leader leadermsg = leaderMapper.LeaderMsg(leaderphone);
		return leadermsg;
	}

	//修改队长信息（不能修改电话号码）
	@Override
	public boolean modifyLeaderMsgService(String Leaderphone) {
		boolean modifyLeaderMsg = leaderMapper.modifyLeaderMsg(Leaderphone);
		
		return modifyLeaderMsg;
	}

	//修改队长手机号码
	@Override
	public boolean modifyLeaderphoneService(int Leaderid) {
		boolean modifyLeaderphone = leaderMapper.modifyLeaderphone(Leaderid);
		return modifyLeaderphone;
	}

	//根据手机号码 查找内置id 用于队长修改手机号
	@Override
	public int selectLeaderidService(String Leaderphone) {
		int Leaderid = leaderMapper.selectLeaderId(Leaderphone);
		return Leaderid;
	}

	//删除队长
	public boolean delLeaderService(String leaderphone) {
		boolean delLeader = leaderMapper.delLeader(leaderphone);
		return delLeader;
	}

	/*根据队伍id查询队长信息*/
	public Leader TidSLeaderService(int teamid) {
		Leader leader = leaderMapper.TidSLeader(teamid);
		return leader;
	}

	/*修改队长信息中的 战队id与战队名*/
	public boolean modifyLeaderTidService(int teamid, String teamname, String leaderphone) {
		boolean mLeaderTid = leaderMapper.modifyLeaderTid(teamid, teamname, leaderphone);
		return mLeaderTid;
	}


	

}
