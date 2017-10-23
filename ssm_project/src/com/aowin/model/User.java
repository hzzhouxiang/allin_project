package com.aowin.model;

import java.util.List;

public class User {

	private String username;
	private String password;
	private String gender;
	private String userphone;
	private String usergameid;
	private String useridcard;
	private String userrole;
	//是否报名参赛 0表示未报名 1表示报名
	private int situation;
	private String teamname;
	private List<Team> roleList;
	
	public User(){
		super();
	}
	public User(String userphone){
		super();
		this.userphone= userphone;
	}
	public User(String userphone,String password){
		super();
		this.userphone= userphone;
		this.password=password;
	}
	
	public List<Team> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Team> roleList) {
		this.roleList = roleList;
	}
	
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getUserphone() {
		return userphone;
	}

	public void setUserphone(String userphone) {
		this.userphone = userphone;
	}

	public String getUsergameid() {
		return usergameid;
	}

	public void setUsergameid(String usergameid) {
		this.usergameid = usergameid;
	}

	public String getUseridcard() {
		return useridcard;
	}

	public void setUseridcard(String useridcard) {
		this.useridcard = useridcard;
	}

	public String getUserrole() {
		return userrole;
	}

	public void setUserrole(String userrole) {
		this.userrole = userrole;
	}

	public int getSituation() {
		return situation;
	}

	public void setSituation(int situation) {
		this.situation = situation;
	}

	public String getTeamname() {
		return teamname;
	}

	public void setTeamname(String teamname) {
		this.teamname = teamname;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "用户信息查看：User [username=" + username + ", password=" + password + ", gender=" + gender + ", userphone="
				+ userphone + ", usergameid=" + usergameid + ", useridcard=" + useridcard + ", userrole=" + userrole
				+ ", situation=" + situation + ", teamname=" + teamname + ", roleList=" + roleList + "]";
	}

}
