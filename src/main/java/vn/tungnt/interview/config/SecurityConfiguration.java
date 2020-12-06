package vn.tungnt.interview.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static vn.tungnt.interview.config.Constants.ADMIN_ROLE;
import static vn.tungnt.interview.config.Constants.USER_ROLE;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserDetailsService securityProviderService;

    public SecurityConfiguration(final UserDetailsService securityProviderService) {
        this.securityProviderService = securityProviderService;
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
                    .antMatchers("/api/management/**").hasRole(ADMIN_ROLE)
                    .antMatchers("/api/driver/**").hasAnyRole(ADMIN_ROLE, USER_ROLE)
                    .antMatchers("/api/vehicle/**").hasAnyRole(ADMIN_ROLE, USER_ROLE)
                    .antMatchers("/api/payment/**").hasAnyRole(ADMIN_ROLE, USER_ROLE)
        .and().httpBasic();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
