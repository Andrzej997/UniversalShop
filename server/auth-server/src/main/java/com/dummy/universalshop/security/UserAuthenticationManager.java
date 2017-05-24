package com.dummy.universalshop.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class UserAuthenticationManager implements AuthenticationManager {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (authentication.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return authentication;
        } else {
            if (authentication.getPrincipal() == null || authentication.getCredentials() == null) {
                throw new BadCredentialsException("Bad username/password provided!");
            }
            String username = authentication.getPrincipal().toString();
            String password = authentication.getCredentials().toString();

            if (!StringUtils.isEmpty(username) && !StringUtils.isEmpty(password)) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                if (!userDetails.isEnabled()) {
                    throw new BadCredentialsException("User is not enabled");
                }

                if (bCryptPasswordEncoder.matches(password, userDetails.getPassword())) {
                    Authentication auth = new UsernamePasswordAuthenticationToken(username, password,
                            userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(auth);

                    return auth;
                } else {
                    throw new BadCredentialsException("Bad username/password provided!");
                }
            }

            throw new BadCredentialsException("Bad credentials provided!");
        }
    }
}
