package com.productsaleswebsitespringboot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.productsaleswebsitespringboot.repository.UserRepository;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private UserRepository userRepository;

    public UserDetailsService userDetailsServiceBean() {
        UserDetailService userDetailService = new UserDetailService(userRepository);
        return userDetailService;
    }

    @Bean
    public BCryptPasswordEncoder generateEncryption() {
        BCryptPasswordEncoder cryptography = new BCryptPasswordEncoder();
        return cryptography;
    }

    @Bean
    public SecurityFilterChain securityChain(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeHttpRequests()
                .requestMatchers("/user/admin/**").hasAnyAuthority("ADMIN")
                .requestMatchers("/product/admin/**").hasAnyAuthority("ADMIN")
                .requestMatchers("/supplier/admin/**").hasAnyAuthority("ADMIN")
                .requestMatchers("/category/admin/**").hasAnyAuthority("ADMIN")
                .requestMatchers("/**").permitAll()
                .anyRequest().authenticated()
                .and().exceptionHandling().accessDeniedPage("/")
                .and().formLogin().loginPage("/login").permitAll()
                .and().formLogin().successForwardUrl("/")
                .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .and().logout().logoutSuccessUrl("/").permitAll()
                .and().httpBasic(Customizer.withDefaults());
        return http.build();
    }

    public void authenticationManagerBean(AuthenticationManagerBuilder auth) throws Exception {
        UserDetailsService userDetailService = userDetailsServiceBean();
        BCryptPasswordEncoder cryptography = generateEncryption();

        auth.userDetailsService(userDetailService).passwordEncoder(cryptography);
    }
}
