package com.zjgsu.shuidiansys.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.zjgsu.shuidiansys.pojo.RESTful.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import com.zjgsu.shuidiansys.service.IUserAccountService;

//Tested

@Controller
public class UserAccountController {
	
	@Resource
	private IUserAccountService accountService;


	@ResponseBody
	@RequestMapping(value = "/api/authorization" ,method = {RequestMethod.POST})
	public LoginRes doLogin(@RequestBody LoginReq user, HttpServletResponse response) {

		LoginRes result = accountService.doLogin(user);
		

		return result;
		
	}

	@ResponseBody
	@RequestMapping(value = "/api/account" ,method = {RequestMethod.GET})
	public GetUserAccountListRes doLogin() {

		return accountService.getUserAccountList();

	}

	@ResponseBody
	@RequestMapping(value = "/api/account/add" ,method = {RequestMethod.POST})
	public AddUserAccountRes addUserAccount(@RequestBody AddUserAccountReq addUserAccountReq) {

		return accountService.addUserAccount(addUserAccountReq);

	}

	@ResponseBody
	@RequestMapping(value = "/api/account" ,method = {RequestMethod.DELETE})
	public DeleteRes deleteUserAccount(@RequestBody DeleteOneReq id) {

		return accountService.delete(id.getAccount());

	}

	@ResponseBody
	@RequestMapping(value = "/api/account/chg" ,method = {RequestMethod.POST})
	public ChangeRes changeUserAccount(@RequestBody ChangeUserAccountReq req) {

		return accountService.changeUserAccount(req);

	}

	@ResponseBody
	@RequestMapping(value = "/api/chgPwd" ,method = {RequestMethod.POST})
	public ChangeRes changUserPwd(@RequestBody ChangeAccountPwdReq req) {

		return accountService.changePwd(req);

	}

	@ResponseBody
	@RequestMapping(value = "/api/account/reset" ,method = {RequestMethod.POST})
	public ChangeRes resetPwd(@RequestBody ResetReq account) {

		return accountService.resetPwd(account.getAccount());

	}
}
