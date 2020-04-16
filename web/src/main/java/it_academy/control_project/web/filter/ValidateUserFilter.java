package it_academy.control_project.web.filter;

import it_academy.control_project.data.User;
import it_academy.control_project.service.IUserService;
import it_academy.control_project.service.impl.DefaultUserService;
import it_academy.control_project.web.WebUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebFilter("/faculty")
public class ValidateUserFilter implements Filter {

    private IUserService userService = DefaultUserService.getInstance();
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest rq = (HttpServletRequest) servletRequest;
        Object userId = rq.getSession().getAttribute("userId");
        User user = userService.getUser((long) userId);

        Pattern patternName = Pattern.compile("^([^0-9]){3,}+$");
        Pattern patternPhone = Pattern.compile("^([+]?[0-9\\s-\\(\\)]{3,25})*$");
        Pattern patternEmail = Pattern.compile("(\\w+)@.*");

        Matcher matcher1 = patternName.matcher(user.getName());
        Matcher matcher2 = patternName.matcher(user.getSurname());
        Matcher matcher3 = patternPhone.matcher(user.getPhone());
        Matcher matcher4 = patternEmail.matcher(user.getEmail());
        if(!(matcher1.find() && matcher2.find() && matcher3.find() && matcher4.find())){
            WebUtils.forward("user", rq, ((HttpServletResponse) servletResponse));
            return;
        }
        filterChain.doFilter(rq, servletResponse);
    }

    @Override
    public void destroy() {

    }
}