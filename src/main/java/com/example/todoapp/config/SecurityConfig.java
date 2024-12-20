package com.example.todoapp.config;

import com.example.todoapp.services.IdentificationDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
@Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder){
    UserDetails user = User.builder().username("user").password(encoder.encode("user")).build();

    return new IdentificationDetailService();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http.csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(auth -> auth.requestMatchers("/register/**").permitAll()
                    .requestMatchers("/registration/**").permitAll()
                    .requestMatchers("/").permitAll()
                    .requestMatchers("/style.css/**").permitAll()
                    .requestMatchers("/**").permitAll())
            .formLogin(AbstractAuthenticationFilterConfigurer::permitAll) // TODO 16:30 Доделать авторизацию и регистрацию
            .build();

    }
    @Bean
    public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
    }
}
