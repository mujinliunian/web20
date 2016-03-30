package com.kaishengit.web.filter;

import javax.servlet.*;
import java.io.IOException;

public class HelloFilter extends AbstractFilter {
    private String encoding = "UTF-8";
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String value = filterConfig.getInitParameter("encoding");
        if(value != null && !"".equals(value.trim())) {
            encoding = value;
        }
    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        servletRequest.setCharacterEncoding(encoding);
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
