package com.zjgsu.shuidiansys.dao;

import com.zjgsu.shuidiansys.pojo.WaterHistory;

import java.util.List;
import java.util.Map;

public interface IWaterHistoryDao {

    public int getRowCountByPage();

    public List<WaterHistory> selectByParams(Map<String,Object> params);

    public int delete(List<String> ids);

    public int update(WaterHistory water);

    public int insert(WaterHistory water);
}
