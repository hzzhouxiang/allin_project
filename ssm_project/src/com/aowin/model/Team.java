package com.aowin.model;

public class Team {

	private String leadername;
	private String teamname; 
	private String leaderphone;
	
	
	//三个不能为空的值
	public Team(String leadername,String teamname,String leaderphone){
		super();
		this.leadername=leadername;
		this.teamname = teamname;
		this.leaderphone = leaderphone;
	}
	
	@Override
	public String toString() {
		return "Team战队信息： [leadername=" + leadername + ", teamname=" + teamname + ", leaderphone=" + leaderphone
				 + "]";
	}
	public String getLeadername() {
		return leadername;
	}
	public void setLeadername(String leadername) {
		this.leadername = leadername;
	}
	public String getTeamname() {
		return teamname;
	}
	public void setTeamname(String teamname) {
		this.teamname = teamname;
	}
	public String getLeaderphone() {
		return leaderphone;
	}
	public void setLeaderphone(String leaderphone) {
		this.leaderphone = leaderphone;
	}

	
	
}
