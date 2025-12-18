package com.fpj.report.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author fangpengjun
 * @date 2025/12/18
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 将根路径 "/" 重定向到 /test.html
        registry.addRedirectViewController("/", "/report_list.html");
    }
}
