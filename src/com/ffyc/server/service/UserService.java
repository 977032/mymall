package com.ffyc.server.service;

import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import com.ffyc.exception.attach.AttachIOException;
import com.ffyc.exception.permission.DuplicateUsernameException;
import com.ffyc.server.model.User;
import com.ffyc.server.model.UserBasicInfo;
import com.ffyc.server.vo.LoginVO;
import com.ffyc.server.vo.UserTokenVO;

public interface UserService
{

    /**
     * 修改用户头像
     * 
     * @param id 用户id
     * @param file 头像附件
     * @throws AttachIOException 处理头像文件时出错，抛出该异常
     */
    public void modifyUserPhoto(String id, MultipartFile file) throws AttachIOException;

    /**
     * 下载用户头像
     * 
     * @param id 用户id
     * @param response HttpServletResponse对象，用于设置返回参数
     * @param out 文件输出到这里
     * @throws FileNotFoundException 文件不存在，抛出该异常
     * @throws AttachIOException 读取头像时出错，抛出该异常
     */
    public void downloadPhoto(String id, HttpServletResponse response, OutputStream out) throws FileNotFoundException, AttachIOException;
    
    /**
     * 修改用户基本信息
     * @param id
     * @param gender
     * @throws DuplicateUsernameException 
     */
    public void updateUserBasicInfo(String id
    		,String gender
    		,String name
            ) throws DuplicateUsernameException;

    /**
     * 更新用户地理信息
     * @param userId
     * @param lng
     * @param lat
     */
	public void updateUserLocation(String userId, double lng, double lat);

	/**
	 * 根据手机号查找用户
	 * @param mobile
	 * @return
	 */
	public LoginVO getUserByMobile(String mobile);
	
	public User getUserByUserId(String userId);

	UserTokenVO getUserTokenByToken(String token);

	UserTokenVO getUserTokenByUserId(String userId);
	
    public List<UserBasicInfo> searchUserByName(String name,int page);

	public int searchUserByNameCount(String name);

	void insertUserToken(UserTokenVO userTokenVO);

	void updateUserToken(String userId, String token);

	public void deleteUserToken(String userId);

}
