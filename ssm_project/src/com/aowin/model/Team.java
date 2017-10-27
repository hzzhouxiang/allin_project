package com.aowin.model;

public class Team {

	private String leadername;
	private String teamname; 
	private String leaderphone;
	private String teampoints;
	private String coachname;
	//三个不能为空的值
	public Team(){
		super();
	}
	public Team(String leadername,String teamname,String leaderphone){
		super();
		this.leadername=leadername;
		this.teamname = teamname;
		this.leaderphone = leaderphone;
	}
	

	@Override
	public String toString() {
		return "Team [leadername=" + leadername + ", teamname=" + teamname + ", leaderphone=" + leaderphone
				+ ", teampoints=" + teampoints + ", coachname=" + coachname + "]";
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

	public String getTeampoints() {
		return teampoints;
	}

	public void setTeampoints(String teampoints) {
		this.teampoints = teampoints;
	}

	public String getCoachname() {
		return coachname;
	}

	public void setCoachname(String coachname) {
		this.coachname = coachname;
	}

	
	
}
