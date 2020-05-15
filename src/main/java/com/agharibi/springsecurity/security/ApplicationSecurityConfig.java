package com.agharibi.springsecurity.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import static com.agharibi.springsecurity.security.ApplicationUserRole.*;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
//            .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).and()
            .authorizeRequests()
            .antMatchers("/", "index", "/css/*", "/js/*").permitAll()
            .antMatchers("/api/**").hasRole(STUDENT.name())
            .anyRequest()
            .authenticated()
            .and()
            .httpBasic();
    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        UserDetails anna = User.builder()
            .username("anna")
            .password(passwordEncoder.encode("anna"))
            //.roles(STUDENT.name()) // ROLE_STUDENT
            .authorities(STUDENT.getGrantedAuthorities())
            .build();

        UserDetails linda = User.builder()
            .username("linda")
            .password(passwordEncoder.encode("linda"))
            //.roles(ADMIN.name())   // ROLE_ADMIN
            .authorities(ADMIN.getGrantedAuthorities())
            .build();

        UserDetails tom = User.builder()
            .username("tom")
            .password(passwordEncoder.encode("tom"))
            //.roles(ADMIN_TRAINEE.name())  // ROLE_ADMIN_TRAINEE
            .authorities(ADMIN_TRAINEE.getGrantedAuthorities())
            .build();

        return new InMemoryUserDetailsManager(anna, linda, tom);
    }
}
