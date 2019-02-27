package com.zjgsu.shuidiansys.service.impl;

import javax.annotation.Resource;

import com.zjgsu.shuidiansys.pojo.RESTful.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjgsu.shuidiansys.dao.IUserAccountDao;
import com.zjgsu.shuidiansys.pojo.UserAccount;
import com.zjgsu.shuidiansys.pojo.RESTful.LoginRes.Content;
import com.zjgsu.shuidiansys.service.IUserAccountService;
import com.zjgsu.shuidiansys.common.ConstantString;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserAccountServiceImpl implements IUserAccountService{

	@Resource
	IUserAccountDao accountDao;
	
	public LoginRes doLogin(LoginReq user) {
		
		
		UserAccount userAccount = accountDao.selectByAccount(user.getUsername());
		LoginRes userRes = new LoginRes();
		if(userAccount!=null&&userAccount.getPassword().equals(user.getPassword())){
			userRes.setCode(ConstantString.LOGIN_SUCCESS);
			userRes.setMsg("登陆成功");
			Content content = userRes.new Content();
			content.setType(userAccount.getPermission());
			userRes.setContent(content);
		}else if(userAccount==null) {
			userRes.setCode(ConstantString.LOGIN_FAILED);
			userRes.setMsg("账号不存在");
		}else if(!userAccount.getPassword().equals(user.getPassword())) {
			userRes.setCode(ConstantString.LOGIN_FAILED);
			userRes.setMsg("密码错误");
		}
		return userRes;
	}

	public GetUserAccountListRes getUserAccountList() {

		List<UserAccount> userAccountList = accountDao.selectAll();
		GetUserAccountListRes userAccountListRes = new GetUserAccountListRes();
		List<GetUserAccountListRes.Content> contents = new ArrayList<GetUserAccountListRes.Content>();
		for(UserAccount one:userAccountList){
			GetUserAccountListRes.Content content = userAccountListRes.new Content();
			content.setAccount(one.getAccount());
			content.setRole(String.valueOf(one.getPermission()));
			contents.add(content);
		}
		userAccountListRes.setContent(contents);

		return userAccountListRes;
	}

	public DeleteRes delete(String id) {
		int deleteFlag = accountDao.delete(id);
		DeleteRes deleteRes = new DeleteRes();
		if(deleteFlag==0){
			deleteRes.setCode(ConstantString.DELETE_FAILED);
			deleteRes.setMsg("删除失败");
		}else{
			deleteRes.setCode(ConstantString.DELETE_SUCCESS);
			deleteRes.setMsg("删除成功");
		}
		return deleteRes;
	}

	public ChangeRes changeUserAccount(ChangeUserAccountReq user) {
		ChangeRes changeRes = new ChangeRes();
		UserAccount userAccount = new UserAccount();
		userAccount.setAccount(user.getAccount());
		userAccount.setPermission(Integer.valueOf(user.getRole()));
		int flag = accountDao.update(userAccount);
		if(flag == 0){
			changeRes.setCode(ConstantString.UPDATE_FAILED);
			changeRes.setMsg("更新失败");
		}else{
			changeRes.setCode(ConstantString.UPDATE_SUCCESS);
			changeRes.setMsg("更新成功");
		}
		return changeRes;
	}

	public ChangeRes changePwd(ChangeAccountPwdReq chgpwd) {
		ChangeRes changeRes = new ChangeRes();
		UserAccount userAccount = new UserAccount();
		UserAccount user = accountDao.selectByAccount(chgpwd.getAccount());
		int updateFlag = 0;
		if(user.getPassword().equals(chgpwd.getPwd())){
			userAccount.setAccount(chgpwd.getAccount());
			userAccount.setPassword(chgpwd.getNewPwd());
			updateFlag = accountDao.update(userAccount);
		}


		if(updateFlag == 0){
			changeRes.setCode(ConstantString.UPDATE_FAILED);
			changeRes.setMsg("更新失败");
		}else{
			changeRes.setCode(ConstantString.UPDATE_SUCCESS);
			changeRes.setMsg("更新成功");
		}
		return changeRes;
	}

	public AddUserAccountRes addUserAccount(AddUserAccountReq userAccountReq) {
		UserAccount user = new UserAccount();
		user.setAccount(userAccountReq.getAccount());
		user.setPassword(userAccountReq.getPassword());
		user.setPermission(Integer.valueOf(userAccountReq.getRole()));
		int flag = accountDao.insert(user);
		AddUserAccountRes userAccountRes = new AddUserAccountRes();
		if(flag == 0){
			userAccountRes.setCode(ConstantString.INSERT_FAILED);
			userAccountRes.setMsg("更新失败");
		}else{
			userAccountRes.setCode(ConstantString.INSERT_SUCCESS);
			userAccountRes.setMsg("更新成功");
		}
		return userAccountRes;
	}

	public ChangeRes resetPwd(String account){
		ChangeRes changeRes = new ChangeRes();

		UserAccount userAccount = new UserAccount();

		userAccount.setAccount(account);
		userAccount.setPassword(ConstantString.ORIGIN_PWD);
		int updateFlag = accountDao.update(userAccount);
		if(updateFlag == 0){
			changeRes.setCode(ConstantString.UPDATE_FAILED);
			changeRes.setMsg("更新失败");
		}else{
			changeRes.setCode(ConstantString.UPDATE_SUCCESS);
			changeRes.setMsg("更新成功");
		}
		return changeRes;

	}


}
