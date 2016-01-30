package com.ffyc.server.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.nationsky.pub.log.Log;
import com.nationsky.pub.log.LogFactory;

@Component
public class TestInterceptor implements HandlerInterceptor
{
    Log log = LogFactory.getLog(TestInterceptor.class);

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
        String ip = getIpAddr(request);

        log.info("RECEIVE REQUEST: " + request.getRemoteAddr() + ":" + request.getRemotePort() + "  |   " + ip + ":" + request.getRemotePort() + "  -   " + request.getRequestURI() + "  -   " + request.getRemoteUser());
        return true;
    }

    private String getIpAddr(HttpServletRequest request)
    {
        String ip = request.getHeader(" x-forwarded-for ");
        if(ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip))
        {
            ip = request.getHeader(" Proxy-Client-IP ");
        }
        if(ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip))
        {
            ip = request.getHeader(" WL-Proxy-Client-IP ");
        }
        if(ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip))
        {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

}