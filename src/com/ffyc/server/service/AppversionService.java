package com.ffyc.server.service;

import java.io.FileNotFoundException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import com.ffyc.exception.about.LatestVersionException;
import com.ffyc.server.vo.AppversionVO;
import com.ffyc.exception.attach.AttachIOException;

public interface AppversionService
{

    /**
     * 检查是否最新版本
     * 
     * @param app 客户端App类型
     * @param versionNo App版本号
     * @return 如果是最新版本，返回null
     */
    public AppversionVO checkLatestVersion(String app, String versionNo) throws LatestVersionException;

    /**
     * 下载App附件
     * 
     * @param app 客户端App类型
     * @param versionNo App版本号
     * @param response HTTPResponse对象
     * @param out 附件下载的输出流
     * @throws FileNotFoundException 找不到文件，抛出该异常
     * @throws AttachIOException 文件获取时发生IO错误，抛出该异常
     */
    public void downloadAppAttach(String app, String versionNo, HttpServletResponse response, OutputStream out) throws FileNotFoundException, AttachIOException;

    /**
     * 获取最新版APP信息
     * @param app
     * @return
     */
	public AppversionVO getLatestVersion(String app);
}
