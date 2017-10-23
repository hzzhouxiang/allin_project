package com.aowin.model;


public class Leader {

	private String leaderphone;
	private String leaderidcard;
	private String leadername;
	private String leadergender;
	//游戏角色所在大区
	private String leaderrole;
	//游戏id
	private String leadergameid;
	@Override
	public String toString() {
		return "Leader队长信息： [leaderphone=" + leaderphone + ", leaderidcard=" + leaderidcard + ", leadername=" + leadername
				+ ", leadergender=" + leadergender + ", leaderrole=" + leaderrole + ", leadergameid=" + leadergameid
				+ "]";
	}
	public String getLeaderphone() {
		return leaderphone;
	}
	public void setLeaderphone(String leaderphone) {
		this.leaderphone = leaderphone;
	}
	public String getLeaderidcard() {
		return leaderidcard;
	}
	public void setLeaderidcard(String leaderidcard) {
		this.leaderidcard = leaderidcard;
	}
	public String getLeadername() {
		return leadername;
	}
	public void setLeadername(String leadername) {
		this.leadername = leadername;
	}
	public String getLeadergender() {
		return leadergender;
	}
	public void setLeadergender(String leadergender) {
		this.leadergender = leadergender;
	}
	public String getLeaderrole() {
		return leaderrole;
	}
	public void setLeaderrole(String leaderrole) {
		this.leaderrole = leaderrole;
	}
	public String getLeadergameid() {
		return leadergameid;
	}
	public void setLeadergameid(String leadergameid) {
		this.leadergameid = leadergameid;
	}
	
	
}
