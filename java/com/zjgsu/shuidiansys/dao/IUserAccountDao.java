package com.zjgsu.shuidiansys.dao;

import com.zjgsu.shuidiansys.pojo.UserAccount;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IUserAccountDao {
	
	public UserAccount selectByAccount(String account);

	public List<UserAccount> selectAll();

	public int delete(@Param("id") String id);

	public int update(UserAccount user);

	public int insert(UserAccount user);





	
}
