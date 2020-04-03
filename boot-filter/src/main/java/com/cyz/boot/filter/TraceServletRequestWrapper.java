package com.cyz.boot.filter;

import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
/**
 * @Author:cyz
 * @Date:2020/4/3 16:21
 * @Description:请求封装
 */
public class TraceServletRequestWrapper extends HttpServletRequestWrapper {
    TraceServletInputStream traceInputStream;

    public TraceServletRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        if (traceInputStream == null) {
            traceInputStream = new TraceServletInputStream(super.getInputStream());
        }
        return traceInputStream;
    }

    public TraceServletInputStream getTraceInputStream() {
        return traceInputStream;
    }

}
