package ac.app.aop;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

@Component
@Aspect
public class UserAspect {
    private static final Logger logger = LoggerFactory.getLogger(UserAspect.class);

    @Pointcut("execution(* ac.app.controller..*.*(..))")
    public void UserIntercept() {
    }

    @Before("UserIntercept()")
    public void doBeforeAdvice(JoinPoint joinPoint) {

        //可以通过获取JoinPoint的Signature字段，来获取被代理的方法的信息
        //如下为获取http请求的相关参数
        logger.info("Doing before-advice.");
        logger.info("Try to intercept http request params.");
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = (HttpServletRequest) requestAttributes
                .resolveReference(RequestAttributes.REFERENCE_REQUEST);

        Enumeration<String> enumeration = request.getParameterNames();
        while (enumeration.hasMoreElements()) {
            String parameter = enumeration.nextElement();
            logger.info(parameter + " : " + request.getParameter(parameter));
        }
        logger.info("Finishing before-advice.");
    }
    /**
     * 通知的方式还有doAround和doAfter
     */
}
