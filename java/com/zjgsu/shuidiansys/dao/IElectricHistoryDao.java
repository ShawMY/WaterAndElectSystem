package com.zjgsu.shuidiansys.dao;

import com.zjgsu.shuidiansys.pojo.ElectricHistory;

import java.util.List;
import java.util.Map;

public interface IElectricHistoryDao {
    public int getRowCountByPage();

    public List<ElectricHistory> selectByParams(Map<String,Object> params);

    public int delete(List<String> ids);

    public int update(ElectricHistory electric);

    public int insert(ElectricHistory electric);
}
