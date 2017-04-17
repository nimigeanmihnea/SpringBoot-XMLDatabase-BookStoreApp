package application;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

/**
 * Created by MIHONE on 4/8/2017.
 */

@Configuration
public class MVCConfig extends WebMvcConfigurerAdapter{

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer){
        configurer.enable();
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry){

        registry.addViewController("/").setViewName("login");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/admin").setViewName("admin");
        registry.addViewController("/view").setViewName("view");
        registry.addViewController("/sell").setViewName("sell");
        registry.addViewController("/admin").setViewName("admin");
        registry.addViewController("/403").setViewName("403");
        registry.addViewController("/errorpage").setViewName("errorpage");
        registry.addViewController("/admin/view").setViewName("admin/view");
        registry.addViewController("/admin/edit").setViewName("admin/edit");
        registry.addViewController("/admin/add").setViewName("admin/add");
        registry.addViewController("/admin/new").setViewName("admin/new");
        registry.addViewController("/admin/user").setViewName("admin/user");
        registry.addViewController("/admin/edituser").setViewName("admin/edituser");
    }
}
