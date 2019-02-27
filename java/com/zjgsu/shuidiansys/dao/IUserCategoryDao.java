package com.zjgsu.shuidiansys.dao;

import java.util.List;
import java.util.Map;

import com.zjgsu.shuidiansys.pojo.RESTful.AddUserCategoryReq;
import com.zjgsu.shuidiansys.pojo.UserCategory;

public interface IUserCategoryDao {

	public int getRowCount();
	
	public List<UserCategory> selectByParams(Map<String, Object> params);

	public int updateUserCategory(UserCategory cat);

	public int deleteUserCategory(List<Integer> ids);

	public int addUserCategory(UserCategory cat);

	public UserCategory selectById(int id);

	public List<UserCategory> selectAll();

}
