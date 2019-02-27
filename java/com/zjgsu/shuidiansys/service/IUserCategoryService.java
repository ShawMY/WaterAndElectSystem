package com.zjgsu.shuidiansys.service;

import com.zjgsu.shuidiansys.common.PageParam;
import com.zjgsu.shuidiansys.pojo.RESTful.AddUserCategoryReq;
import com.zjgsu.shuidiansys.pojo.RESTful.ChangeUserCategoryReq;
import com.zjgsu.shuidiansys.pojo.RESTful.GetAllUserCatListRes;
import com.zjgsu.shuidiansys.pojo.UserCategory;
import com.zjgsu.shuidiansys.pojo.RESTful.GetUserCatListRes;

import java.util.List;

public interface IUserCategoryService {

	public GetUserCatListRes getUserCategoryList(PageParam pageParam);

	public boolean updateUserCategory(ChangeUserCategoryReq categoryReq);

	public String deleteUserCategory(List<Integer> ids);

	public UserCategory addUserCategory(AddUserCategoryReq categoryReq);

	public GetAllUserCatListRes getAllUserCatList();

}
