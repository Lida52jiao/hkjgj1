package com.yj.bj.mapper;


import com.yj.bj.entity.CountT1Entity;
import com.yj.bj.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by bin on 2017/12/19.
 */
public interface CountT1Mapper extends MyMapper<CountT1Entity> {
    List<CountT1Entity> getList(@Param("merchantId") String merchantId, @Param("merName") String merName, @Param("startDate") String startDate, @Param("finishDate") String finishDate);
}
