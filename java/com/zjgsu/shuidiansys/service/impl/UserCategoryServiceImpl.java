package com.zjgsu.shuidiansys.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.zjgsu.shuidiansys.common.ConstantString;
import com.zjgsu.shuidiansys.pojo.RESTful.AddUserCategoryReq;
import com.zjgsu.shuidiansys.pojo.RESTful.ChangeUserCategoryReq;
import com.zjgsu.shuidiansys.pojo.RESTful.GetAllUserCatListRes;
import org.springframework.stereotype.Service;


import com.zjgsu.shuidiansys.common.PageParam;
import com.zjgsu.shuidiansys.dao.IUserCategoryDao;
import com.zjgsu.shuidiansys.pojo.UserCategory;
import com.zjgsu.shuidiansys.pojo.RESTful.GetUserCatListRes;
import com.zjgsu.shuidiansys.pojo.RESTful.GetUserCatListRes.Content;
import com.zjgsu.shuidiansys.service.IUserCategoryService;

@Service
public class UserCategoryServiceImpl implements IUserCategoryService{

	@Resource
	IUserCategoryDao dao;
	
	public int getRowCount() {
		return dao.getRowCount();
	}
	
	public GetUserCatListRes getUserCategoryList(PageParam pageParam) {
		int currPage = pageParam.getCurrPage();
		pageParam.setRowCount(getRowCount());
		// limit offset, size
		int offset = (currPage - 1) * PageParam.pageSize ; //起始条数
		int size = PageParam.pageSize;
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("offset", offset);
		params.put("size", size);
		
		List<UserCategory> categoryList = dao.selectByParams(params);


		GetUserCatListRes userCatListRes = new GetUserCatListRes();
		GetUserCatListRes.Content content = userCatListRes.new Content();
		List<Content.Info> infoList = new ArrayList<Content.Info>();
		userCatListRes.setCode(ConstantString.QUEARY_SUCCESS);
		userCatListRes.setMsg("共查询到数据"+pageParam.getRowCount()+"条");

		for(UserCategory userCategory:categoryList){
			Content.Info info = content.new Info();
			info.setTypeCode(String.valueOf(userCategory.getUserCategoryId()));
			info.setTypeName(userCategory.getUserCategoryname());
			info.setElectricFreeLimit(String.valueOf(userCategory.getElectricityFreeQuata()));
			info.setWaterFreeLimit(String.valueOf(userCategory.getWaterFreeQuata()));
			info.setElectricPriceIntax(String.valueOf(userCategory.getTaxElectricityPrice()));
			info.setWaterPriceIntax(String.valueOf(userCategory.getTaxWaterPrice()));
			info.setWaterUnitPrice(String.valueOf(userCategory.getWaterPrice()));
			info.setElectricUnitPrice(String.valueOf(userCategory.getElectricityPrice()));
			infoList.add(info);
		}
		content.setInfo(infoList);
		content.setPage(pageParam.getCurrPage());
		content.setPageNum(pageParam.getTotalPage());
		userCatListRes.setContent(content);
		
		return userCatListRes;
	}



	public boolean updateUserCategory(ChangeUserCategoryReq categoryReq) {


		UserCategory category = new UserCategory();
		category.setElectricityFreeQuata(Integer.valueOf(categoryReq.getElectricFreeLimit()));
		category.setElectricityPrice(Double.valueOf(categoryReq.getElectricUnitPrice()));
		category.setTaxElectricityPrice(Double.valueOf(categoryReq.getElectricPriceIntax()));
		category.setWaterFreeQuata(Integer.valueOf(categoryReq.getWaterFreeLimit()));
		category.setWaterPrice(Double.valueOf(categoryReq.getWaterUnitPrice()));
		category.setTaxWaterPrice(Double.valueOf(categoryReq.getWaterPriceIntax()));
		category.setUserCategoryId(Integer.valueOf(categoryReq.getTypeCode()));
		category.setUserCategoryname(categoryReq.getTypeName());

		if(dao.updateUserCategory(category)==0){
			return false;
		}else {
			return true;
		}
	}

	public String deleteUserCategory(List<Integer> ids) {

		int deleteFlag = dao.deleteUserCategory(ids);
		if(deleteFlag==0){
			return ConstantString.DELETE_FAILED;
		}else if(deleteFlag<ids.size()){
			return ConstantString.DELETE_PART_FAILED;
		} else{
			return ConstantString.DELETE_SUCCESS;
		}

	}

	public UserCategory addUserCategory(AddUserCategoryReq categoryReq) {

		UserCategory category = new UserCategory();
		category.setElectricityFreeQuata(Integer.valueOf(categoryReq.getElectricFreeLimit()));
		category.setElectricityPrice(Double.valueOf(categoryReq.getElectricUnitPrice()));
		category.setTaxElectricityPrice(Double.valueOf(categoryReq.getElectricPriceIntax()));
		category.setWaterFreeQuata(Integer.valueOf(categoryReq.getWaterFreeLimit()));
		category.setWaterPrice(Double.valueOf(categoryReq.getWaterUnitPrice()));
		category.setTaxWaterPrice(Double.valueOf(categoryReq.getWaterPriceIntax()));
		category.setUserCategoryname(categoryReq.getTypeName());
		int insertFlag = dao.addUserCategory(category);
		return category;
	}

	public GetAllUserCatListRes getAllUserCatList() {

		List<UserCategory> userCatList = dao.selectAll();

		GetAllUserCatListRes userCatListRes = new GetAllUserCatListRes();
		userCatListRes.setCode(ConstantString.QUEARY_SUCCESS);
		userCatListRes.setMsg("查询成功");
		List<GetAllUserCatListRes.Content> contents = new ArrayList<GetAllUserCatListRes.Content>();
		for (UserCategory one:userCatList){
			GetAllUserCatListRes.Content content = userCatListRes.new Content();
			content.setTypeCode(String.valueOf(one.getUserCategoryId()));
			content.setTypeName(String.valueOf(one.getUserCategoryname()));
			contents.add(content);
		}
		userCatListRes.setContent(contents);
		return userCatListRes;
	}


}
