package it.academy.vladsin.contro.project.web.config;

import it.academy.vladsin.contro.project.web.controller.*;
import it.academy.vladsin.control.project.service.config.ServiceConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
public class WebConfig {

    private ServiceConfig serviceConfig;

    public WebConfig(ServiceConfig serviceConfig) {
        this.serviceConfig = serviceConfig;
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
    public ViewResolver viewResolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setSuffix(".jsp");
        return resolver;
    }
}
