package org.binar.movie.configuration;

import org.binar.movie.filter.CustomAuthorizationFilter;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    private static final String ROLE_ADMIN = "ROLE_ADMIN";

    public SecurityConfiguration() {

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers("/login", "/cinema/api/v1/users/refresh").permitAll();
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/cinema/api/v1/movies/getmovie")
                .permitAll();
        http.authorizeRequests()
                .antMatchers("/cinema/api/v1/movies/**")
                .hasAnyAuthority(ROLE_ADMIN);
       http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
