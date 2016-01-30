package com.ffyc.server.service.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ffyc.exception.about.LatestVersionException;
import com.ffyc.exception.attach.AttachIOException;
import com.ffyc.server.service.AppversionService;
import com.ffyc.server.vo.AppversionVO;
import com.ffyc.server.common.AttachDir;
import com.ffyc.server.common.utils.MIMEUtils;
import com.ffyc.server.mapper.AppversionMapper;

@Service("appversionService")
public class AppversionServiceImpl implements AppversionService
{
    @Autowired
    private AppversionMapper appversionMapper;

    @Override
    public AppversionVO checkLatestVersion(String app, String versionNo) throws LatestVersionException
    {
        AppversionVO vo = appversionMapper.getLatestAppversion(app);
        if(vo.getVersionNo().equals(versionNo))
        {
            throw new LatestVersionException("您的App是最新版本");
        }
        return vo;
    }
    
    @Override
    public AppversionVO getLatestVersion(String app) 
    {
        AppversionVO vo = appversionMapper.getLatestAppversion(app);
       
        return vo;
    }

    @Override
    public void downloadAppAttach(String app, final String versionNo, HttpServletResponse response, OutputStream out) throws FileNotFoundException, AttachIOException
    {
        File appFile = null;
        // 获取附件
        {
            File appFiles[] = new File(AttachDir.APP_DIR, app).listFiles(new FilenameFilter()
            {
                @Override
                public boolean accept(File dir, String name)
                {
                    if(name.startsWith(versionNo))
                    {
                        return true;
                    }
                    return false;
                }
            });
            if(appFiles != null && appFiles.length > 0)
            {
                appFile = appFiles[0];
            }
        }

        if(appFile == null)
        {
            throw new FileNotFoundException("找不到附件");
        }

        try
        {
            String disposition = "attachment;filename=" + URLEncoder.encode(appFile.getName(), "UTF-8");
            response.setContentType(MIMEUtils.getMIME(appFile.getName()));
            response.addHeader("Content-Disposition", disposition);
            response.addHeader("Content-Length", String.valueOf(appFile.length()));

            FileInputStream in = new FileInputStream(appFile);

            BufferedInputStream bis = new BufferedInputStream(in);
            BufferedOutputStream bos = new BufferedOutputStream(out);

            byte[] buffer = new byte[1024];

            while(bis.read(buffer) != -1)
            {
                bos.write(buffer);
            }

            bos.flush();
            bos.close();

            out.flush();
            out.close();

            bis.close();
            in.close();
        }
        catch(IOException e)
        {
            throw new AttachIOException("App下载失败，" + e.getMessage());
        }

    }

}
