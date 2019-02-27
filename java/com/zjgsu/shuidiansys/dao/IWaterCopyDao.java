package com.zjgsu.shuidiansys.dao;

import com.zjgsu.shuidiansys.common.PageParam;
import com.zjgsu.shuidiansys.pojo.WaterCopy;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IWaterCopyDao {

    public int insert(WaterCopy waterCopy);

    public int delete(List<String> ids);

    public int update(WaterCopy waterCopy);

    public List<WaterCopy> selectByParams(Map<String,Object> params);

    public List<WaterCopy> selectByQuery(Map<String,Object> query);

    public int getRowCountByPage();

    public int getRowCountByQuery(Map<String,Object> query);

    public int getRowCountById(@Param("id") String id);

    public List<WaterCopy> selectByIds(@Param("Ids") List<String> ids);
}
