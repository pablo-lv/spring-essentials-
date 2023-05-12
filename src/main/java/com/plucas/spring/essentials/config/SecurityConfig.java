package com.plucas.spring.essentials.config;

import com.plucas.spring.essentials.entities.UserAccount;
import com.plucas.spring.essentials.repositories.UserManagementRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailService() {
        UserDetailsManager userDetailsManager =
                new InMemoryUserDetailsManager();

        UserDetails admin = User.builder()
                .username("user")
                .password(passwordEncoder().encode("password"))
                .roles("USER")
                .build();

        UserDetails user = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("password"))
                .roles("ADMIN")
                .build();

        userDetailsManager.createUser(admin);
        userDetailsManager.createUser(user);

        return userDetailsManager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner initUsers(UserManagementRepository repository) {
        return args -> {
            repository.save(new UserAccount("user", "password", "ROLE_USER"));
        };
    }
}
