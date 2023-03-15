//package com.cg.config;
//
//import com.cg.formatter.RoleFormatter;
//import com.cg.service.role.RoleServiceImpl;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.BeansException;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ApplicationContextAware;
//import org.springframework.context.annotation.Bean;
//import org.springframework.format.FormatterRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//
//public class AppConfiguration  implements WebMvcConfigurer, ApplicationContextAware {
//
//    private ApplicationContext applicationContext;
//
//    @Override
//    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//        this.applicationContext = applicationContext;
//    }
//    @Override
//    public void addFormatters(FormatterRegistry registry) {
//        registry.addFormatter(new RoleFormatter(applicationContext.getBean(RoleServiceImpl.class)));
//    }
//    @Bean
//    public ModelMapper modelMapper() {
//        return new ModelMapper();
//    }
//}
