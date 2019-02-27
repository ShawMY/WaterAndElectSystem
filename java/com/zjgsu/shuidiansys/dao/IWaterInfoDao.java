package com.zjgsu.shuidiansys.dao;

import com.zjgsu.shuidiansys.pojo.WaterInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IWaterInfoDao {

    public int getRowCountByPage();

    public List<WaterInfo> selectByParams(Map<String,Object> params);

    public int getRowCountByQuery(WaterInfo water);

    public List<WaterInfo> selectByQuery(Map<String,Object> query);

    public int deleteWaterInfo(List<String> ids);

    public int updateWaterInfo(WaterInfo water);

    public WaterInfo selectLikeInfo(String id);

    public int addWaterInfo(WaterInfo waterInfo);

    public List<WaterInfo> selectByAddr(String firstAddr,String secondAddr);

    public List<WaterInfo> selectAllByQuery(WaterInfo waterInfo);

    public List<WaterInfo> selectByLevel(String level);

    public WaterInfo selectById(@Param("waterId") String waterId);

    public List<WaterInfo> selectAll();
}
