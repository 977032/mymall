package com.ffyc.server.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;

import com.ffyc.server.mapper.MessageMapper;
import com.ffyc.server.service.PushService;
import com.ffyc.server.vo.MessageVO;

@Service("pushService")
public class PushServiceImpl implements PushService {
	
	@Autowired
	private MessageMapper messageMapper;
	
	private JPushClient pushClient = null;
	
	@Override
	public void sendUserMessage(MessageVO msg){
		
		String targetUserId = this.messageMapper.getPushUserId(msg.getToId());
		if(targetUserId==null)
			return;
		
		String fromId = msg.getFromId();
		String fromName = msg.getFromName();
		String content = msg.getContent();
		String contentPush = "{fromId:\""+fromId+"\",fromName:\""+fromName+"\",content:\""+content+"\"}";
		System.out.println(contentPush);
		Message m = Message.content(contentPush);
		PushPayload pb = PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.registrationId(targetUserId)).setMessage(m)
                .build();
		 try {
	            PushResult result = this.getPushClient().sendPush(pb);
	            System.out.println("Got result - " + result);

	        } catch (APIConnectionException e) {
	            // Connection error, should retry later
	        	System.out.println("Connection error, should retry later");

	        } catch (APIRequestException e) {
	            // Should review the error, and fix the request
	        	System.out.println("Should review the error, and fix the request");
	            System.out.println("HTTP Status: " + e.getStatus());
	            System.out.println("Error Code: " + e.getErrorCode());
	            System.out.println("Error Message: " + e.getErrorMessage());
	        }
	}
	
	
	public JPushClient getPushClient(){
		if(this.pushClient==null)
			this.pushClient = new JPushClient("a9b5d0bc8f9bd8727fbcfcd6", "977e696509b83755fcb947d0", 3);
		return this.pushClient;
	}
}
