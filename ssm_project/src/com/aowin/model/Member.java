package com.aowin.model;

public class Member {
	private String memberphone;
	private String membername;
	private String membergameid;
	private String memberduty;
	private int teamid;
	private String teamname;
	private String membergender;
	private String memberidcard;
	private String memberrole;
	
	public Member(){
		super();
	}
	public Member(String memberphone){
		super();
		this.memberphone = memberphone;
	}
	@Override
	public String toString() {
		return "战队成员信息： [memberphone=" + memberphone + ", membername=" + membername + ", membergameid=" + membergameid
				+ ", memberduty=" + memberduty + ", teamid=" + teamid + ", teamname=" + teamname + ", membergender="
				+ membergender + ", memberidcard=" + memberidcard + ", memberrole=" + memberrole + "]";
	}
	public String getMemberphone() {
		return memberphone;
	}
	public void setMemberphone(String memberphone) {
		this.memberphone = memberphone;
	}
	public String getMembername() {
		return membername;
	}
	public void setMembername(String membername) {
		this.membername = membername;
	}
	public String getMembergameid() {
		return membergameid;
	}
	public void setMembergameid(String membergameid) {
		this.membergameid = membergameid;
	}
	public String getMemberduty() {
		return memberduty;
	}
	public void setMemberduty(String memberduty) {
		this.memberduty = memberduty;
	}
	public int getTeamid() {
		return teamid;
	}
	public void setTeamid(int teamid) {
		this.teamid = teamid;
	}
	public String getTeamname() {
		return teamname;
	}
	public void setTeamname(String teamname) {
		this.teamname = teamname;
	}
	public String getMembergender() {
		return membergender;
	}
	public void setMembergender(String membergender) {
		this.membergender = membergender;
	}
	public String getMemberidcard() {
		return memberidcard;
	}
	public void setMemberidcard(String memberidcard) {
		this.memberidcard = memberidcard;
	}
	public String getMemberrole() {
		return memberrole;
	}
	public void setMemberrole(String memberrole) {
		this.memberrole = memberrole;
	}
	
}
