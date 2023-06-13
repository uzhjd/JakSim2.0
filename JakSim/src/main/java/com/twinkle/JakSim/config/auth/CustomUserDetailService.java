package com.twinkle.JakSim.config.auth;

import com.twinkle.JakSim.model.dto.account.UserDto;
import com.twinkle.JakSim.model.dao.account.UserDao;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jdbc.pool.DataSource;
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
    private DataSource ds;

    /**
     * 확인해보니 얘는 그냥 데이터를 불러와서 인증할 때 사용하도록 만들어주는 것에 불과함
     * @param username the username identifying the user whose data is required.
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDao userDao = new UserDao(ds);
        UserDto siteUser = null;

        siteUser = userDao.findByUserId(username);

        System.out.println("[UserDetailService]");
        System.out.println(siteUser.toString());

        List<GrantedAuthority> authorities = new ArrayList<>();
        if("admin".equals(username)){
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }else{
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }

        return new User(siteUser.getId(), siteUser.getPw(), authorities);
        //Parameter -> username, password, Authority
    }
}
