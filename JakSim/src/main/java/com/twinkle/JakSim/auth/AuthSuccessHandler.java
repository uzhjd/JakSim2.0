package com.twinkle.JakSim.auth;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.Principal;

@Component
public class AuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    @Autowired
    private DataSource ds;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        //Spring Context Holder에 인증정보를 저장하게 됨
        SecurityContextHolder.getContext().setAuthentication(authentication);

        setDefaultTargetUrl("/");
        super.onAuthenticationSuccess(request, response, chain, authentication);
    }
}
