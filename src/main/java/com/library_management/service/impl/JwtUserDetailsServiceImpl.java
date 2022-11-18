package com.library_management.service.impl;

import com.library_management.constant.Constant;
import com.library_management.entity.Config;
import com.library_management.repository.UserRepo;
import lombok.RequiredArgsConstructor;
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
public class JwtUserDetailsServiceImpl implements UserDetailsService {
    private final UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.library_management.entity.User user = userRepo.findFirstByUsername(username);

        if (user != null) {
            return new User(user.getUsername(), user.getPassword(), this.getAuthority(user));
        } else {
            throw new UsernameNotFoundException("username not found with username = " + username);
        }
    }

    private List<GrantedAuthority> getAuthority(com.library_management.entity.User user) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(Constant.ROLE_PREFIX + user.getRole()));
        return authorities;
    }

}
