package com.zjgsu.shuidiansys.service;

import com.zjgsu.shuidiansys.pojo.RESTful.*;

public interface IUserAccountService {
	
	public LoginRes doLogin(LoginReq user);

	public GetUserAccountListRes getUserAccountList();

	public DeleteRes delete(String id);

	public ChangeRes changeUserAccount(ChangeUserAccountReq user);

	public ChangeRes changePwd(ChangeAccountPwdReq chgpwd);

	public AddUserAccountRes addUserAccount(AddUserAccountReq userAccountReq);

	public ChangeRes resetPwd(String account);
}
