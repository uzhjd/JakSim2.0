package com.twinkle.JakSim.config.auth;

import com.twinkle.JakSim.model.dto.account.UserDto;
import com.twinkle.JakSim.model.dao.account.UserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private UserDao userDao;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDto siteUser = null;

        siteUser = userDao.findByUserId(username);

        if(siteUser == null){
            throw new UsernameNotFoundException("user not found");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        if(siteUser.getRole() == 0){
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }else if(siteUser.getRole() == 1){
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }else if(siteUser.getRole() == 2){
            authorities.add(new SimpleGrantedAuthority("ROLE_TRAINER"));
        }

        return new User(siteUser.getId(), siteUser.getPw(), authorities);
    }
}
