package com.library_management.service.impl;

import com.library_management.dto.request.user.RegisterRequest;
import com.library_management.entity.User;
import com.library_management.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class UserServiceImpl {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public boolean register(RegisterRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .fullName(request.getFullName())
                .phoneNumber(request.getPhoneNumber())
                .imageUrl(request.getImageUrl())
                .role(User.Role.CLIENT)
                .note(request.getNote())
                .createdAt(new Date())
                .updatedAt(new Date())
                .registeredAt(new Date())
                .build();

        userRepo.save(user);
        return true;
    }

    public User getCurrentUser() {
        UsernamePasswordAuthenticationToken user
                = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        String username = ((org.springframework.security.core.userdetails.User) user.getPrincipal()).getUsername();
        return userRepo.findFirstByUsername(username);
    }
}
