package com.zjgsu.shuidiansys.dao;

import com.zjgsu.shuidiansys.common.PageParam;
import com.zjgsu.shuidiansys.pojo.UserInfo;
import com.zjgsu.shuidiansys.pojo.WaterFeeInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IWaterFeeInfoDao {

    public int getRowCountByPage();

    public List<WaterFeeInfo> selectByParams(Map<String,Object> params);

    public int getRowCountByQuery(WaterFeeInfo water);

    public List<WaterFeeInfo> selectByQuery(Map<String,Object> query);

    public int deleteWaterFee(List<String> ids);

    public int updateWaterFee(WaterFeeInfo water);

    public UserInfo selectOneById(@Param("waterId") String waterId);

    public UserInfo selectOneByIds(String userId,String waterId);

    public int insert(WaterFeeInfo waterFeeInfo);

    public List<WaterFeeInfo> selectByIds(@Param("Ids") List<String> ids);

    public WaterFeeInfo selectById(@Param("id") String id);

    public WaterFeeInfo selectOneByDateLike(@Param("date") String date,@Param("meterId") String meterId);

    public List<WaterFeeInfo> selectByDateLike(@Param("date") String date);
}
