package com.ffyc.server.common;

import java.io.File;

import com.nationsky.pub.Env;

public class AttachDir
{
    /** 附件文件夹 */
    public static final File ATTACH_DIR = new File(Env.getProperty("ATTACH_DIR"));

    /** 客户端App安装包文件夹 */
    public static final File APP_DIR = new File(ATTACH_DIR, "app");

    /** 用户头像目录 */
    public static final File USER_MOBILE_DIR = new File(ATTACH_DIR, "user_mobile");


    static
    {
        ATTACH_DIR.mkdirs();
        APP_DIR.mkdirs();
        USER_MOBILE_DIR.mkdirs();
    }
}
