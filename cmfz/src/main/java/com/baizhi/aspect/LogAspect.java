package com.baizhi.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * Created by Administrator on 2018/9/5 0005.
 */
@Aspect
@Configuration
public class LogAspect {
    @Around("@annotation(LogAnnotation)")//基于自定义注解切入,这次不基于切入点切入:好处可以拿到用户看的懂得方法名
    public void log(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        //什么人 什么时间 做了什么事 结果
        //注意方法里获得request的类 RequestContextHolder
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpSession session = servletRequestAttributes.getRequest().getSession();
        String str = (String) session.getAttribute("user");//到此可以找出那个人
        Date date = new Date();//时间
        //下面是获得调用了那个方法.
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();//proceedingJoinpoint是接入点
        Method method = signature.getMethod();
        LogAnnotation annotation = method.getAnnotation(LogAnnotation.class);
        String name = annotation.name();//至此获得调用的方法的 自定义汉子方法名

        //接下来看方法的执行结果
        boolean flag = false;
        try {
            Object proceed = proceedingJoinPoint.proceed();//代表目标方法的调用
            flag = true;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

    }
}
