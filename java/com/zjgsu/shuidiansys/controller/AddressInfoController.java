package com.zjgsu.shuidiansys.controller;


import com.zjgsu.shuidiansys.common.PageParam;
import com.zjgsu.shuidiansys.pojo.RESTful.*;
import com.zjgsu.shuidiansys.service.IAddressInfoService;
import com.zjgsu.shuidiansys.service.IUserInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
public class AddressInfoController {

    @Resource
    IAddressInfoService service;

    @ResponseBody
    @RequestMapping(value = "/api/address/level/item",method = {RequestMethod.GET})
    public GetAddressByLevelRes getAddressByLevel(@RequestParam int level,@RequestParam int page){
        PageParam pageParam = new PageParam();
        pageParam.setCurrPage(page);
        return service.getAddressByLevel(level,pageParam);
    }

    @ResponseBody
    @RequestMapping(value = "/api/address/add",method = {RequestMethod.POST})
    public AddAddressRes addAddress(@RequestBody AddAddressReq addressReq){
        return service.addAddress(addressReq);

    }

    @ResponseBody
    @RequestMapping(value = "/api/address",method = {RequestMethod.DELETE})
    public DeleteAddressRes deleteAddress(@RequestBody DeleteAddressReq addressReq){
        return service.deleteAddress(addressReq);
    }

    @ResponseBody
    @RequestMapping(value = "/api/address/chg",method = {RequestMethod.POST})
    public ChangeAddressRes changeAddress(@RequestBody ChangeAddressReq addressReq){
        return service.changeAddress(addressReq);
    }


    @ResponseBody
    @RequestMapping(value = "/api/address/preLevel",method = {RequestMethod.GET})
    public GetAddressByPreRes getAddressByPre(@RequestParam String level,@RequestParam String preCode){

        return service.getAddressByPre(preCode,level);

    }

    @ResponseBody
    @RequestMapping(value = "/api/address",method = {RequestMethod.GET})
    public GetAddressListRes getAddressList(@RequestParam int page){
        PageParam pageParam = new PageParam();
        pageParam.setCurrPage(page);
        return service.getAddressList(pageParam);
    }

}
