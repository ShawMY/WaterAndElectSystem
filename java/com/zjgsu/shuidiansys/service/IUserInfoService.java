package com.zjgsu.shuidiansys.service;

import com.zjgsu.shuidiansys.common.PageParam;
import com.zjgsu.shuidiansys.pojo.RESTful.*;

public interface IUserInfoService {

    public GetUserInfoListRes getUserListByPage(PageParam pageParam);

    public QueryUserInfoRes getUserListByQuery(QueryUserInfoReq userInfoReq, PageParam pageParam);

    public AddUserInfoRes addUserInfo(AddUserInfoReq userInfoReq);

    public ChangeUserInfoRes changeUserInfo(ChangeUserInfoReq userInfoReq);

    public DeleteUserInfoRes deleteUserInfo(Id[] ids);

    public GetMeterIdByTypeRes getMeterIdByType(String type);
}
