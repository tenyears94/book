package cn.javamap.book.configuration;

import cn.javamap.book.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 实现 MVC 的部分配置
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 注册拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration interceptor = registry.addInterceptor(new LoginInterceptor());
        interceptor.excludePathPatterns("/static/**");//放行静态资源
        interceptor.excludePathPatterns("/layui/**");//放行静态资源
        interceptor.excludePathPatterns("/style/**");//放行静态资源
        interceptor.addPathPatterns("/**");            //添加所有路径被拦截
    }

    /**
     * 自定义资源映射
     * @param registry
     */
    /*@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }*/
}
