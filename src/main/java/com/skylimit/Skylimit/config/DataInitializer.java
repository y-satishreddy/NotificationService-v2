package com.skylimit.Skylimit.config;

import com.skylimit.Skylimit.entity.AppUser;
import com.skylimit.Skylimit.repository.UserRepository;

import lombok.AllArgsConstructor;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DataInitializer
        implements CommandLineRunner {

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args)
            throws Exception {

        if (userRepository.findByName("satish") == null) {

            AppUser user = new AppUser();

            user.setName("satish");

            user.setPassword(
                    passwordEncoder.encode("1234")
            );

            userRepository.save(user);

        }
    }
}