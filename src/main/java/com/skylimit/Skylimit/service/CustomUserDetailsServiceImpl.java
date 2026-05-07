package com.skylimit.Skylimit.service;

import com.skylimit.Skylimit.entity.AppUser;
import com.skylimit.Skylimit.repository.UserRepository;

import lombok.AllArgsConstructor;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomUserDetailsServiceImpl
        implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        AppUser user = userRepository.findByName(username);

        if (user == null) {

            throw new UsernameNotFoundException(
                    "User not found"
            );
        }

        return new CustomUserDetails(user);
    }
}