package com.zjgsu.shuidiansys.dao;

import com.zjgsu.shuidiansys.common.PageParam;
import com.zjgsu.shuidiansys.pojo.TopElectricityMeter;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ITopElectricMeterDao {

    public int getRowCount();

    public List<TopElectricityMeter> selectByParams(Map<String,Object> map);

    public int updateTopElectricityMeter(TopElectricityMeter twm);

    public int deleteTopElectricityMeter(List<String> ids);

    public int addTopElectricityMeter(TopElectricityMeter twm);

    public List<TopElectricityMeter> selectByIds(@Param("ids") List<String> ids);

    public List<TopElectricityMeter> selectByQuery(Map<String,Object> query);

    public TopElectricityMeter selectById(String id);
}
