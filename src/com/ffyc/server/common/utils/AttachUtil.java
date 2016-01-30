package com.ffyc.server.common.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;

import com.ffyc.exception.attach.AttachIOException;
import com.ffyc.server.common.AttachDir;

public class AttachUtil
{
    /**
     * 返回文件的上附件路径（ID）
     * 
     * @deprecated 未使用，貌似有点问题
     * @param f 文件
     * @return 该文件的附件路径
     */
    public static final String getAttachPath(File f)
    {
        return f.getPath().replaceAll(Pattern.quote(AttachDir.ATTACH_DIR.getPath()), "").replaceAll(Pattern.quote("\\"), "/");
    }

    /**
     * 删除文件夹dir下文件名以prefix开头的文件
     * 
     * @param dir 文件夹
     * @param prefix 要删除的文件名前缀
     * @throws AttachIOException
     */
    public static final void deleteStartsWith(File dir, final String prefix) throws AttachIOException
    {
        File files[] = dir.listFiles(new FilenameFilter()
        {
            @Override
            public boolean accept(File dir, String name)
            {
                if(name.startsWith(prefix))
                {
                    return true;
                }
                return false;
            }
        });
        if(files != null)
        {
            for(File file: files)
            {
                if(!file.exists())
                {
                    continue;
                }
                try
                {
                    FileUtils.forceDelete(file);
                }
                catch(IOException e)
                {
                    throw new AttachIOException("附件删除出错，" + e.getMessage());
                }
            }
        }
    }

    /**
     * 获取dir下以prefix开头的文件名的数量
     * 
     * @param dir 文件夹
     * @param prefix 要统计的文件名前缀
     * @return 文件数量
     */
    public static int getFileCountStartsWith(File dir, final String prefix)
    {
        File files[] = dir.listFiles(new FilenameFilter()
        {
            @Override
            public boolean accept(File dir, String name)
            {
                if(name.startsWith(prefix))
                {
                    return true;
                }
                return false;
            }
        });

        if(files == null)
        {
            return 0;
        }
        return files.length;
    }

    /**
     * 获取dir下以prefix开头的文件名的第一个文件
     * 
     * @param dir 文件夹
     * @param prefix 要获取的文件名前缀
     * @return 文件
     */
    public static File getFirstFileStartsWith(File dir, final String prefix)
    {
        File files[] = dir.listFiles(new FilenameFilter()
        {
            @Override
            public boolean accept(File dir, String name)
            {
                if(name.startsWith(prefix))
                {
                    return true;
                }
                return false;
            }
        });

        if(files == null || files.length == 0)
        {
            return null;
        }
        return files[0];
    }

    /**
     * 将文件写入到HttpServletResponse中实现下载
     * 
     * @param file 文件
     * @param response HttpServletResponse
     * @throws IOException 如果写入过程中发生IO错误，抛出该异常
     */
    public static void writeToResponse(File file, HttpServletResponse response) throws IOException
    {
        String disposition = "attachment;filename=" + URLEncoder.encode(file.getName(), "UTF-8");
        response.setContentType(MIMEUtils.getMIME(file.getName()));
        response.setHeader("Content-disposition", disposition);

        FileInputStream in = new FileInputStream(file);
        OutputStream out = response.getOutputStream();

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
}
