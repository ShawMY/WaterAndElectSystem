package com.zjgsu.shuidiansys.service.impl;

import com.zjgsu.shuidiansys.common.ConstantString;
import com.zjgsu.shuidiansys.common.PageParam;
import com.zjgsu.shuidiansys.common.Util;
import com.zjgsu.shuidiansys.dao.IElectricInfoDao;
import com.zjgsu.shuidiansys.dao.IUserInfoDao;
import com.zjgsu.shuidiansys.dao.IWaterInfoDao;
import com.zjgsu.shuidiansys.pojo.ElectricInfo;
import com.zjgsu.shuidiansys.pojo.RESTful.*;
import com.zjgsu.shuidiansys.pojo.UserInfo;
import com.zjgsu.shuidiansys.pojo.WaterInfo;
import com.zjgsu.shuidiansys.service.IUserInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.text.Format;
import java.util.*;

@Service
public class UserInfoServiceImpl implements IUserInfoService {

    @Resource
    IUserInfoDao dao;
    @Resource
    IWaterInfoDao wdao;
    @Resource
    IElectricInfoDao edao;

    public int getRowCountByPage(){return dao.getRowCountByPage();}

    public int getRowCountByQuery(UserInfo userInfo){ return dao.getRowCountByQuery(userInfo) ;}

    public GetUserInfoListRes getUserListByPage(PageParam pageParam) {
        int currPage = pageParam.getCurrPage();
        pageParam.setRowCount(getRowCountByPage());
        // limit offset, size
        int offset = (currPage - 1) * PageParam.pageSize ; //起始条数
        int size = PageParam.pageSize;

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("offset", offset);
        params.put("size", size);

        List<UserInfo> userInfoList = dao.selectByParams(params);


        GetUserInfoListRes userCatInfoListRes = new GetUserInfoListRes();
        GetUserInfoListRes.Content content = userCatInfoListRes.new Content();
        List<GetUserInfoListRes.Content.Info> infoList = new ArrayList<GetUserInfoListRes.Content.Info>();
        userCatInfoListRes.setCode(ConstantString.QUEARY_SUCCESS);
        userCatInfoListRes.setMsg("共查询到数据"+pageParam.getRowCount()+"条");

        for(UserInfo userInfo:userInfoList){
            GetUserInfoListRes.Content.Info info = content.new Info();
            info.setCode(userInfo.getUserId());
            info.setName(userInfo.getUserName());
            info.setContactPeople(userInfo.getContact());
            info.setCloseTime(userInfo.getAccountCloseTime());
            info.setOpenTime(userInfo.getAccountOpenTime());
            info.setPayment(userInfo.getPaymentMethod());
            info.setContactPhone(userInfo.getPhoneNumber());
            info.setMeterCode(userInfo.getMeterId());
            info.setMeterType(userInfo.getMeterCategory());
            info.setTypeCode(String.valueOf(userInfo.getUserCategoryId()));
            info.setTypeName(userInfo.getUserCategoryName());
            infoList.add(info);
        }
        content.setInfo(infoList);
        content.setPage(pageParam.getCurrPage());
        content.setPageNum(pageParam.getTotalPage());
        userCatInfoListRes.setContent(content);

        return userCatInfoListRes;
    }

    public QueryUserInfoRes  getUserListByQuery(QueryUserInfoReq userInfoReq, PageParam pageParam) {
        UserInfo user= new UserInfo();
        user.setContact(userInfoReq.getContactPeople());
        user.setUserCategoryName(userInfoReq.getTypeName());
        user.setMeterId(userInfoReq.getMeterCode());
        user.setUserId(userInfoReq.getCode());
        user.setPhoneNumber(userInfoReq.getContactPhone());
        user.setMeterCategory(userInfoReq.getMeterType());
        user.setUserName(userInfoReq.getName());

        int currPage = pageParam.getCurrPage();
        pageParam.setRowCount(getRowCountByQuery(user));
        // limit offset, size
        int offset = (currPage - 1) * PageParam.pageSize ; //起始条数
        int size = PageParam.pageSize;

        Map<String, Object> maps = new HashMap<String, Object>();
        maps.put("offset", offset);
        maps.put("size", size);
        maps.put("user",user);

        List<UserInfo> userInfoList = dao.selectByCondition(maps);

        QueryUserInfoRes queryUserInfoRes = new QueryUserInfoRes();
        QueryUserInfoRes.Content content = queryUserInfoRes.new Content();
        List<QueryUserInfoRes.Content.Info> infoList = new ArrayList<QueryUserInfoRes.Content.Info>();
        queryUserInfoRes.setCode(ConstantString.QUEARY_SUCCESS);
        queryUserInfoRes.setMsg("共查询到数据"+pageParam.getRowCount()+"条");

        for(UserInfo userInfo:userInfoList){
            QueryUserInfoRes.Content.Info info = content.new Info();
            info.setCode(userInfo.getUserId());
            info.setName(userInfo.getUserName());
            info.setContactPeople(userInfo.getContact());
            info.setCloseTime(userInfo.getAccountCloseTime());
            info.setOpenTime(userInfo.getAccountOpenTime());
            info.setPayment(userInfo.getPaymentMethod());
            info.setMeterCode(userInfo.getMeterId());
            info.setContactPhone(userInfo.getPhoneNumber());
            info.setMeterType(userInfo.getMeterCategory());
            info.setTypeCode(String.valueOf(userInfo.getUserCategoryId()));
            info.setTypeName(userInfo.getUserCategoryName());
            infoList.add(info);
        }
        content.setInfo(infoList);
        content.setPage(pageParam.getCurrPage());
        content.setPageNum(pageParam.getTotalPage());
        queryUserInfoRes.setContent(content);

        return queryUserInfoRes;
    }

