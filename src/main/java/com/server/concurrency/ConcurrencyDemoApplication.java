package com.server.concurrency;


import com.server.concurrency.examples.ThreadLocal.MyFilter;
import com.server.concurrency.examples.ThreadLocal.MyInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@RestController
@SpringBootApplication
public class ConcurrencyDemoApplication extends WebMvcConfigurerAdapter {


    public static void main(String[] args) {
        SpringApplication.run(ConcurrencyDemoApplication.class, args);
    }

    @GetMapping("/test")
    public void test(){

    }

    @Bean
    public FilterRegistrationBean httpFilter(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new MyFilter());
        filterRegistrationBean.addUrlPatterns("/threadLocal/*");
        return filterRegistrationBean;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**");
    }
}
