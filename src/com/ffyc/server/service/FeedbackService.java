package com.ffyc.server.service;

public interface FeedbackService
{
    /**
     * 提交意见反馈
     * 
     * @param userId 用户id
     * @param feedback 意见反馈内容
     */
    public void submitFeedback(String userId, String feedback);
}
