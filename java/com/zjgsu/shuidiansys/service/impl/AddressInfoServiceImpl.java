package com.zjgsu.shuidiansys.service.impl;

import com.zjgsu.shuidiansys.common.ConstantString;
import com.zjgsu.shuidiansys.common.PageParam;
import com.zjgsu.shuidiansys.dao.IAddressInfoDao;
import com.zjgsu.shuidiansys.pojo.AddressInfo;
import com.zjgsu.shuidiansys.pojo.RESTful.*;
import com.zjgsu.shuidiansys.service.IAddressInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AddressInfoServiceImpl implements IAddressInfoService {

    @Resource
    IAddressInfoDao dao;

    public int getRowCountByLevel(String level){return dao.getRowCountByQuery(level);}

    public int getRowCount(){return dao.getRowCountByPage();}

    public GetAddressByLevelRes getAddressByLevel(int level, PageParam pageParam) {

        int currPage = pageParam.getCurrPage();
        String sLevel = ConstantString.LEVELS.get(level-1);
        pageParam.setRowCount(getRowCountByLevel(sLevel));
        // limit offset, size
        int offset = (currPage - 1) * PageParam.pageSize ; //起始条数
        int size = PageParam.pageSize;

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("offset", offset);
        params.put("size", size);
        params.put("address",sLevel);

        List<AddressInfo> addressInfos = dao.selectByLevelParams(params);

        GetAddressByLevelRes addressRes=new GetAddressByLevelRes();
        GetAddressByLevelRes.Content content = addressRes.new Content();
        List<GetAddressByLevelRes.Content.Addr> addrList = new ArrayList<GetAddressByLevelRes.Content.Addr>();
        for(AddressInfo addrInfo:addressInfos){
            GetAddressByLevelRes.Content.Addr addr = content.new Addr();
            addr.setCode(addrInfo.getAddressId());
            addr.setLevel(addrInfo.getAddressLevel());
            addr.setName(addrInfo.getAddress());
            addr.setPreCode(addrInfo.getSuperiorId());
            addrList.add(addr);
        }
        addressRes.setCode(ConstantString.QUEARY_SUCCESS);
        addressRes.setMsg("共查询到数据"+pageParam.getRowCount()+"条");
        content.setAddr(addrList);
        content.setPage(pageParam.getCurrPage());
        content.setPageNum(pageParam.getTotalPage());
        addressRes.setContent(content);
        return addressRes;
    }

    public GetAddressListRes getAddressList(PageParam pageParam) {
        int currPage = pageParam.getCurrPage();
        pageParam.setRowCount(getRowCount());
        // limit offset, size
        int offset = (currPage - 1) * PageParam.pageSize ; //起始条数
        int size = PageParam.pageSize;

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("offset", offset);
        params.put("size", size);

        List<AddressInfo> addressInfos = dao.selectByParams(params);

        GetAddressListRes addressRes=new GetAddressListRes();
        GetAddressListRes.Content content = addressRes.new Content();
        List<GetAddressListRes.Content.Addr> addrList = new ArrayList<GetAddressListRes.Content.Addr>();
        for(AddressInfo addrInfo:addressInfos){
            GetAddressListRes.Content.Addr addr = content.new Addr();
            addr.setCode(addrInfo.getAddressId());
            addr.setLevel(addrInfo.getAddressLevel());
            addr.setName(addrInfo.getAddress());
            addr.setPreCode(addrInfo.getSuperiorId());
            addrList.add(addr);
        }
        addressRes.setCode(ConstantString.QUEARY_SUCCESS);
        addressRes.setMsg("共查询到数据"+pageParam.getRowCount()+"条");
        content.setAddr(addrList);
        content.setPage(pageParam.getCurrPage());
        content.setPageNum(pageParam.getTotalPage());
        addressRes.setContent(content);
        return addressRes;
    }

    public DeleteAddressRes deleteAddress(DeleteAddressReq addressReq) {
        int deleteFlag = dao.deleteAddress(addressReq.getIds());
        DeleteAddressRes addressRes = new DeleteAddressRes();

        if(deleteFlag == 0){
            addressRes.setCode(ConstantString.DELETE_FAILED);
            addressRes.setMsg("删除数据失败");
        }else if(deleteFlag < addressReq.getIds().size()){
            addressRes.setCode(ConstantString.DELETE_PART_FAILED);
            addressRes.setMsg("删除数据部分失败");
        }else{
            addressRes.setCode(ConstantString.DELETE_SUCCESS);
            addressRes.setMsg("删除数据成功");
        }
        return addressRes;
    }

    public ChangeAddressRes changeAddress(ChangeAddressReq addressReq) {
        AddressInfo addressInfo = new AddressInfo();
        addressInfo.setAddress(addressReq.getAddressName());
        addressInfo.setAddressId(addressReq.getCode());
        addressInfo.setAddressLevel(addressReq.getLevel());
        addressInfo.setSuperiorId(addressReq.getPreCode());

        int updateFlag = dao.updateAddress(addressInfo);
        ChangeAddressRes addressRes = new ChangeAddressRes();
        if(updateFlag == 0){
            addressRes.setCode(ConstantString.UPDATE_FAILED);
            addressRes.setMsg("更新数据失败");
        }else {
            addressRes.setCode(ConstantString.INSERT_SUCCESS);
            addressRes.setMsg("更新数据成功");

        }
        return addressRes;
    }

    public String createAddressId(int level,String preCode) {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("level", ConstantString.LEVELS.get(level-1));
        map.put("preCode", preCode);
        AddressInfo addr = dao.selectLikeAddress(map);

        int code = 0;

        if(addr!=null){
            code = Integer.valueOf(addr.getAddressId())+1;
        }else{
            code = Integer.valueOf((preCode+String.valueOf(level))+"00");
            System.out.println(code);
        }


        return String.valueOf(code);
    }

    public AddAddressRes addAddress(AddAddressReq addAddressReq) {

        AddressInfo addr = new AddressInfo();

        addr.setSuperiorId(addAddressReq.getPreCode());
        addr.setAddressLevel(addAddressReq.getLevel());
        for(int i=0;i<ConstantString.LEVELS.size();i++) {
            if(ConstantString.LEVELS.get(i).equals(addAddressReq.getLevel())) {
                addr.setAddressId(createAddressId(i+1,addAddressReq.getPreCode()));
            }

        }
        addr.setAddress(addAddressReq.getAddressName());

        int addFlag = dao.addAddress(addr);
        AddAddressRes addressRes = new AddAddressRes();
        AddAddressRes.Content content = addressRes.new Content();

        if(addFlag == 0){
            addressRes.setCode(ConstantString.INSERT_FAILED);
            addressRes.setMsg("插入数据失败");
        }else {
            addressRes.setCode(ConstantString.INSERT_SUCCESS);
            addressRes.setMsg("插入成功");
            content.setCode(addr.getAddressId());
            addressRes.setContent(content);
        }

        return addressRes;
    }

    public GetAddressByPreRes getAddressByPre(String preId, String level) {
        List<AddressInfo> addressInfos =new ArrayList<AddressInfo>();
        try {
            addressInfos = dao.selectByPreAndLevel(preId, level);
        }catch (Exception e){
            GetAddressByPreRes addressByPreRes = new GetAddressByPreRes();
            addressByPreRes.setCode(ConstantString.QUEARY_FAILED);
            addressByPreRes.setMsg("查询失败，数据不存在或输入错误");
            return addressByPreRes;
        }

        GetAddressByPreRes addressByPreRes = new GetAddressByPreRes();
        List<GetAddressByPreRes.Content> contents = new ArrayList<GetAddressByPreRes.Content>();
        for(AddressInfo one:addressInfos){
            GetAddressByPreRes.Content content = addressByPreRes.new Content();
            content.setCode(one.getAddressId());
            content.setName(one.getAddress());
            contents.add(content);
        }
        addressByPreRes.setCode(ConstantString.QUEARY_SUCCESS);
        addressByPreRes.setMsg("查询成功");
        addressByPreRes.setContent(contents);
        return addressByPreRes;
    }
}
