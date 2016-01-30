package com.ffyc.server.interceptor;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class ResourceInterceptor implements HandlerInterceptor
{


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object data, Exception exception) throws Exception
    {

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object data, ModelAndView mav) throws Exception
    {

    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object data) throws Exception
    {
        String id = new File(request.getRequestURI()).getParentFile().getName();
        String userId = request.getParameter("userId");

        /*
        if(collegeMapper.isCourseActived(userId, id) == 0)
        {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "您的课程未激活或已到期");
        }*/
        
        System.err.println("url: "+request.getRequestURL());
        System.err.println("uri: "+request.getRequestURI());

        return true;
    }

}