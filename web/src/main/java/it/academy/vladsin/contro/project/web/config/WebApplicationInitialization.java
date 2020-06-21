package it.academy.vladsin.contro.project.web.config;

import it.academy.vladsin.control.project.dao.config.DaoConfig;
import it.academy.vladsin.control.project.service.config.ServiceConfig;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

public class WebApplicationInitialization extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{
                RootConfig.class,
                WebSecurityConfig.class,
                ResourceConfig.class
        };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{
                WebConfig.class,
                ServiceConfig.class,
                DaoConfig.class
        };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected Filter[] getServletFilters() {
//        DelegatingFilterProxy delegateFilterProxy = new DelegatingFilterProxy();
//        delegateFilterProxy.setTargetBeanName("springSecurityFFilterChain");
//        return new Filter[]{delegateFilterProxy};
        return super.getServletFilters();
    }
}
