package com.aowin.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSONObject;
import com.aowin.model.AjaxResult;
import com.aowin.model.Leader;
import com.aowin.model.Member;
import com.aowin.model.Team;
import com.aowin.model.User;
import com.aowin.service.ILeaderService;
import com.aowin.service.IMemberService;
import com.aowin.service.IService;
import com.aowin.service.ITeamService;
import com.aowin.service.UserService;
import com.aowin.support.sms.SDKTestSendTemplateSMS;
import com.aowin.utils.IdNumberUtils;
import com.aowin.utils.JsonUtil;
import com.aowin.utils.MobileUtils;

@Controller
public class UserController {
	//@Resource
	//private IService iService;
	@Autowired
	IService iService;
	@Autowired
	ITeamService iTeamService;
	@Autowired
	IMemberService iMemberService;
	@Autowired
	ILeaderService iLeaderervice;
	
	//接收index.do
	@RequestMapping(value="index.do", produces = "text/html;charset=UTF-8")
	public String addUser(User user,HttpServletRequest request) {
		return "redirect:/index.jsp";
	}
	
	//测试JSON格式
	@RequestMapping(value="test.do", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String test(HttpServletResponse response){
		//return webUtils.responseJson(response,new AjaxResult(false, "错误信息"));
		//return new AjaxResult(false, "错误信息");
		//return JSONObject.toJSONString(new AjaxResult(false, "错误信息"));
		return JsonUtil.formatToStr(new AjaxResult(false, "信息："));
	}
	

	//判断用户注册时 验证码是否正确，是否能调用短信接口
	@RequestMapping(value="verificationcode.do")
	public @ResponseBody AjaxResult VerificationCode(String userphone, String vcode,HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(session.getAttribute("rand")==null||StringUtils.equalsIgnoreCase(session.getAttribute("rand").toString(), vcode)==false)
		{	
			AjaxResult  result = new AjaxResult(false, "验证码错误！");  
            return result;  
		}
		
		if(StringUtils.isBlank(userphone)||MobileUtils.isPhoneLegal(userphone)==false){
			AjaxResult  result = new AjaxResult(false, "手机号码有误！");  
            return result;  
		}
		if(iService.havePhoneService(userphone)){
			AjaxResult  result = new AjaxResult(false, "该手机已经完成注册，不可重复注册！");  
            return result;  
		}
		AjaxResult  result = new AjaxResult(true, "验证码正确");  
		return result;
	}
	
	//判断队长验证时 验证码是否正确，是否能调用短信接口
	@RequestMapping(value="leadervcode.do")
	public @ResponseBody AjaxResult LeaderVCode(String leaderphone, String vcode,HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(session.getAttribute("rand")==null||StringUtils.equalsIgnoreCase(session.getAttribute("rand").toString(), vcode)==false)
		{	
			AjaxResult  result = new AjaxResult(false, "验证码错误！");  
            return result;  
		}
		
		if(StringUtils.isBlank(leaderphone)||MobileUtils.isPhoneLegal(leaderphone)==false){
			AjaxResult  result = new AjaxResult(false, "手机号码格式有误！");  
            return result;  
		}
		
		if(iLeaderervice.haveLeaderPhoneService(leaderphone)){
			AjaxResult  result = new AjaxResult(false, "该手机号已经绑定过队长！");  
            return result;  
		}
		
		AjaxResult  result = new AjaxResult(true, "验证码正确");  
		return result;
	}
	
	/*使用短信接口 ——新注册用户 */
	@RequestMapping(value="usersendmsg.do")
	public @ResponseBody AjaxResult UserSendMsg(User user,HttpServletRequest request) {
		if(StringUtils.isBlank(user.getUserphone())||MobileUtils.isPhoneLegal(user.getUserphone())==false){
			AjaxResult  result = new AjaxResult(false, "手机号码有误！");  
            return result;  
		}
		//生成6位数短信验证码
		String phonevcode = null;
		Random random = new Random();
		for (int i=0;i<6;i++){
			String rand=String.valueOf(random.nextInt(10));
			if(i==0){
				phonevcode=rand;
			}else{
			phonevcode+=rand;
			}
			}
		SDKTestSendTemplateSMS.sendMsg(phonevcode,user.getUserphone());
		HttpSession session = request.getSession();
		session.setAttribute("phonevcode_"+user.getUserphone(), phonevcode);
		AjaxResult  result = new AjaxResult(true, "短信验证码已发送");  
		return result;
	}
	
	/*使用短信接口——新注册队长*/
	@RequestMapping(value="leadersendmsg.do")
	public @ResponseBody AjaxResult LeaderSendMsg(Leader leader,HttpServletRequest request) {
		if(StringUtils.isBlank(leader.getLeaderphone())||MobileUtils.isPhoneLegal(leader.getLeaderphone())==false){
			AjaxResult  result = new AjaxResult(false, "手机号码格式有误！");  
            return result;  
		}
		if(iService.havePhoneService(leader.getLeaderphone())==false){
			AjaxResult  result = new AjaxResult(false, "请先注册会员再操作！");  
            return result;  
		}
		//生成6位数短信验证码
		String phonevcode = null;
		Random random = new Random();
		for (int i=0;i<6;i++){
			String rand=String.valueOf(random.nextInt(10));
			if(i==0){
				phonevcode=rand;
			}else{
			phonevcode+=rand;
			}
			}
		SDKTestSendTemplateSMS.sendMsg(phonevcode,leader.getLeaderphone());
		HttpSession session = request.getSession();
		session.setAttribute("leadervcode"+leader.getLeaderphone(), phonevcode);
		AjaxResult  result = new AjaxResult(true, "短信验证码已发送");  
		return result;
	}
	
	/*用户报名参赛*/
	@RequestMapping(value="userentryforgame.do")
	public @ResponseBody AjaxResult UserEntryForGame(User user,HttpServletRequest request) {
		if(StringUtils.isBlank(user.getUserphone())){
			AjaxResult  result = new AjaxResult(false, "请登录后再参加报名");  
			return result;
		}
		user=iService.userMsgService(user.getUserphone());
		if(StringUtils.isBlank(user.getUsername())||StringUtils.isBlank(user.getUsergameid())||StringUtils.isBlank(user.getUseridcard())){
			AjaxResult  result = new AjaxResult(false, "请先完善个人资料再参加报名");  
			return result;
		}
		if(iService.userMsgService(user.getUserphone()).getSituation()==0)
		{
			AjaxResult  result = new AjaxResult(true, "报名成功"); 
			iService.enterForGameService(user.getUserphone());
            return result;  
		}
		AjaxResult  result = new AjaxResult(false, "报名失败,已参加过报名！");  
		return result;
	}
	
	/*用户注册*/
	@RequestMapping(value="adduser.do")
	//ResponseBody此注解不能省略 否则ajax无法接受返回值  
	public @ResponseBody AjaxResult AddUser(User user,String phonevcode,HttpServletRequest request) {
		
		//打桩查看输入的user信息
		System.out.println(user);
		String userphone = user.getUserphone();
		System.out.println("电话号码是否重复："+iService.havePhoneService(userphone));
		if(StringUtils.isBlank(user.getPassword())||StringUtils.isBlank(user.getUserphone())){
			AjaxResult  result = new AjaxResult(false, "手机号或密码不能为空！");  
            return result;  
		}
		if(StringUtils.isBlank(userphone)||MobileUtils.isPhoneLegal(userphone)==false){
			AjaxResult  result = new AjaxResult(false, "手机号码格式有误！");  
            return result;  
		}
		if(iService.havePhoneService(userphone)){
			AjaxResult  result = new AjaxResult(false, "该号码已被注册！");  
            return result;  
		}
		HttpSession session = request.getSession();
		if(session.getAttribute("phonevcode_"+user.getUserphone())==null){
			AjaxResult  result = new AjaxResult(false, "取不到内置验证码！");  
            return result;  
		}
		String phonecode = session.getAttribute("phonevcode_"+user.getUserphone()).toString();
		if(StringUtils.isBlank(phonevcode)||StringUtils.equals(phonevcode,phonecode)==false){
			AjaxResult  result = new AjaxResult(false, "手机验证码输入错误！");  
            return result;  
		}
		
		AjaxResult  result = new AjaxResult(true, "注册成功");  
		//将用户信息添加到数据库
		iService.addUserService(user);
		return result;
	}

	/*用户登陆*/
	@RequestMapping(value="login.do")
	public @ResponseBody AjaxResult LoginUser(User user,HttpServletRequest request) {
		System.out.println("查看想登陆的用户信息："+user);
		
		if(StringUtils.isBlank(user.getPassword())||StringUtils.isBlank(user.getUserphone())){
			AjaxResult  result = new AjaxResult(false, "手机号或密码不能为空！");  
            return result;  
		}
		
		//检查手机号与密码是否存在
		boolean success=iService.userLoginService(user.getUserphone(), user.getPassword());
		if(success==false){
			AjaxResult  result = new AjaxResult(false, "手机号或密码错误！");  
            return result;  
		}
  		user=iService.userMsgService(user.getUserphone());
		HttpSession session = request.getSession();
		//将用户存入session
		session.setAttribute("user",user);
		//该用户是否是队长
		if(iLeaderervice.haveLeaderPhoneService(user.getUserphone())){
			Leader leader = iLeaderervice.LeaderMsgService(user.getUserphone());
			session.setAttribute("leader",leader);
		}
		AjaxResult result = new AjaxResult(true, "登陆成功");  
		return result;
	}
	
	/*用户查看自己信息页面（用户后台）*/
	@RequestMapping(value="usermsg.do")
	public String UserMsg(User user,HttpServletRequest request) {
		HttpSession session = request.getSession();
		user = (User)session.getAttribute("user");
		if(user==null||StringUtils.isBlank(user.getUserphone())){
			return "redirect:/index.do";
		}
		if(iMemberService.userIsMService(user.getUserphone())==false){
			return "redirect:/usermsg.jsp";
		}
		//获取所在战队的战队id
		int teamid = iMemberService.mphoneSTidService(user.getUserphone());
		//获取所在战队的信息
		Team team = iTeamService.idSteamMsgService(teamid);
		//保存该用户所在的战队信息
		session.setAttribute("team", team);
		//获取该用户的队长信息
		Leader leader = iLeaderervice.TidSLeaderService(teamid);
		session.setAttribute("leader",leader);
		//获取同个战队的所有成员信息
		List<Member> listM = iMemberService.queryAllMembersService(teamid);
		//保存该用户的战队队员信息  list格式保存
		session.setAttribute("member", listM);
		return "redirect:/usermsg.jsp";
	}	
	

	//修改（完善）用户信息
	@RequestMapping(value="modifyuser.do")
	public @ResponseBody AjaxResult ModifyUser(User user) {
		
		if(StringUtils.isBlank(user.getUsername())){
			AjaxResult result = new AjaxResult(false, "姓名不能为空！");  
            return result;  
		}
		if(StringUtils.isBlank(user.getUsergameid())){
			AjaxResult result = new AjaxResult(false, "游戏id不能为空！");  
            return result;  
		}
		if(StringUtils.isBlank(user.getUseridcard())){
			AjaxResult  result = new AjaxResult(false, "身份证不能为空！");  
            return result;  
		}
		if(iService.havaIdcardService(user.getUseridcard())){
			AjaxResult  result = new AjaxResult(false, "该身份证已被使用！");  
            return result;  
		}
		if(IdNumberUtils.strongVerifyIdNumber((user.getUseridcard()))==false){
			AjaxResult  result = new AjaxResult(false, "身份证格式有误！");  
            return result;  
		}
		if(StringUtils.isBlank(user.getUsergameid())){
			AjaxResult  result = new AjaxResult(false, "游戏id不能为空！");  
            return result;  
		}
		AjaxResult  result = new AjaxResult(true, "信息完善成功");  
		//将用户信息添加到数据库
		boolean success=iService.modifyuserMsgService(user);
		
		System.out.println("是否修改成功："+success);
		return result;
	}
	
	//用户成为队长（新建队长）
	@RequestMapping(value="addleader.do")
		//ResponseBody此注解不能省略 否则ajax无法接受返回值  
	public @ResponseBody AjaxResult AddLeader(User user,Leader leader,String leadervcode,HttpServletRequest request) {
			String leaderphone = leader.getLeaderphone();
			HttpSession session = request.getSession();
			user = (User) session.getAttribute("user");
		if(StringUtils.isBlank(leader.getLeadername())){
			AjaxResult  result = new AjaxResult(false, "队长姓名不能为空！");  
	        return result;  
		}
		if(StringUtils.isBlank(leaderphone)){
			AjaxResult  result = new AjaxResult(false, "队长手机号不能为空！");  
	           return result;  
		}
		if(user==null||StringUtils.isBlank(user.getUsername())||leader.getLeadername().equals(user.getUsername())==false){
			AjaxResult  result = new AjaxResult(false, "姓名与用户名不匹配！");  
			return result;  
		}			
		if(iLeaderervice.haveLeaderPhoneService(leaderphone)){
			AjaxResult  result = new AjaxResult(false, "该号码已经是队长，不可重复绑定！");  
	        return result;  
		}
		if(leaderphone.equals(user.getUserphone())==false){
			AjaxResult  result = new AjaxResult(false, "队长手机号必须是自己账号的号码！");  
	        return result;  
		}
		if(StringUtils.isBlank(leader.getLeadergameid())){
			AjaxResult  result = new AjaxResult(false, "游戏id不能为空！");  
	        return result;  
		}
		if(leader.getLeadergameid().equals(user.getUsergameid())==false){
			AjaxResult  result = new AjaxResult(false, "绑定的游戏id与用户的游戏id不匹配！");  
	        return result;  
		}
		if(StringUtils.isBlank(leader.getLeadergender())){
			AjaxResult  result = new AjaxResult(false, "性别不能为空！");  
	        return result;  
		}
		if(StringUtils.isBlank(leader.getLeaderrole())){
			AjaxResult  result = new AjaxResult(false, "服务器不能为空！");  
	        return result;  
		}
		if(StringUtils.equals(user.getUserrole(),leader.getLeaderrole())==false){
			AjaxResult  result = new AjaxResult(false, "填写服务器与账号所在服务器不一致！");  
	        return result;  
		}
		if(leader.getLeaderidcard().equals(user.getUseridcard())==false){
			AjaxResult  result = new AjaxResult(false, "身份证与用户的身份证不匹配！");  
	        return result;  
		}
		if(session.getAttribute("leadervcode"+leader.getLeaderphone())==null||session.getAttribute("leadervcode"+leader.getLeaderphone()).toString().equals(leadervcode)==false){
			AjaxResult  result = new AjaxResult(false, "手机验证码错误！");  
	        return result; 
		}
		AjaxResult  result = new AjaxResult(true, "绑定队长成功");  
		session.setAttribute("newleader",leader);
		//将用户信息添加到数据库
		iLeaderervice.addLeaderService(leader);
		return result;
	}
	
	//队长创建战队（新建战队）
	@RequestMapping(value="addteam.do")
	//ResponseBody此注解不能省略 否则ajax无法接受返回值  
	public @ResponseBody AjaxResult AddTeam(String teamname,Leader leader,HttpServletRequest request) {
		HttpSession session = request.getSession();
		leader = (Leader) session.getAttribute("leader");
		if(leader==null||StringUtils.isBlank(leader.getLeaderphone())||iLeaderervice.haveLeaderPhoneService(leader.getLeaderphone())==false){
			AjaxResult  result = new AjaxResult(false, "请先完成队长绑定！");  
            return result;  
		}
		String leaderphone = leader.getLeaderphone();
	
		if(StringUtils.isBlank(teamname)){
			AjaxResult  result = new AjaxResult(false, "战队名不能为空！");  
            return result;  
		}
		if(iTeamService.haveTnameService(teamname)){
			AjaxResult  result = new AjaxResult(false, "战队名已存在！");  
            return result;  
		}
		if(iTeamService.haveleaderPhoneService(leaderphone)){
			AjaxResult  result = new AjaxResult(false, "该队长已经拥有战队！");  
            return result;  
		}
		
		Team team = new Team(leader.getLeadername(), teamname, leaderphone);
		//将战队信息添加到数据库
		iTeamService.addTeamService(team);
		//将队长加入到战队成员表（职责为队长
		Member member = new Member(leaderphone, leader.getLeadername(), leader.getLeadergameid(), "队长", iTeamService.tnameStidService(teamname), teamname,leader.getLeadergender(), leader.getLeaderidcard(), leader.getLeaderrole());
		iMemberService.addMemberService(member);
		//在队长表中加入队伍名与队伍id
		int teamid = iTeamService.tnameStidService(teamname);
		iLeaderervice.modifyLeaderTidService(teamid, teamname, leaderphone);
		
		AjaxResult  result = new AjaxResult(true, "创建战队成功");  
		return result;
	}
	
	//队长添加新的战队成员（新增战队成员）
	@RequestMapping(value="addmember.do")
	//ResponseBody此注解不能省略 否则ajax无法接受返回值  
	public @ResponseBody AjaxResult AddMember(Member member,String teamname,Leader leader,HttpServletRequest request) {
	//	HttpSession session = request.getSession();
		String leaderphone = leader.getLeaderphone();
		if(iLeaderervice.haveLeaderPhoneService(leaderphone)==false){
			AjaxResult  result = new AjaxResult(false, "请先完成队长绑定！");  
            return result;  
		}
		if(iTeamService.haveTnameService(teamname)){
			AjaxResult  result = new AjaxResult(false, "请先完成战队确认！");  
            return result;  
		}
		if(iService.havePhoneService(member.getMemberphone())==false){
			AjaxResult  result = new AjaxResult(false, "该号码的队员尚未完成注册！");  
            return result;  
		}
		if(IdNumberUtils.strongVerifyIdNumber((member.getMemberidcard()))==false){
			AjaxResult  result = new AjaxResult(false, "身份证格式有误！");  
            return result;  
		}
		
		member.setTeamid(iTeamService.tnameStidService(teamname));
		member.setTeamname(teamname);
		//需要从页面传一个成员职则（如：教练、队员1、队员2、经理）
		//member.setMemberduty();
		//将战队成员的信息添加到数据库
		iMemberService.addMemberService(member);
		
		AjaxResult  result = new AjaxResult(true, "新建战队成员成功");  
		return result;
	}
	
	//队长修改战队成员信息,返回查看信息页面
	@RequestMapping(value="modifymembers.do")
	public @ResponseBody AjaxResult ModifyMembers(String teamname,Leader leader,List<Member> listM,HttpServletRequest request){
		HttpSession session = request.getSession();
		String leaderphone = leader.getLeaderphone();
		if(iLeaderervice.haveLeaderPhoneService(leaderphone)==false){
			AjaxResult  result = new AjaxResult(false, "请先完成队长绑定！");  
            return result;  
		}
		if(iTeamService.haveTnameService(teamname)){
			AjaxResult  result = new AjaxResult(false, "请先完成战队确认！");  
            return result;  
		}
		for(int i=0;i<listM.size();i++){
		if(iService.havePhoneService(listM.get(i).getMemberphone())==false){
			AjaxResult  result = new AjaxResult(false, "该号码:"+listM.get(i).getMemberphone()+"的队员尚未完成注册！");  
            return result;  
		}
		if(IdNumberUtils.strongVerifyIdNumber((listM.get(i).getMemberidcard()))==false){
			AjaxResult  result = new AjaxResult(false, "身份证格式有误！");  
            return result;  
		}
		}
		
		iMemberService.modifyMsMsgService(listM);
		AjaxResult  result = new AjaxResult(true, "修改队员信息成功");  
		return result;
		
	}
	
	//删除战队
		@RequestMapping(value="delTeam.do")
		//ResponseBody此注解不能省略 否则ajax无法接受返回值  
		public @ResponseBody AjaxResult DelTeam(String leaderphone) {
			//将战队成员的信息添加到数据库
			iTeamService.deleteteamMsgService(leaderphone);
			AjaxResult  result = new AjaxResult(true, "成功删除该战队");  
			return result;
		}	
	
	//队长删除战队成员（删除战队成员）
		@RequestMapping(value="delMember.do")
		//ResponseBody此注解不能省略 否则ajax无法接受返回值  
		public @ResponseBody AjaxResult DelMember(String memberphone) {
			//将战队成员的信息添加到数据库
			iMemberService.delMMsgService(memberphone);
			AjaxResult  result = new AjaxResult(true, "成功删除该战队成员");  
			return result;
		}	
	
	//删除队长
		@RequestMapping(value="delLeader.do")
		//ResponseBody此注解不能省略 否则ajax无法接受返回值  
		public @ResponseBody AjaxResult DelLeader(String leaderphone) {
			//将战队成员的信息添加到数据库
			iLeaderervice.delLeaderService(leaderphone);
			AjaxResult  result = new AjaxResult(true, "成功删除该战队成员");  
			return result;
		}	
		
//	@RequestMapping(value="adduser.do",method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
//	@ResponseBody//此注解不能省略 否则ajax无法接受返回值  
//	public void addUser(User user,HttpServletRequest request,HttpServletResponse response,final RedirectAttributes redirectAttributes) {
//		HttpSession session = request.getSession();
//		//打桩查看输入的user信息
//		System.out.println(user);
//		String userphone = user.getUserphone();
//		System.out.println("电话号码是否重复："+iService.havePhoneService(userphone));
//		//判断电话号码是否重复,如果为真，则返回注册 页面
//		try {
//		if(iService.havePhoneService(userphone)){
//			session.setAttribute("regist_failed","该号码已被注册");
//			//使用addFlashAttribute,参数不会出现在url地址栏中,
//			//session.removeAttribute("org.springframework.web.servlet.support.SessionFlashMapManager.FLASH_MAPS");
//			//redirectAttributes.addFlashAttribute("regist_failed", "该号码已被注册");  
//			 //RequestDispatcher rd = request.getRequestDispatcher("adduser.jsp");
//			 //转发(参数是请求对象和响应对象)
//			 //rd.forward(request,response);
//			response.sendRedirect("adduser.jsp");
//	         
//		}else{
//		//将用户信息添加到数据库
//		iService.addUserService(user);
//		//上述操作完成后页面重定向index.jsp
//		response.sendRedirect("index.do");
//		
//		}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
	
	
	

	
}
