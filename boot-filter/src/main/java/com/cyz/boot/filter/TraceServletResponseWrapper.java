package com.cyz.boot.filter;

import javax.servlet.http.HttpServletResponseWrapper;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
/**
 * @Author:cyz
 * @Date:2020/4/3 16:21
 * @Description:响应封装
 */
public class TraceServletResponseWrapper extends HttpServletResponseWrapper {
    private TraceServletOutputStream traceOutputStream;
    private HttpServletResponse response;

    public TraceServletResponseWrapper(HttpServletResponse response) {
        super(response);
        this.response = response;
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        return null;
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        if (null == traceOutputStream) {
            traceOutputStream = new TraceServletOutputStream(super.getOutputStream());
        }
        return traceOutputStream;
    }

    public TraceServletOutputStream getTraceOutputStream() {
        return traceOutputStream;
    }
}
