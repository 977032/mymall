package com.ffyc.server.mapper;

import org.apache.ibatis.annotations.Param;

import com.ffyc.server.vo.AppversionVO;

public interface AppversionMapper
{
    public AppversionVO getLatestAppversion(@Param("app") String app);
}
