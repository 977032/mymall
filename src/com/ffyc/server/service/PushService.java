package com.ffyc.server.service;

import com.ffyc.server.vo.MessageVO;

public interface PushService {

	public void sendUserMessage(MessageVO msg);

}
