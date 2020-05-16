package it.academy.contro.project.web.filter;

import it.academy.contro.project.web.WebUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/faculty")
public class AuntFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest rq = (HttpServletRequest) servletRequest;
        Object authUser = rq.getSession().getAttribute("userId");
        if (authUser == null) {
            WebUtils.forward("user", rq, ((HttpServletResponse) servletResponse));
            return;
        }
        filterChain.doFilter(rq, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
