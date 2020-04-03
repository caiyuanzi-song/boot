package com.cyz.boot.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NamedThreadLocal;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @Author:cyz
 * @Date:2020/4/3 15:27
 * @Description:
 */
public class TraceInterceptor implements HandlerInterceptor {
    private static final Logger log = LoggerFactory.getLogger(TraceInterceptor.class);

    private NamedThreadLocal<Long> startTimeThreadLocal = new NamedThreadLocal<>("StopWatch-StartTime");

    /**
     * 预处理回调函数，进入controller之前执行
     * 如果返回true，则进入下一个拦截器，所有拦截器全部通过，则进入controller方法
     * 如果返回false，则请求被拦截
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        long beginTime = System.currentTimeMillis();
        startTimeThreadLocal.set(beginTime);

        String uri = request.getRequestURI();
        log.info("<------------------");
        log.info("request url:{}",uri);
        log.info("------------------>");

        return true;
    }

    /**
     * 后处理回调方法，经过controller处理之后执行，在此处可以对模型数据或对视图进行处理
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("entry postHandle");
    }

    /**
     * 整个请求处理完毕回调方法，即在视图渲染完毕时回调
     * 在此可以进行一些资源清理
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        long endTime = System.currentTimeMillis();
        long beginTime = startTimeThreadLocal.get();
        long consumeTime = endTime - beginTime;
        log.info("<********************************");
        log.info("consume time:{}"+consumeTime);
        log.info("********************************>");
    }
}
