package com.twinkle.JakSim.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import javax.servlet.Filter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private CustomAuthSuccessHandler customAuthSuccessHandler;
    @Autowired
    private CustomAuthFailureHandler customAuthFailureHandler;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.csrf().disable();
        http.authorizeHttpRequests().antMatchers( "/account/**", "/", "/trainer/**",
                "/javascript/**", "/css/**", "/image/**").permitAll()
                .antMatchers("/login/**", "/find/**").hasAnyRole("ANONYMOUS")
                .anyRequest().authenticated()
                        .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login/action")
                .successHandler(customAuthSuccessHandler)
                .failureHandler(customAuthFailureHandler)
                .usernameParameter("username")
                .passwordParameter("password");
        http.logout()
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true).logoutUrl("/logout").permitAll()
                .logoutSuccessUrl("/");

        return http.build();
    }
}
