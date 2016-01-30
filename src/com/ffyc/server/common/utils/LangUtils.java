package com.ffyc.server.common.utils;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

public class LangUtils
{

    public static Set<String> getTypeNamesInPackage(String packName)
    {
        Set<String> classNameSet = new HashSet<String>();
        String packageName = packName;
        String packageDirName = packageName.replace(".", "/");
        Enumeration<URL> dirs = null;
        try
        {
            dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
            // 迭代此 Enumeration
            while(dirs.hasMoreElements())
            {
                URL url = dirs.nextElement();
                File file = new File(url.getFile());
                // 把此目录下的所有文件列出
                String[] classes = file.list();
                // 循环此数组，并把.class去掉
                for(String className: classes)
                {
                    className = className.substring(0, className.length() - 6);
                    // 添加到集合中
                    classNameSet.add(className);
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return classNameSet;
    }

    /**
     * 将一场堆栈转换为字符串表示
     * 
     * @param e 异常
     * @return
     */
    public static final String stackTrace2String(Exception e)
    {
        Writer w = new StringWriter();
        e.printStackTrace(new PrintWriter(w));
        String smsg = w.toString();

        return smsg;
    }

}
