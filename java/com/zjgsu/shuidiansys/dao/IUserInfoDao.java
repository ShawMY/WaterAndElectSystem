package com.zjgsu.shuidiansys.dao;

import com.zjgsu.shuidiansys.pojo.RESTful.DeleteUserInfoReq;
import com.zjgsu.shuidiansys.pojo.RESTful.Id;
import com.zjgsu.shuidiansys.pojo.UserCategory;
import com.zjgsu.shuidiansys.pojo.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IUserInfoDao {

    public int getRowCountByPage();

    public int getRowCountByQuery(UserInfo userInfo);

    public List<UserInfo> selectByParams(Map<String, Object> params);

    public int updateUserInfo(UserInfo cat);

    public int deleteUserInfo(@Param("Ids") List<Id> ids);

    public int addUserInfo(UserInfo cat);

    public UserInfo selectTopOne();

    public UserInfo selectByName(@Param("name") String name);

    public List<UserInfo> selectByCondition(Map<String, Object> map);

    public UserCategory selectCatByName(String name);

    public UserInfo selectByMeterId(String id);
}
