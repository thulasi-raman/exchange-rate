package com.exrate.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.exrate.entity.User;
import com.exrate.service.UserServiceImpl;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Configuration
@EnableWebSecurity
public class ExRateSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	UserServiceImpl userService;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	}
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
               .antMatchers("/registration").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
            .logout()
                .permitAll();
    }
	
	/*@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
                .withUser("user").password("password").roles("USER");
    }*/
	
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
        auth.authenticationProvider(new AuthenticationProvider() {
            @Override
            public Authentication authenticate(Authentication authentication) {
                String email = (String) authentication.getPrincipal();
                String password = (String) authentication.getCredentials();
                UserDetails userDetails = userService.loadUserByUsername(email);
                if (userDetails == null) {
                    throw new BadCredentialsException("Invalid User Name:  " + authentication.getPrincipal());
                }
                System.err.println("Password : " + password);
                User user = userService.authenticateUser(email, password);
                if(null == user) throw new BadCredentialsException("Invalid Password:  " + authentication.getPrincipal());
                return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            }

            @Override
            public boolean supports(Class<?> authentication) {
                return true;
            }
        });
    }
	
	

}
