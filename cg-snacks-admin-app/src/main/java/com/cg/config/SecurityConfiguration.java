//package com.cg.config;
//
//import com.cg.model.dto.UserDetailsServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//import javax.transaction.Transactional;
//
//@Configuration
//@EnableWebSecurity
//@Transactional
//public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    private UserDetailsServiceImpl userDetailsService;
//
//    @Bean
//    public BCryptPasswordEncoder bCryptPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService)
//                .passwordEncoder(bCryptPasswordEncoder());
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable();
//
//        //The pages does not require login
//        http.authorizeHttpRequests()
//                .requestMatchers("/login", "/logout")
//                .permitAll();
//
//        // For both of user, admin
//        http.authorizeHttpRequests()
//                .requestMatchers("/", "/home", "/user/**")
//                .hasAnyRole("USER", "ADMIN");
//
//        // For admin only
//        http.authorizeHttpRequests()
//                .requestMatchers("/role/**")
//                .hasRole("ADMIN");
//
//        // When the user has logged in as XX.
//        // But access a page that requires role YY,
//        // AccessDeniedException will throw.
//        http.authorizeHttpRequests()
//                .and()
//                .exceptionHandling()
//                .accessDeniedPage("/access-denied");
//
//        // Config for Login Form
//        http.authorizeHttpRequests()
//                .and().formLogin()// Submit URL of login page.
//                .loginProcessingUrl("/j_spring_security_check") // Submit URL
//                .loginPage("/login")
//                .defaultSuccessUrl("/home")
//                .failureUrl("/login?error=true")
//                .usernameParameter("username")
//                .passwordParameter("password")
//                // Config for Logout Page
//                .and().logout()
//                .logoutUrl("/logout")
//                .logoutSuccessUrl("/login?logout=true");
//    }
//}
//
