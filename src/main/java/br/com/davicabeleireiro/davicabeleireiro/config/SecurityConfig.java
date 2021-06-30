package br.com.davicabeleireiro.davicabeleireiro.config;

import br.com.davicabeleireiro.davicabeleireiro.security.jwt.JWTConfigurer;
import br.com.davicabeleireiro.davicabeleireiro.security.jwt.JWTTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private Environment environment;

    @Autowired
    private JWTTokenProvider tokenProvider;

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        if (Arrays.asList(environment.getActiveProfiles()).contains("test")){
            http.headers().frameOptions().disable();
        }

        http.httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/signing").permitAll()
                .antMatchers("/user/**", "/permission/**").
                hasAuthority("ADMIN")

                .antMatchers("/address", "/category", "/contact", "/item")
                .hasAnyAuthority("ADMIN", "CREATOR")

                .antMatchers("/client", "reservation").
                hasAnyAuthority("ADMIN", "CLIENT", "CREATOR")

                .and()
                .apply(new JWTConfigurer(tokenProvider));
    }
}
