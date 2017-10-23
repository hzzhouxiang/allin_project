package com.aowin.service;

import java.util.List;

import javax.annotation.Resource;

import com.aowin.dao.MemberMapper;
import com.aowin.model.Member;

public class MemberService implements IMemberService{
	@Resource
	MemberMapper memberMapper;

	public MemberMapper getMemberMapper() {
		return memberMapper;
	}

	public void setMemberMapper(MemberMapper memberMapper) {
		this.memberMapper = memberMapper;
	}
	
	
	//添加战队成员
	public int addMemberService(Member member) {
		int addmember = memberMapper.addMember(member);
		return addmember;
	}

	//查询战队成员电话号码是否重复
	public boolean haveMPhoneService(String memberphone) {
		boolean havemphone = memberMapper.haveMPhone(memberphone);
		return havemphone;
	}

	//查询战队成员信息
	public Member memberMsgService(String memberphone) {
		Member member = memberMapper.memberMsg(memberphone);
		return member;
	}

	//修改战队成员信息
	public boolean modifyMMsgService(Member member) {
		boolean modifyMMsg = memberMapper.modifyMMsg(member);
		return modifyMMsg;
	}

	//根据战队成员手机号查找所在战队id
	public int mphoneSTService(String memberphone) {
		int teamid = memberMapper.mphoneST(memberphone);
		return teamid;
	}

	//删除战队成员信息
	public boolean delMMsgService(String memberphone) {
		boolean delMMsg = memberMapper.delMMsg(memberphone);
		return delMMsg;
	}

	//根据战队内置id 查找出同个队伍的所有成员list
	public List<Member> queryAllMembersService(int teamid) {
		List<Member> listM = memberMapper.queryAllMembers(teamid);
		return listM;
	}

	
	

		
}
