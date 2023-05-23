package com.plucas.spring.essentials.config;

import com.plucas.spring.essentials.entities.UserAccount;
import com.plucas.spring.essentials.repositories.UserManagementRepository;
import com.plucas.spring.essentials.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

//    @Bean
//    public UserDetailsService userDetailService() {
//        UserDetailsManager userDetailsManager =
//                new InMemoryUserDetailsManager();
//
//        UserDetails admin = User.builder()
//                .username("user")
//                .password(passwordEncoder().encode("password"))
//                .roles("USER")
//                .build();
//
//        UserDetails user = User.builder()
//                .username("admin")
//                .password(passwordEncoder().encode("password"))
//                .roles("ADMIN")
//                .build();
//
//        userDetailsManager.createUser(admin);
//        userDetailsManager.createUser(user);
//
//        return userDetailsManager;
//    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    @Bean
    UserDetailsService userService(UserRepository repo) {
        return username -> repo.findByUsername(username)
                .asUser();
    }

    @Bean
    CommandLineRunner initUsers(UserManagementRepository repository) {
        return args -> {
            repository.save(new UserAccount("alice", "password", "ROLE_USER"));
            repository.save(new UserAccount("bob", "password", "ROLE_USER"));
            repository.save(new UserAccount("admin", "password", "ROLE_USER"));
        };
    }

    @Bean
    SecurityFilterChain configureSecurity(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests() //
                .requestMatchers("/login").permitAll() //
                .requestMatchers("/", "/search").authenticated() //
                .requestMatchers(HttpMethod.GET, "/api/**").authenticated() //
                .requestMatchers("/admin").hasRole("ADMIN") //
                .requestMatchers(HttpMethod.POST, "/delete/**", "/new-video").authenticated() //
                .anyRequest().denyAll() //
                .and() //
                .formLogin() //
                .and() //
                .httpBasic();
        return http.build();
    }
}
