package com.applyskillproject.config;

import com.applyskillproject.filter.EventMoneySenderFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<EventMoneySenderFilter> eventMoneySenderFilterRegistration() {
        FilterRegistrationBean<EventMoneySenderFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new EventMoneySenderFilter());
        registrationBean.addUrlPatterns("/*"); // 필터를 적용할 URL 패턴 설정
        registrationBean.setName("EventMoneySenderFilter"); // 필터의 이름 설정
        return registrationBean;
    }
}
