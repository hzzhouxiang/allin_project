package com.aowin.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSONObject;
import com.aowin.dao.JsonUtil;
import com.aowin.model.AjaxResult;
import com.aowin.model.User;
import com.aowin.service.IService;
import com.aowin.service.UserService;

@Controller
public class UserController {
	@Resource
	private IService iService;
	//接收index.do
	@RequestMapping(value="index.do", produces = "text/html;charset=UTF-8")
	public String addUser(User user) {
		return "redirect:/index.jsp";
	}
	
	//测试JSON格式
	@RequestMapping(value="test.do", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String test(HttpServletResponse response){
		
		//return webUtils.responseJson(response,new AjaxResult(false, "错误信息"));
		//return new AjaxResult(false, "错误信息");
		//return JSONObject.toJSONString(new AjaxResult(false, "错误信息"));
		return JsonUtil.formatToStr(new AjaxResult(false, "错误信息"));
	}
	
	@RequestMapping(value="checkadduser.do")
	//此注解不能省略 否则ajax无法接受返回值  
	public @ResponseBody AjaxResult balancePayment(User user) {
		//打桩查看输入的user信息
		System.out.println(user);
		String userphone = user.getUserphone();
		System.out.println("电话号码是否重复："+iService.havePhoneService(userphone));
		if(StringUtils.isBlank(user.getPassword())||StringUtils.isBlank(user.getUserphone())){
			AjaxResult  result = new AjaxResult(false, "手机号或密码不能为空！");  
            return result;  
		}
		if(iService.havePhoneService(userphone)){
			AjaxResult  result = new AjaxResult(false, "该号码已被注册！");  
            return result;  
		}
		AjaxResult  result = new AjaxResult(true, "注册成功");  
		//将用户信息添加到数据库
		iService.addUserService(user);
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

	
	public IService getiService() {
		return iService;
	}

	public void setiService(IService iService) {
		this.iService = iService;
	}

}
