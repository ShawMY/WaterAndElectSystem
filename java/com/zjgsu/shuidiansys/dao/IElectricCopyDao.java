package com.zjgsu.shuidiansys.dao;

import com.zjgsu.shuidiansys.pojo.ElectricCopy;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IElectricCopyDao {
    public int insert(ElectricCopy electricCopy);

    public int delete(List<String> ids);

    public int update(ElectricCopy electricCopy);

    public List<ElectricCopy> selectByParams(Map<String,Object> params);

    public List<ElectricCopy> selectByQuery(Map<String,Object> query);

    public int getRowCountByPage();

    public int getRowCountByQuery(Map<String,Object> query);

    public int getRowCountById(@Param("id") String id);

    public List<ElectricCopy> selectByIds(@Param("Ids") List<String> ids);
}
