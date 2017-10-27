package com.aowin.service;

import java.util.List;

import com.aowin.model.Member;

public interface IMemberService {
	/**添加战队成员*/
	public int addMemberService(Member member);
	/**查询战队成员电话号码是否重复*/
	public boolean haveMPhoneService(String memberphone);
	/**查询战队成员信息*/
	public Member memberMsgService(String memberphone); 
	/**修改战队成员信息*/
	public boolean modifyMMsgService(Member member);
	/**批量修改战队成员信息*/
	public boolean modifyMsMsgService(List<Member> listM);
	/**根据战队成员手机号查找所在战队id*/
	public int mphoneSTidService(String memberphone);
	/**删除战队成员信息*/
	public boolean delMMsgService(String memberphone);
	/**根据战队内置id 查找出同个队伍的所有成员list*/
	public List<Member> queryAllMembersService(int teamid);
	/**该用户是否是战队成员*/
	public boolean userIsMService(String userphone);
}
