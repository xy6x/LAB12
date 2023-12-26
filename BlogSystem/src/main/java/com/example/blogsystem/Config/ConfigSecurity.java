package com.example.blogsystem.Config;

import com.example.blogsystem.Service.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class ConfigSecurity {
    private final MyUserDetailsService myUserDetailsService;
    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider =new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(myUserDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws  Exception{
        http.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .authenticationProvider(authenticationProvider())
                .authorizeHttpRequests()
                .requestMatchers("/api/v1/user/login",
                        "/api/v1/user/add","/api/v1/user/put","/api/v1/user/delete/{id}",
                        "/api/v1/user/getUser").permitAll()
                .requestMatchers("/api/v1/blog/add").permitAll()
                .requestMatchers("/api/v1/user/getId/{id}","/api/v1/user/my-blog",
                        "/api/v1/user//getTitle/{title}","/api/v1/blog/add",
                        "/api/v1/blog/update/{id}","/api/v1/blog/delete/{id}").permitAll()
                .requestMatchers("/api/v1/user/getAllUser","/api/v1/blog/all-blog").hasAuthority("ADMIN")
                .and()
                .logout().logoutUrl("api/v1/user/logout")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .and()
                .httpBasic();
                return  http.build();

    }


}
