package com.zjgsu.shuidiansys.dao;

import com.zjgsu.shuidiansys.pojo.ElectricFeeInfo;
import com.zjgsu.shuidiansys.pojo.UserInfo;
import org.apache.ibatis.annotations.Param;

import javax.xml.registry.infomodel.User;
import java.util.List;
import java.util.Map;

public interface IElectricFeeInfoDao {


    public int getRowCountByPage();

    public List<ElectricFeeInfo> selectByParams(Map<String,Object> params);

    public int getRowCountByQuery(ElectricFeeInfo electric);

    public List<ElectricFeeInfo> selectByQuery(Map<String,Object> query);

    public int delete(List<String> ids);

    public int update(ElectricFeeInfo electric);

    public UserInfo selectOneById(@Param("electricId") String electricId);

    public UserInfo selectOneByIds(String userId, String electricId);

    public int insert(ElectricFeeInfo electricFeeInfo);

    public List<ElectricFeeInfo> selectByIds(@Param("Ids")List<String> ids);

    public ElectricFeeInfo selectById(@Param("id") String id);

    public ElectricFeeInfo selectOneByDateLike(@Param("date") String date,@Param("meterId") String meterId);

    public List<ElectricFeeInfo> selectByDateLike(@Param("date") String date);
}
