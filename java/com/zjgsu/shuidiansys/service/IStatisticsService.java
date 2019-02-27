package com.zjgsu.shuidiansys.service;

import java.util.List;
import java.util.Map;

public interface IStatisticsService {

    public List<Map<String,Object>> getEYearTopSecondCompare(String year);
}
