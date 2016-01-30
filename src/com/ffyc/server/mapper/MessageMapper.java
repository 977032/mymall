package com.ffyc.server.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ffyc.server.vo.MessageVO;


public interface MessageMapper {
	
	public List<MessageVO> getSystemMessageList(@Param("userId") String userId,@Param("start") int start, @Param("count") int count);
	
	public int getSystemMessageListCount(@Param("userId") String userId);
	
	public List<MessageVO> getUserMessageList(@Param("userId") String userId,@Param("me") String me,@Param("start") int start, @Param("count") int count);
	
	public int getUserMessageListCount(@Param("userId") String userId,@Param("me") String me);
	
	public void insertUserMessage(MessageVO userMessage);
	
	public void insertOrUpdateUserFriend(@Param("userId")String userId,@Param("friendId")String friendId,@Param("lastMsgId")String lastMsgId);
	
	public List<MessageVO> getMessageList(@Param("userId") String userId,@Param("start") int start, @Param("count") int count);

	public int getMessageListCount(@Param("userId") String userId);
		
	public void insertOrUpdatePushTarget(@Param("userId") String userId,@Param("pushUserId") String pushUserId);
	
	public void deletePushTarget(@Param("pushUserId") String pushUserId);
	
	public String getPushUserId(@Param("userId") String userId);
	
	public void deleteUserFriendMessage(@Param("userId")String userId,@Param("friendId")String friendId);
	
	public void refreshUserFriendLastMessage(@Param("userId")String userId,@Param("friendId")String friendId);
	
	public void deleteUserMessage(@Param("userId")String userId,@Param("id")String id);
	
	public HashMap<String,String> getUserMessageToFromId(@Param("id")String id);
	
	public void insertSystemMsgDelete(@Param("userId")String userId,@Param("msgId")String msgId);

	
}
