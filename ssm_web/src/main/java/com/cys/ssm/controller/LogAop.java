package com.cys.ssm.controller;

import com.cys.ssm.domain.SysLog;
import com.cys.ssm.service.SysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
public class LogAop {

    @Autowired
    private HttpServletRequest request;//需要在web中配置

    @Autowired
    private SysLogService sysLogService;

    private Date visitTime; //访问开始的时间
    private Class clazz; //访问的类
    private Method method;//访问的方法

    /**
     * 前置通知，拦截所有controller下的方法
     * 用于获取开始时间，执行的类是哪一个，执行的是哪一个方法
     *
     * @param jp
     */
    @Before("execution(* com.cys.ssm.controller.*.*(..)) && !execution(* com.cys.ssm.controller.LogAop.*(..))")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {
        visitTime = new Date(); //当前的时间就是开始访问的时间
        clazz = jp.getTarget().getClass();//具体要访问的类对象
        String methodName = jp.getSignature().getName();//获取访问的方法的名称

        Object[] args = jp.getArgs();//获取访问的方法的参数
        // 获取具体执行的方法的Method对象
        if (args == null || args.length == 0) {
            //如果无参数
            method = clazz.getMethod(methodName);//只能获取无参数的方法
        } else {
            Class[] classArgs = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                classArgs[i] = args[i].getClass();
            }
            clazz.getMethod(methodName, classArgs);
        }
    }


    @After("execution(* com.cys.ssm.controller.*.*(..))&& !execution(* com.cys.ssm.controller.LogAop.*(..))")
    public void doAfter(JoinPoint jp) throws Exception {
        long time = new Date().getTime() - visitTime.getTime();//获取访问的时长

        //获取访问的url
        String url = "";
        /*思路：通过反射来完成操作*/
        if (clazz != null && method != null && clazz != LogAop.class) {
            //1、获取类上的@requestMapping("/orders")
            RequestMapping clazzAnnotation = (RequestMapping) clazz.getAnnotation(RequestMapping.class);

            if (clazzAnnotation != null) {
                //1、根据注解上的value获取值
                String[] classValue = clazzAnnotation.value();

                //2、获取方法上的@RequestMapping
                RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);

                if (methodAnnotation != null) {
                    String[] methodValue = methodAnnotation.value();

                    //获取到url
                    url = classValue[0] + methodValue[0];



                    //获取当前访问的ip地址
                    String ip = request.getRemoteAddr();

                    //获取当前访问的操作者
                    /*通过spring SecurityContext对象*/
                    SecurityContext context = SecurityContextHolder.getContext();//从上下文中获取当前登录的用户
                    User user = (User) context.getAuthentication().getPrincipal();
                    String username = user.getUsername();//获取当前访问操作者的用户名

                    /*将日志信息封装到syslog对象中*/
                    SysLog sysLog = new SysLog();
                    sysLog.setExecutionTime(time);
                    sysLog.setId(ip);
                    sysLog.setMethod("[类名]"+clazz.getName()+"[方法名]"+method.getName());
                    sysLog.setUrl(url);
                    sysLog.setUsername(username);
                    sysLog.setVisitTime(visitTime);


                    //调用Service完成记录日志操作
                    sysLogService.saveLog(sysLog);
                }
            }
        }//if


    }
}
