package com.cyz.boot.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @Author:cyz
 * @Date:2020/4/3 16:03
 * @Description:拦截器
 */
public class TraceFilter extends GenericFilterBean {
    private static final Logger log = LoggerFactory.getLogger(TraceFilter.class);

    @Override
    protected void initFilterBean() throws ServletException {
        log.info("init Trace Filter");
        super.initFilterBean();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        long startTime = System.currentTimeMillis();
        // 封装流
        TraceServletRequestWrapper requestWrapper = new TraceServletRequestWrapper(request);
        TraceServletResponseWrapper responseWrapper = new TraceServletResponseWrapper(response);
        // 执行
        chain.doFilter(requestWrapper, responseWrapper);
        long endTime = System.currentTimeMillis();
        // 执行日志打印
        String requestBody = getRequestBody(requestWrapper);
        String responseBody = getResponseBody(responseWrapper);
        StringBuilder builder = new StringBuilder();
        if (requestBody == null || requestBody.isEmpty()) {
            requestBody = "{}";
        }
        if (responseBody == null || responseBody.isEmpty()) {
            responseBody = "{}";
        }
        builder.append("请求url:").append(request.getRequestURL()).append("|");
        builder.append("耗时:").append(endTime - startTime).append("-");
        builder.append("请求内容:").append(requestBody).append(",");
        builder.append("返回内容:").append(responseBody).append(",");
        logger.info(builder.toString());
    }

    private String getResponseBody(TraceServletResponseWrapper responseWrapper) {
        TraceServletOutputStream traceOutputStream = responseWrapper.getTraceOutputStream();
        String content;
        if ((null != traceOutputStream) && (content = traceOutputStream.getContent()) != null && !content.isEmpty()) {
            return new String(content.getBytes(), StandardCharsets.UTF_8);
        }
        return null;
    }

    private String getRequestBody(TraceServletRequestWrapper requestWrapper) {
        TraceServletInputStream traceInputStream = requestWrapper.getTraceInputStream();
        if (traceInputStream != null) {
            return new String(traceInputStream.getContent().getBytes(), StandardCharsets.UTF_8);
        }
        return null;
    }

}
