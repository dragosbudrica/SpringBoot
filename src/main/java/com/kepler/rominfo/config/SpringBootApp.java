package com.kepler.rominfo.config;

import com.kepler.rominfo.filter.AuthenticationFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.filter.CharacterEncodingFilter;
import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.EnumSet;

@SpringBootApplication
public class SpringBootApp extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootApp.class, args);
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);
        servletContext.setAttribute("Constants", com.kepler.rominfo.utils.Constants.class);
        EnumSet<DispatcherType> disps = EnumSet.allOf(DispatcherType.class);

        FilterRegistration.Dynamic encodingFilter = servletContext.addFilter("encoding-filter", CharacterEncodingFilter.class);
        encodingFilter.addMappingForUrlPatterns(disps, false, "/*");
        encodingFilter.setInitParameter("encoding", "UTF-8");

        FilterRegistration.Dynamic authenticationFilter = servletContext.addFilter("authentication-filter", AuthenticationFilter.class);
        authenticationFilter.addMappingForUrlPatterns(null, false, "/*");
    }
}
