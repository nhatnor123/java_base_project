package com.library_management.controller;

import com.library_management.config.jwtConfig.JwtTokenUtil;
import com.library_management.dto.ResponseObject;
import com.library_management.dto.request.user.LoginRequest;
import com.library_management.dto.request.user.LoginResponse;
import com.library_management.dto.request.user.RegisterRequest;
import com.library_management.service.impl.JwtUserDetailsServiceImpl;
import com.library_management.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(path = "/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserServiceImpl userService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final JwtUserDetailsServiceImpl jwtUserDetailsService;

    @PostMapping(value = "/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {

        return ResponseEntity.ok(new ResponseObject<>(true, "SUCCESS", "Đăng ký tài khoản thành công", userService.register(request)));
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginRequest loginRequest) throws Exception {
        this.authenticate(loginRequest.getUsername(), loginRequest.getPassword());
        final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(loginRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new ResponseObject<>(true, "SUCCESS", "Đăng nhập thành công", token));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLE", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }


}
