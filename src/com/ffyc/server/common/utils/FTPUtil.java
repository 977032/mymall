package com.ffyc.server.common.utils;

import it.sauronsoftware.ftp4j.FTPClient;
import it.sauronsoftware.ftp4j.FTPException;

import java.io.File;

import com.nationsky.pub.framework.exception.SystemException;
import com.nationsky.pub.log.Log;
import com.nationsky.pub.log.LogFactory;

public class FTPUtil
{

    private static final Log log = LogFactory.getLog(FTPUtil.class);

    FTPClient ftpClient = null;

    FTPParams ftpParams = null;

    public FTPUtil(FTPParams ftpParams)
    {
        this.ftpParams = ftpParams;
    }

    /**
     * 连接并登录FTP服务器
     * 
     */
    public boolean ftpLogin()
    {
        ftpClient = new FTPClient();
        try
        {
            // 建立连接
            ftpClient.connect(ftpParams.getUrl());
            ftpClient.setType(FTPClient.TYPE_BINARY);
            ftpClient.setPassive(true);
            ftpClient.setCharset("UTF-8");
        }
        catch(Exception e)
        {
            log.error("FTP client connect error, " + e);
        }
        try
        {
            ftpClient.login(ftpParams.getUsername(), ftpParams.getPassword());
        }
        catch(Exception e)
        {
            log.error("FTP client login error, " + e);
        }
        return true;
    }

    /**
     * 退出并关闭FTP连接
     * 
     */
    public void close()
    {
        if(null != ftpClient)
        {
            try
            {
                ftpClient.disconnect(true);
            }
            catch(Exception e)
            {
                log.error("FTP client close error, " + e);
            }
        }
    }

    /**
     * 更换工作目录
     * 
     * @param remotePath 工作目录
     * @throws SystemException
     */
    public void changeDir(String remotePath) throws SystemException
    {
        try
        {
            ftpClient.changeDirectory(remotePath);
        }
        catch(Exception e)
        {
            log.error("FTP file download error, " + e);
            throw new SystemException("跳转FTP目录出错，" + e.getMessage());
        }
    }

    /**
     * 下载文件
     * 
     * @param localFilePath 本地文件名及路径
     * @param remoteFileName 远程文件名称
     * @return
     * @throws Exception
     */
    public void downloadFile(File localFile, String remotePath, String remoteFileName) throws SystemException
    {
        try
        {
            // 下载前强制删除
            localFile.delete();
            ftpClient.changeDirectory(remotePath);
            ftpClient.download(remoteFileName, localFile);
        }
        catch(Exception e)
        {
            // 下载失败强制删除
            localFile.delete();
            log.error("FTP file download error, " + e);
            throw new SystemException("下载FTP文件出错，" + e.getMessage());
        }
    }

    /**
     * 获得FTP 服务器下所有的文件名列表
     * 
     * @param regex
     * @return
     * @throws SystemException
     */
    public String[] listFileNames() throws SystemException
    {
        try
        {
            return this.ftpClient.listNames();
        }
        catch(Exception e)
        {
            log.error("FTP list file error, " + e);
            if(e instanceof FTPException && e.getMessage().equals("No files found.") || e.getMessage().equals("Transfer complete."))
            {
                return new String[]{};
            }
            throw new SystemException("列出文件目录出错，" + e.getMessage());
        }
    }
}