    public AddUserInfoRes addUserInfo(AddUserInfoReq userInfoReq) {
        UserInfo user= new UserInfo();
        UserInfo topUser = dao.selectTopOne();
        String topTimeCode = topUser.getUserId().substring(0,6); // 获取初始六位号码
        String lastCode = topUser.getUserId().substring(6,9); // 获取最后三位流水号
        Format f1 = new DecimalFormat("000"); // 格式化流水号
        String currentTimeCode = Util.getCurrentYearAndMonth();
        String userId="";
        UserInfo existedUser = dao.selectByName(userInfoReq.getName());//检测是否存在同名用户
        if (existedUser!=null){
            user.setUserId(existedUser.getUserId());
        }else {
            // 进行时间代码差异比对
            if (topTimeCode.equals(currentTimeCode)) {
                int lastInt = Integer.valueOf(lastCode) + 1;
                userId = currentTimeCode + f1.format(lastInt);
            } else {
                userId = currentTimeCode + "000";
            }
            user.setUserId(userId);
        }
        user.setUserCategoryId(dao.selectCatByName(userInfoReq.getTypeName()).getUserCategoryId());
        user.setMeterId(userInfoReq.getMeterCode());
        user.setContact(userInfoReq.getContactPeople());
        user.setMeterCategory(userInfoReq.getTypeName());
        user.setAccountOpenTime(userInfoReq.getOpenTime());
        user.setAccountCloseTime(userInfoReq.getCloseTime());
        user.setPhoneNumber(userInfoReq.getContactPhone());
        user.setMeterCategory(userInfoReq.getMeterType());
        user.setUserName(userInfoReq.getName());
        user.setUserCategoryName(userInfoReq.getTypeName());


        int addFlag = dao.addUserInfo(user);
        AddUserInfoRes userInfoRes = new AddUserInfoRes();
        AddUserInfoRes.Content content = userInfoRes.new Content();
        if(addFlag == 0){
            userInfoRes.setCode(ConstantString.INSERT_FAILED);
            userInfoRes.setMsg("插入数据失败");
        }else {
            userInfoRes.setCode(ConstantString.INSERT_SUCCESS);
            userInfoRes.setMsg("插入成功");
            content.setTypeCode(user.getUserId());
            userInfoRes.setContent(content);
        }
        return userInfoRes;
    }

    public ChangeUserInfoRes changeUserInfo(ChangeUserInfoReq userInfoReq) {
        UserInfo user = new UserInfo();
        user.setUserCategoryName(userInfoReq.getTypeName());
        user.setUserId(userInfoReq.getCode());
        user.setUserCategoryId(Integer.valueOf(userInfoReq.getTypeCode()));
        user.setMeterId(userInfoReq.getTypeCode());
        user.setContact(userInfoReq.getContactPeople());
        user.setMeterCategory(userInfoReq.getMeterType());
        user.setUserCategoryId(Integer.valueOf(userInfoReq.getTypeCode()));
        user.setAccountOpenTime(userInfoReq.getOpenTime());
        user.setAccountCloseTime(userInfoReq.getCloseTime());
        user.setPhoneNumber(userInfoReq.getContactPhone());
        user.setUserName(userInfoReq.getName());
        user.setMeterId(userInfoReq.getMeterCode());

        int updateFlag = dao.updateUserInfo(user);
        ChangeUserInfoRes userInfoRes = new ChangeUserInfoRes();

        if(updateFlag == 0){
            userInfoRes.setCode(ConstantString.UPDATE_FAILED);
            userInfoRes.setMsg("更新数据失败");
        }else {
            userInfoRes.setCode(ConstantString.INSERT_SUCCESS);
            userInfoRes.setMsg("更新数据成功");

        }
        return userInfoRes;
    }

    public DeleteUserInfoRes deleteUserInfo(Id[] ids) {
        List<Id> idList = Arrays.asList(ids);
        int deleteFlag = dao.deleteUserInfo(idList);
        DeleteUserInfoRes userInfoRes = new DeleteUserInfoRes();

        if(deleteFlag == 0){
            userInfoRes.setCode(ConstantString.DELETE_FAILED);
            userInfoRes.setMsg("删除数据失败");
        }else if(deleteFlag < idList.size()){
            userInfoRes.setCode(ConstantString.DELETE_PART_FAILED);
            userInfoRes.setMsg("删除数据部分失败");
        }else{
            userInfoRes.setCode(ConstantString.DELETE_SUCCESS);
            userInfoRes.setMsg("删除数据成功");
        }
        return userInfoRes;
    }

    public GetMeterIdByTypeRes getMeterIdByType(String type) {
        GetMeterIdByTypeRes res = new GetMeterIdByTypeRes();
        List<String> content = new ArrayList<String>();
        if(type.equals("水")){
            List<WaterInfo> waterInfos = wdao.selectAll ();
            for (WaterInfo one:waterInfos){
                content.add(one.getWaterMeterId());
            }
        }else if(type.equals("电")){
            List<ElectricInfo> electricInfos = edao.selectAll();
            for(ElectricInfo one:electricInfos){
                content.add(one.getElectricityMeterId());
            }
        }
        res.setCode(ConstantString.QUEARY_SUCCESS);
        res.setCode("查询成功");
        res.setContent(content);
        return res;
    }


}
