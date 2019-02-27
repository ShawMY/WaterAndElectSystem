package com.zjgsu.shuidiansys.controller;

import com.zjgsu.shuidiansys.common.PageParam;
import com.zjgsu.shuidiansys.pojo.RESTful.*;
import com.zjgsu.shuidiansys.service.IUserInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
//Tested

@Controller
public class UserInfoController {

    @Resource
    IUserInfoService service;


    @ResponseBody
    @RequestMapping(value = "/api/user/add",method = {RequestMethod.POST})
    public AddUserInfoRes addUserInfo(@RequestBody AddUserInfoReq user){
        AddUserInfoRes result = service.addUserInfo(user);

        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/api/user/chg",method = {RequestMethod.POST})
    public ChangeUserInfoRes changeUserInfo(@RequestBody ChangeUserInfoReq user){
        ChangeUserInfoRes result = service.changeUserInfo(user);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/api/user",method = {RequestMethod.DELETE})
    public DeleteUserInfoRes deleteUserInfo(@RequestBody Id[] ids){
        DeleteUserInfoRes result = service.deleteUserInfo(ids);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/api/user",method = {RequestMethod.GET})
    public GetUserInfoListRes deleteUserInfo(@RequestParam int page){

        PageParam pageParam = new PageParam();
        pageParam.setCurrPage(page);
        GetUserInfoListRes result = service.getUserListByPage(pageParam);

        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/api/user/some",method = {RequestMethod.GET})
    public QueryUserInfoRes queryUserInfo(QueryUserInfoReq userInfoReq){

        PageParam pageParam = new PageParam();
        pageParam.setCurrPage(Integer.valueOf(userInfoReq.getPage()));
        System.out.println(userInfoReq.getTypeName());
        QueryUserInfoRes result = service.getUserListByQuery(userInfoReq,pageParam);

        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/api/code/meter",method = {RequestMethod.GET})
    public GetMeterIdByTypeRes getMeterIdByType(@RequestParam String type){
        return service.getMeterIdByType(type);
    }




}
