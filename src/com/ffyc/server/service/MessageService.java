package com.ffyc.server.service;

import java.util.List;

import com.ffyc.server.vo.MessageVO;
import com.ffyc.server.model.UserMessage;

public interface MessageService {

	/**
	 * 获取系统信息列表
	 * @param userId
	 * @param page
	 * @return
	 */
	public List<MessageVO> getSystemMessageList(String userId, int page);

	/**
	 * 获取聊天记录
	 * @param userId
	 * @param me
	 * @param page
	 * @return
	 */
	public List<MessageVO> getUserMessageList(String userId, String me, int page);


	public String insertUserMessage(MessageVO userMessage);

	/**
	 * 更新用户好友信息
	 * @param userId
	 * @param friendId
	 * @param lastMsgId
	 */
	public void updateUserFriend(String userId, String friendId, String lastMsgId);

	/**
	 * 获取消息列表
	 * @param userId
	 * @param page
	 * @return
	 */
	public List<MessageVO> getMessageList(String userId, int page);

	/**
	 * 设置推送ID
	 * @param userId
	 * @param pushUserId
	 */
	public void setPushUserId(String userId, String pushUserId);

	/**
	 * 删除用户聊天记录
	 * @param userId
	 * @param friendId
	 */
	public void deleteUserFriendMessage(String userId, String friendId);

	/**
	 * 删除聊天消息
	 * @param userId
	 * @param id
	 */
	public void deleteUserMessage(String userId, String id);

	/**
	 * 删除系统消息
	 * @param userId
	 * @param msgId
	 */
	public void deleteSystemMessage(String userId, String msgId);
	
	

}
