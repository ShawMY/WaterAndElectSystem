package com.zjgsu.shuidiansys.dao;

import com.zjgsu.shuidiansys.pojo.AddressInfo;
import com.zjgsu.shuidiansys.pojo.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface IAddressInfoDao {

    public int getRowCountByPage();

    public int getRowCountByQuery(@Param("level") String level);

    public List<AddressInfo> selectByParams(Map<String,Object> params);

    public int addAddress(AddressInfo addressInfo);

    public int deleteAddress(List<String> ids);

    public int updateAddress(AddressInfo addressInfo);

    public UserInfo selectPreAdress(String id);

    public List<AddressInfo> selectByLevelParams(Map<String,Object> level);

    public AddressInfo selectLikeAddress(Map<String,Object> maps);

    public AddressInfo selectByName(String name);

    public List<AddressInfo> selectByPreAndLevel(@Param("preCode") String preCode, @Param("level") String level);
}
