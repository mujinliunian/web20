package com.kaishengit.web.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ValidateFilter extends AbstractFilter {

    String staticPath = null;
    List<String> excludePathList = new ArrayList<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        staticPath = filterConfig.getInitParameter("staticPath");

        String excludePath = filterConfig.getInitParameter("excludePath");
        String[] paths = excludePath.split(",");
        excludePathList = Arrays.asList(paths);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //获取用户请求的路径
        String url = request.getRequestURI();
        System.out.println("url:" + url);

        //如果是静态资源则直接通过
        if(url.startsWith(staticPath)) {
            filterChain.doFilter(request,response);
        } else {
            //跟登录有关的资源直接放行
            if(excludePathList.contains(url)) {
                filterChain.doFilter(request, response);
            } else {
                HttpSession session = request.getSession();
                if(session.getAttribute("user") == null) {
                    //说明当前客户端未登录，需要重定向到登录页面
                    response.sendRedirect("/index.do?code=1002");
                } else {
                    //已登录用户，则直接通过
                    filterChain.doFilter(request,response);
                }
            }
        }



    }


}
