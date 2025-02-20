package com.example.todoapp.config;

import com.example.todoapp.services.IdentificationDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
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
    public UserDetailsService userDetailsService(){

    return new IdentificationDetailService();

    //TODO Добавить обработчик ошибки 404 | Можно еще добавить авторизацию через email и Google

    // TODO Добавить пагинацию
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http.csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(auth -> auth.requestMatchers("/register/**").permitAll()
                    .requestMatchers("/registration/**").permitAll()
                    .requestMatchers("/style.css/**").permitAll()
                    .requestMatchers("/**").authenticated())
            .formLogin(login -> login
                    .loginPage("/login")
                    .defaultSuccessUrl("/secondpage", true)
                    .failureUrl("/login")
                    .permitAll()


            )
            .logout(logout -> logout
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login?logout")
                    .permitAll())
            .build();

    }
    @Bean
    AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
    }
}
