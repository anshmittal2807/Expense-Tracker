package com.agile.expenseTracker.configurations;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecuirityConfig {

    @Bean
    public SecurityFilterChain securityFilter (HttpSecurity https){
        https.csrf(csrf -> csrf.disable());
        https.authorizeHttpRequests(auth -> auth.
                requestMatchers("/save" , "/login")
                .permitAll().anyRequest().authenticated()
        );
        https.formLogin(form -> form.permitAll());

    return https.build();

    }
}
