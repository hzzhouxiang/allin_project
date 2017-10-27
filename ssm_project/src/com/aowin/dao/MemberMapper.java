package com.aowin.dao;

import java.util.List;

import com.aowin.model.Member;

public interface MemberMapper {
	//添加战队成员
	public int addMember(Member member);
	//查询战队成员电话号码是否重复
	public boolean haveMPhone(String memberphone);
	//查询战队成员信息
	public Member memberMsg(String memberphone); 
	//修改战队成员信息
	public boolean modifyMMsg(Member member);
	//批量修改战队成员信息
	public boolean modifyMsMsg(List<Member> listM);
	//根据战队成员手机号查找所在战队id
	public int mphoneSTid(String memberphone);
	//删除战队成员信息
	public boolean delMMsg(String memberphone);
	//根据战队内置id 查找出同个队伍的所有成员list
	public List<Member> queryAllMembers(int teamid);
	/*该用户是否是战队成员*/
	public boolean userIsM(String userphone);
}
