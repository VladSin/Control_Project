package it.academy.vladsin.contro.project.web.config;

import it.academy.vladsin.contro.project.web.controller.*;
import it.academy.vladsin.control.project.service.config.ServiceConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;

import java.util.Locale;

@Configuration
@EnableWebMvc
public class WebConfig {

    private ServiceConfig serviceConfig;

    public WebConfig(ServiceConfig serviceConfig) {
        this.serviceConfig = serviceConfig;
    }

    @Bean
    public FirstPageController firstPageController(){
        return new FirstPageController();
    }

    @Bean
    public AfterExamController afterExamController(){
        return new AfterExamController(serviceConfig.examService(), serviceConfig.applicantService(), serviceConfig.facultyService());
    }

    @Bean
    public ExamController examController(){
        return new ExamController(serviceConfig.applicantService(), serviceConfig.studentService());
    }

    @Bean
    public FacultyController facultyController(){
        return new FacultyController(serviceConfig.facultyService());
    }

    @Bean
    public LoginController loginController(){
        return new LoginController(serviceConfig.securityService());
    }

    @Bean
    public LogoutController logoutController(){
        return new LogoutController(serviceConfig.securityService());
    }

    @Bean
    public StudentsController studentsController(){
        return new StudentsController(serviceConfig.studentService(), serviceConfig.userService());
    }

    @Bean
    public TeacherController teacherController(){
        return new TeacherController(serviceConfig.applicantService(), serviceConfig.userService(), serviceConfig.facultyService());
    }

    @Bean
    public UserController userController(){
        return new UserController(serviceConfig.userService());
    }

    @Bean
    public UrlBasedViewResolver tilesViewResolver(){
        UrlBasedViewResolver resolver = new UrlBasedViewResolver();
        resolver.setViewClass(TilesView.class);
        return resolver;
    }

    @Bean
    public TilesConfigurer tilesConfigurer(){
        final TilesConfigurer tilesConfigurer = new TilesConfigurer();
        tilesConfigurer.setDefinitions("/WEB-INF/tiles.xml");
        return tilesConfigurer;
    }

//    @Bean
//    public ReloadableResourceBundleMessageSource messageSource(){
//        ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
//        source.setBasename("classpath:i18n/messages");
//        source.setDefaultEncoding("UTF-8");
//
//        return source;
//    }

    @Bean
    public CookieLocaleResolver localeResolver(){
        CookieLocaleResolver resolver = new CookieLocaleResolver();
        resolver.setDefaultLocale(Locale.forLanguageTag("en"));
        resolver.setCookieName("LocaleCookie");
        resolver.setCookieMaxAge(3600);
        return resolver;
    }
}
