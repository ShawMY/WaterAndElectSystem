package com.zjgsu.shuidiansys.dao;

import com.zjgsu.shuidiansys.pojo.ElectricInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IElectricInfoDao {

    public int getRowCountByPage();

    public List<ElectricInfo> selectByParams(Map<String,Object> params);

    public int getRowCountByQuery(ElectricInfo electric);

    public List<ElectricInfo> selectByQuery(Map<String,Object> query);

    public int delete(List<String> ids);

    public int update(ElectricInfo electric);

    public ElectricInfo selectLikeInfo(String id);

    public int insert(ElectricInfo electricInfo);

    public List<ElectricInfo> selectByAddr(String firstAddr,String secondAddr);

    public List<ElectricInfo> selectAllByQuery(ElectricInfo electricInfo);

    public List<ElectricInfo> selectByLevel(String level);

    public ElectricInfo selectById(@Param("electricId") String electricId);

    public List<ElectricInfo> selectAll();
}
