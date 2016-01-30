package com.ffyc.server.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class DocInterceptor implements HandlerInterceptor
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
    	return true;
    	
        /*if("woshinibaba".equals(request.getParameter("who")))
        {
            return true;
        }
        else
        {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "你没有这个权限");
            return false;
        }*/
    }

}