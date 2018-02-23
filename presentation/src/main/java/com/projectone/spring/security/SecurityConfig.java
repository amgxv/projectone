package com.projectone.spring.security;

import com.projectone.spring.security.CustomBasicAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * com.learningjava.full.spring.configuration.configuration
 * Class
 * By berto. 12/02/2018
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // Define el Realm
    public static String REALM = "MY_TEST_REALM";

    // Crea usuarios en memoria, y les define roles
    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("joanet").password("undostres").roles("ADMIN");
        auth.inMemoryAuthentication().withUser("tomevet").password("quatre").roles("USER");
    }


    // Configura la seguridad en las peticiones Http
    @Override
    protected void configure(HttpSecurity http) throws Exception {

    http.authorizeRequests()

            // Permite el acceso a todos
            .antMatchers("/", "/public/**","/css/**").permitAll()
            // Solo permite el acceso a los admins
            .antMatchers("/rest/api/v1/**").hasRole("ADMIN")
            .anyRequest().fullyAuthenticated()
            .and()
            // Define el formulario de login
            .formLogin()
            .loginPage("/login")
            .failureUrl("/login?error")
            .permitAll()
            .and()
            // Define el logout
            .logout()
            .logoutUrl("/logout")
            .deleteCookies("remember-me")
            .logoutSuccessUrl("/")
            .permitAll()
            .and()
            .rememberMe();

    }

    // Obtiene el Entrypoint de la Autenticación
    @Bean
    public CustomBasicAuthenticationEntryPoint getBasicAuthEntryPoint() {
        return new CustomBasicAuthenticationEntryPoint();
    }


}
