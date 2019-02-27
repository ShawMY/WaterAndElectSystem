package com.zjgsu.shuidiansys.service;

import com.zjgsu.shuidiansys.common.PageParam;
import com.zjgsu.shuidiansys.pojo.RESTful.*;

public interface IAddressInfoService {

    public GetAddressByLevelRes getAddressByLevel(int level, PageParam pageParam);

    public GetAddressListRes getAddressList(PageParam pageParam);

    public DeleteAddressRes deleteAddress(DeleteAddressReq addressReq);

    public ChangeAddressRes changeAddress(ChangeAddressReq addressReq);

    public AddAddressRes addAddress(AddAddressReq addAddressReq);

    public GetAddressByPreRes getAddressByPre(String preId,String level);

}
