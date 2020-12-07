package vn.tungnt.interview.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import vn.tungnt.interview.security.jwt.JwtConfigurerAdapter;

import static vn.tungnt.interview.security.AuthoritiesConstants.ADMIN;
import static vn.tungnt.interview.security.AuthoritiesConstants.USER;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static final Logger LOG = LoggerFactory.getLogger(SecurityConfiguration.class);

    private final UserDetailsService securityProviderService;

    private final JwtConfigurerAdapter jwtConfigurerAdapter;

    public SecurityConfiguration(final UserDetailsService securityProviderService,
                                 final JwtConfigurerAdapter jwtConfigurerAdapter) {
        this.securityProviderService = securityProviderService;
        this.jwtConfigurerAdapter = jwtConfigurerAdapter;
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.securityProviderService);
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        LOG.debug("Configure Http Security");
        http
                .csrf()
                .disable()
                .headers().frameOptions().disable()
                    .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                .authorizeRequests()
                    .mvcMatchers("/h2-console/**").permitAll()
                    .antMatchers("/api/auth/**").permitAll()
                    .antMatchers("/api/**").authenticated()
                    .antMatchers("/api/management/**").hasRole(ADMIN)
                    .antMatchers("/api/driver/**").hasAnyRole(ADMIN, USER)
                    .antMatchers("/api/vehicle/**").hasAnyRole(ADMIN, USER)
                    .antMatchers("/api/payment/**").hasAnyRole(ADMIN, USER)
                    .and()
                .httpBasic()
                    .and()
                .apply(this.jwtConfigurerAdapter);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
