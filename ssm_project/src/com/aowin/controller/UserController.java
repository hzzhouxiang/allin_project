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
import com.aowin.dao.JsonUtil;
import com.aowin.dao.MobileUtils;
import com.aowin.dao.SDKTestSendTemplateSMS;
import com.aowin.model.AjaxResult;
import com.aowin.model.Member;
import com.aowin.model.User;
import com.aowin.service.ILeaderService;
import com.aowin.service.IMemberService;
import com.aowin.service.IService;
import com.aowin.service.ITeamService;
import com.aowin.service.UserService;

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
	

	//判断验证码是否正确，是否能调用短信接口
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
		
		AjaxResult  result = new AjaxResult(true, "验证码正确");  
		return result;
	}
	
	/*使用短信接口*/
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
	
	
	/*用户报名参赛*/
	@RequestMapping(value="userentryforgame.do")
	public @ResponseBody AjaxResult UserEntryForGame(User user,HttpServletRequest request) {
		user.setUserphone("15757180522");
		if(iService.userMsgService(user.getUserphone()).getSituation()==0)
		{
			AjaxResult  result = new AjaxResult(true, "报名成功"); 
			iService.enterForGameService(user.getUserphone());
            return result;  
		}
		AjaxResult  result = new AjaxResult(false, "报名失败,已参加过报名！");  
		return result;
	}
	
	//用户注册
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
	
	/*读取用户信息，跳转到修改(完善)信息界面*/
	@RequestMapping(value="loaduser.do")
	public String LoadUser(User user,HttpServletRequest request){
		HttpSession session = request.getSession();
		session.setAttribute("usermsg"+user.getUserphone(),user);
		return "redirect:/modifyuser.jsp";
	}
	
	//修改用户信息
	@RequestMapping(value="modifyuser.do")
	public @ResponseBody AjaxResult ModifyUser(User user) {
		System.out.println("查看修改后的用户信息："+user);
		
		if(StringUtils.isBlank(user.getPassword())){
			AjaxResult result = new AjaxResult(false, "密码不能为空！");  
            return result;  
		}
		
		AjaxResult  result = new AjaxResult(true, "修改成功");  
		//将用户信息添加到数据库
		boolean success=iService.modifyuserMsgService(user);
		
		System.out.println("是否修改成功："+success);
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
		HttpSession session = request.getSession();
		//将用户手机号存入session
		session.setAttribute("user"+user.getUserphone(),user.getUserphone());
		AjaxResult  result = new AjaxResult(true, "登陆成功");  
		return result;
	}
	
	/*用户查看自己信息页面（用户后台）*/
	@RequestMapping(value="userMsg.do")
	public @ResponseBody AjaxResult UserMsg(User user,HttpServletRequest request) {
		HttpSession session = request.getSession();
		//获取同个战队的队员信息
		int teamid = iMemberService.mphoneSTService(user.getUserphone());
		List<Member> listM = iMemberService.queryAllMembersService(teamid);
		
		AjaxResult  result = new AjaxResult(true, "登陆成功");  
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
