package com.ffyc.server.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.ffyc.server.vo.DbConfigVO;

public class DataSource {
	
	public static DbConfigVO getDefaultDbConfig(){
     	DbConfigVO config = new DbConfigVO();
     	config.setDbType("mysql");
     	config.setDbName("beautyfamily");
     	config.setHost("127.0.0.1");
     	config.setPort("3306");
     	config.setUser("root");
     	config.setPassWord("root");
     	return config;
    }
	
	public static DbConfigVO getDbConfigLocal(){
		File f = new File(DataSource.class.getResource("/").getPath() +"config.properties");
		if (!f.exists()) return null;
		Properties prop = new Properties();
        DbConfigVO config = getDefaultDbConfig();
        try {   
        	InputStream in = new FileInputStream(f);
            prop.load(in); 
            String url = prop.getProperty("DB_URL").trim();   
            String user = prop.getProperty("DB_USERNAME").trim();   
            String passWord = prop.getProperty("DB_PASSWORD").trim();
            //url = “jdbc:mysql://localhost:3306/beautyfamily?useUnicode=true&characterEncoding=UTF-8”;
            //url = "jdbc:mysql://10.10.100.37:3306/cloud?useUnicode=true&characterEncoding=utf-8";
            String[] url_array=url.split(":");
            if(url_array.length==4){
            	config.setDbType(url_array[1]);
            }
            url = url.split("//")[1];
            url = url.split("\\?")[0];
            url = url.replace("/", ":");
            String[] arr = url.split(":");
            if (user!=null && passWord!=null && arr.length==3){
            	
                String host = arr[0];
                String port = arr[1];
                String dbName = arr[2];
                
            	config.setUser(user);
                config.setPassWord(passWord);
                config.setHost(host);
                config.setPort(port);
                config.setDbName(dbName);
                return config;
            }
            return config;
            
        } catch (IOException e) {   
            e.printStackTrace();   
        } 
        return null;
	}
    
   
}
