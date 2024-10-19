package com.example.bookdemo.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class DemoSecurityConfig {

    // adding manually users for now
    // will link with database later

//    @Bean
//    public InMemoryUserDetailsManager userDetailsManager(){
//        UserDetails ayush = User.builder()
//                .username("ayush")
//                .password("{noop}vulcan")
//                .roles("USER","ADMIN")
//                .build();
//        UserDetails sai = User.builder()
//                .username("sai")
//                .password("{noop}saisp2003")
//                .roles("USER")
//                .build();
//        UserDetails happy = User.builder()
//                .username("happy")
//                .password("{noop}kratos")
//                .roles("USER")
//                .build();
//
//        return new InMemoryUserDetailsManager(ayush, sai, happy);
//    }

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){
        return new JdbcUserDetailsManager(dataSource); // since we are using default tables users and authorities no mentioning is required
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(configure ->
                configure
                        .requestMatchers("/","/books", "book-details/**", "/css/**", "/images/**").permitAll()
                        .requestMatchers("/systems/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
        )
        .formLogin(form ->
                form
                        .loginPage("/plain-login")
                        .loginProcessingUrl("/authenticateTheUser")
                        .permitAll()
        )
        .logout(logout -> logout
                .permitAll()
                .logoutSuccessUrl("/"))
                .exceptionHandling(configurer ->
                        configurer.accessDeniedPage("/access-denied")
        );

        return http.build();
    }
}
