package com.ffyc.server.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nationsky.pub.log.Log;
import com.nationsky.pub.log.LogFactory;
import com.nationsky.pub.utils.GsonUtils;
import com.ffyc.server.controller.ServiceBusController;

@Aspect
public class ServiceBusLogInterceptor
{
    private static final Log log = LogFactory.getLog(ServiceBusController.class);

    @Pointcut("execution(* com.ffyc.server.controller.ServiceBusController.*(..))")
    private void log()
    {
    }

    @Around("log()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable
    {
        MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();

        RequestMapping requestMapping = (RequestMapping)methodSignature.getMethod().getAnnotation(RequestMapping.class);

        String[] names = methodSignature.getParameterNames();
        Class<?>[] types = methodSignature.getParameterTypes();
        Object[] values = joinPoint.getArgs();
        Map<String, String> params = new HashMap<String, String>();

        if(names != null)
        {
            int i = 0;
            for(String name: names)
            {
                if(HttpServletRequest.class == types[i])
                {
                    continue;
                }
                if(HttpServletResponse.class == types[i])
                {
                    continue;
                }
                if(HttpSession.class == types[i])
                {
                    continue;
                }

                params.put(name, values[i] == null ? "" : GsonUtils.toGson(values[i]));
                i++;
            }
        }

        log.info("RECEIVE-REQUEST | URI: " + (requestMapping.value().length > 0 ? requestMapping.value()[0] : "-") + ", METHOD: " + (requestMapping.method().length > 0 ? requestMapping.method()[0] : "-") + ", PARAMS: " + params);

        return joinPoint.proceed();
    }
}