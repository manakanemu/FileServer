package com.simplefileserver.filter;

import com.simplefileserver.entity.Remote;
import com.simplefileserver.entity.Request;
import com.simplefileserver.service.FileService;
import com.simplefileserver.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

@WebFilter(urlPatterns = "/file/*")
public class StaticResourcesFilter implements Filter {
    @Autowired
    RequestService requestService;
    @Autowired
    FileService fileService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String servletPath = ((HttpServletRequest) servletRequest).getServletPath();
        System.out.println("request path:" + servletPath);
        Request request = new Request();
        request.setFilename(((HttpServletRequest) servletRequest).getServletPath());
        request.setIp(servletRequest.getRemoteAddr());
        request.setTime(new Timestamp(new Date().getTime()));
        requestService.recordRequest(request);
        if (request.getFilename().contains("remote:")) {
            Remote remote = fileService.getRemoteFile(request.getFilename().substring(request.getFilename().indexOf("remote:")));
            ((HttpServletResponse) servletResponse).sendRedirect(remote.getUrl());
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }

    }

    @Override
    public void destroy() {

    }
}
