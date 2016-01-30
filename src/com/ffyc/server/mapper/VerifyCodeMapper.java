package com.ffyc.server.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ffyc.server.model.VerifyCode;

public interface VerifyCodeMapper
{
    public List<VerifyCode> list(@Param("type") String type);

    public  VerifyCode get(@Param("type") String type, @Param("key") String key);

    public int isExists(@Param("type") String type, @Param("key") String key);

    public void update(@Param("type") String type, @Param("key") String key, @Param("verifyCode") VerifyCode verifyCode);

    public void insert(@Param("type") String type, @Param("key") String key, @Param("verifyCode") VerifyCode verifyCode);

    public  void delete(@Param("type") String type, @Param("key") String key);

}
