package com.zjgsu.shuidiansys.dao;

import com.zjgsu.shuidiansys.pojo.TopWaterMeter;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ITopWaterMeterDao {

    public int getRowCount();

    public List<TopWaterMeter> selectByParams(Map<String,Object> map);

    public List<TopWaterMeter> selectByQuery(Map<String,Object> query);

    public int updateTopWaterMeter(TopWaterMeter twm);

    public int deleteTopWaterMeter(List<String> ids);

    public int addTopWaterMeter(TopWaterMeter twm);

    public List<TopWaterMeter> selectByIds(@Param("ids") List<String> ids);

    public TopWaterMeter selectById(String id);
}
