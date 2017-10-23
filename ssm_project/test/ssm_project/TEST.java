package ssm_project;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.aowin.model.Team;
import com.aowin.model.User;
import com.aowin.service.IMemberService;
import com.aowin.service.IService;
import com.aowin.service.ITeamService;

public class TEST {
	@Autowired
	private ITeamService iTeamService;
	@Autowired
	private IService iService;
	@Autowired
	private IMemberService iMemberService;
	
	
	@Test
	public void test1(){
		Team team = new Team("德萨海鸥","阿拉伯战队","15757180515");
		System.out.println(team);
		iTeamService.addTeamService(team);
	}
	
	@Test
	public void test2(){
		User user = new User("17171717178", "123213a");
		System.out.println(user);
		iService.addUserService(user);
	}

	
	@Test
	public void test3(){
		
		System.out.println(iMemberService.queryAllMembersService(1));
	}


	
	
}
