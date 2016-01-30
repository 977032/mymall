package com.ffyc.server.service.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ffyc.server.common.PageCalc;
import com.ffyc.server.common.utils.StringUtil;
import com.ffyc.server.mapper.MessageMapper;
import com.ffyc.server.vo.MessageVO;
import com.ffyc.server.service.PushService;
import com.ffyc.server.service.MessageService;

@Service("messageService")
public class MessageServiceImpl implements MessageService{
	
	@Autowired
	private MessageMapper messageMapper;
	
	@Autowired
	private PushService pushService;

	@Override
	public List<MessageVO> getSystemMessageList(String userId, int page){
		PageCalc pageCalc = new PageCalc(this.messageMapper.getSystemMessageListCount(userId));
		List<MessageVO> list = this.messageMapper.getSystemMessageList(userId, pageCalc.getStart(page), pageCalc.getPageSize());
        return list;
	}
	
	@Override
	public List<MessageVO> getUserMessageList(String userId,String me,int page){
		PageCalc pageCalc = new PageCalc(this.messageMapper.getUserMessageListCount(userId, me));
		List<MessageVO> list = this.messageMapper.getUserMessageList(userId, me, pageCalc.getStart(page), pageCalc.getPageSize());
        return list;
	}
	
	@Override
	public String insertUserMessage(MessageVO userMessage){
		this.messageMapper.insertUserMessage(userMessage);
		String msgId = userMessage.getId();
		String fromId = userMessage.getFromId();
		String toId = userMessage.getToId();
		this.updateUserFriend(fromId, toId, msgId);
		this.updateUserFriend(toId, fromId, msgId);
		this.pushService.sendUserMessage(userMessage);
		return msgId;
	}
	
	@Override
	public void deleteUserFriendMessage(String userId,String friendId){
		this.messageMapper.deleteUserFriendMessage(userId, friendId);
	}
	
	@Override
	public void deleteUserMessage(String userId,String id){
		HashMap<String,String> toFrom = this.messageMapper.getUserMessageToFromId(id);
		this.messageMapper.deleteUserMessage(userId, id);
		if(toFrom!=null){
			String toId = toFrom.get("toId");
			String fromId = toFrom.get("fromId");
			if(toId!=null&&fromId!=null){
				this.messageMapper.refreshUserFriendLastMessage(fromId, toId);
				this.messageMapper.refreshUserFriendLastMessage(toId, fromId);
			}
		}
	}
	
	@Override
	public void updateUserFriend(String userId,String friendId,String lastMsgId){
		this.messageMapper.insertOrUpdateUserFriend(userId, friendId, lastMsgId);
	}
	
	@Override
	public List<MessageVO> getMessageList(String userId,int page){
		PageCalc pageCalc = new PageCalc(this.messageMapper.getMessageListCount(userId));
		List<MessageVO> list = this.messageMapper.getMessageList(userId, pageCalc.getStart(page), pageCalc.getPageSize());
        return list;
	}
	
	@Override
	public void setPushUserId(String userId,String pushUserId){
		this.messageMapper.deletePushTarget(pushUserId);
		this.messageMapper.insertOrUpdatePushTarget(userId, pushUserId);
	}
	
	@Override
	public void deleteSystemMessage(String userId,String msgId){
		this.messageMapper.insertSystemMsgDelete(userId, msgId);
	}
}
