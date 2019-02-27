package com.zjgsu.shuidiansys.controller;

import com.zjgsu.shuidiansys.common.ConstantString;
import com.zjgsu.shuidiansys.common.PageParam;
import com.zjgsu.shuidiansys.common.Util;
import com.zjgsu.shuidiansys.pojo.RESTful.*;
import com.zjgsu.shuidiansys.pojo.UserCategory;
import com.zjgsu.shuidiansys.service.IUserCategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
//Tested

@Controller
public class UserCategoryController {

    @Resource
    IUserCategoryService iUserCategoryService;

    @ResponseBody
    @RequestMapping(value = "/api/user/cat" ,method = {RequestMethod.GET})
    public GetUserCatListRes getUserCatList(@RequestParam int page){

        PageParam pageParam = new PageParam();
        pageParam.setCurrPage(page);
        GetUserCatListRes userCatListRes = iUserCategoryService.getUserCategoryList(pageParam);
        return userCatListRes;

    }

    @ResponseBody
    @RequestMapping(value = "/api/user/cat/chg" ,method = {RequestMethod.POST})
    public ChangeUserCategoryRes changeUserCategory(@RequestBody ChangeUserCategoryReq userCatListReq){

        ChangeUserCategoryRes userCategoryRes = new ChangeUserCategoryRes();
        if(iUserCategoryService.updateUserCategory(userCatListReq)){
            userCategoryRes.setCode(ConstantString.UPDATE_SUCCESS);
            userCategoryRes.setMsg("更新成功");
        }else {
            userCategoryRes.setCode(ConstantString.UPDATE_FAILED);
            userCategoryRes.setMsg("更新失败，未找到该数据");
        }
        return userCategoryRes;
    }

    @ResponseBody
    @RequestMapping(value = "/api/user/cat",method = {RequestMethod.DELETE})
    public DeleteUserCategoryRes deleteUserCategory(@RequestBody DeleteUserCategoryReq userCategoryReq){

        DeleteUserCategoryRes userCategoryRes = new DeleteUserCategoryRes();
        List<Integer> ids = Util.listStringToInteger(userCategoryReq.getIds());
        String deleteFlag = iUserCategoryService.deleteUserCategory(ids);
        userCategoryRes.setCode(deleteFlag);
        if(deleteFlag.equals(ConstantString.DELETE_SUCCESS)){
            userCategoryRes.setMsg("删除数据成功");
        }else if(deleteFlag.equals(ConstantString.DELETE_PART_FAILED)){
            userCategoryRes.setMsg("部分删除失败");
        }else{
            userCategoryRes.setMsg("删除失败");
        }
        return userCategoryRes;
    }


    @ResponseBody
    @RequestMapping(value = "/api/user/cat/add",method = {RequestMethod.POST})
    public AddUserCategoryRes addUserCategory(@RequestBody AddUserCategoryReq userCategoryReq){
        UserCategory cat = iUserCategoryService.addUserCategory(userCategoryReq);
        AddUserCategoryRes result = new AddUserCategoryRes();
        AddUserCategoryRes.Content content = result.new Content();
        content.setTypeCode(String.valueOf(cat.getUserCategoryId()));
        if(cat.getUserCategoryId()==0){
            result.setCode(ConstantString.INSERT_FAILED);
            result.setMsg("插入失败");
        }else{
            result.setCode(ConstantString.INSERT_SUCCESS);
            result.setMsg("插入成功");
            result.setContent(content);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/api/user/cat/item",method = {RequestMethod.GET})
    public GetAllUserCatListRes addUserCategory(){
        return iUserCategoryService.getAllUserCatList();
    }
}
