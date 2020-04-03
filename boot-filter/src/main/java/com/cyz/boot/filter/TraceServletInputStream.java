package com.cyz.boot.filter;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import java.io.IOException;

/**
 * @Author:cyz
 * @Date:2020/4/3 16:18
 * @Description:输入封装流
 */
public class TraceServletInputStream  extends ServletInputStream {
    ServletInputStream servletInputStream;

    private StringBuilder builder;

    public TraceServletInputStream(ServletInputStream inputStream) {
        this.servletInputStream = inputStream;
        builder = new StringBuilder();
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public boolean isReady() {
        return false;
    }

    @Override
    public void setReadListener(ReadListener readListener) {

    }

    @Override
    public int read() throws IOException {
        int data = servletInputStream.read();
        builder.append((char)data);
        return data;
    }

    @Override
    public int read(byte b[]) throws IOException {
        int data = servletInputStream.read(b);
        if(data > 0) {
            builder.append(new String(b));
        }
        return data;
    }

    @Override
    public int read(byte b[], int off, int len) throws IOException {
        int data = servletInputStream.read(b, off, len);
        if(data > 0) {
            builder.append(new String(b, off, data));
        }
        return data;
    }

    @Override
    public int readLine(byte[] b, int off, int len) throws IOException {
        int data = servletInputStream.readLine(b, off, len);
        if(data > 0) {
            builder.append(new String(b, off, data));
        }
        return data;
    }

    public String getContent() {
        return builder.toString();
    }

}
