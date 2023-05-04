package org.coursework.project_warehouse.config;

import lombok.RequiredArgsConstructor;
import org.coursework.project_warehouse.service.SecurityDetailsService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.IOException;

@RequiredArgsConstructor

@Configuration
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true
)
//old config
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final SecurityDetailsService service;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/loginpage", "infopage").permitAll()
//                .antMatchers("/test").permitAll()
                .antMatchers("/mainpage").authenticated()
                .antMatchers("/pageread").hasAnyAuthority("read", "write")
                .antMatchers("/pagewrite").hasAuthority("write")
                .antMatchers("/pageuser").hasRole("USER")
                .antMatchers("/pageadmin").hasRole("ADMIN")
                .antMatchers("/store/**").permitAll() //обозначает ** что ко всему ресурсу что начинается на /store/ есть доступ
                .antMatchers("/create").permitAll()
                .and()
                .cors().disable()
                .formLogin()
                .loginPage("/loginpage").loginProcessingUrl("/try-login")
                .successHandler((request, response, authentication) -> {
                    response.sendRedirect("/mainpage");
                })
                //используем заданные нами login & pass вместо username & password
                .usernameParameter("login")
                .passwordParameter("pass")
                .and()
                .logout()
                .logoutUrl("/try-logout")
                .addLogoutHandler((request, response, authentication) -> {
                    try {
                        response.sendRedirect("/infopage");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                })
                .and()
                .httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        auth.userDetailsService(service)
                .passwordEncoder(bCryptPasswordEncoder);
    }
}