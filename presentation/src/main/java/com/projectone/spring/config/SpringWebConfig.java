package com.projectone.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

/**
 * projectone
 * com.projectone.spring.config
 * Created by : tech in 18/02/18.
 * Description :
 */
@EnableWebMvc
@Configuration
@ComponentScan({"com.baeldung.freemarker"})
public class SpringWebConfig extends WebMvcConfigurerAdapter {


}
