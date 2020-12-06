package vn.tungnt.interview.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import vn.tungnt.interview.security.jwt.JwtConfigurerAdapter;
import vn.tungnt.interview.security.jwt.TokenProvider;

import static vn.tungnt.interview.security.AuthoritiesConstants.ADMIN;
import static vn.tungnt.interview.security.AuthoritiesConstants.USER;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserDetailsService securityProviderService;

    private final TokenProvider tokenProvider;

    public SecurityConfiguration(final UserDetailsService securityProviderService,
                                 final TokenProvider tokenProvider) {
        this.securityProviderService = securityProviderService;
        this.tokenProvider = tokenProvider;
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.securityProviderService);
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
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
                .apply(this.securityConfigurerAdapter());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private JwtConfigurerAdapter securityConfigurerAdapter() {
        return new JwtConfigurerAdapter(tokenProvider);
    }

}
