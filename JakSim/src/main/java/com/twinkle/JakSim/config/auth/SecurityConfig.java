package com.twinkle.JakSim.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
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
    public SessionRegistry sessionRegistry(){
        return new SessionRegistryImpl();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.csrf().disable();
        http.authorizeHttpRequests().antMatchers( "/", "/trainerUpdate/**",
                        "/scheduler/**", "/trainerDelete", "/reservation/**",
                         "/trainer/trainerSearch",
                        "/javascript/**", "/css/**", "/image/**").permitAll()
                .antMatchers("/login/**", "/find/**", "/account/**", "/email/**").hasAnyRole("ANONYMOUS")
                .antMatchers("/trainer/trainerRegister/**").hasAuthority("USER_ROLE")
                .anyRequest().authenticated();
        http
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login/action")
                .successHandler(customAuthSuccessHandler)
                .failureHandler(customAuthFailureHandler);
        http.logout()
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true).logoutUrl("/logout").permitAll()
                .logoutSuccessUrl("/");
        http.sessionManagement(
                s -> s.sessionCreationPolicy(SessionCreationPolicy.ALWAYS) //STATELESS -> JWT 와 같은 클라이언트 토큰
                        .sessionFixation(SessionManagementConfigurer.SessionFixationConfigurer::changeSessionId)
                        .maximumSessions(3)
                        .maxSessionsPreventsLogin(true)
                        .expiredUrl("/session-expired")
        );

        return http.build();
    }
}