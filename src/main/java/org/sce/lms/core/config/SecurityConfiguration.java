package org.sce.lms.core.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import javax.sql.DataSource;
import java.security.SecureRandom;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    protected Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    AuthenticationSuccessHandler successHandler;

    @Autowired
    private CustomAccessDeniedHandler accessDeniedHandler;

    @Autowired
    @Qualifier("customUserDetailsService")
    UserDetailsService userDetailsService;

    @Autowired
    DataSource dataSource;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion.$2Y,24);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/","/index/**").permitAll()
                .antMatchers("/dashboard/**").hasAnyRole("USER", "ADMIN")
                .anyRequest().authenticated();

        http.formLogin().loginPage("/login").permitAll()
                .failureUrl("/login?fail")
                .usernameParameter("username").passwordParameter("password")
                .successHandler(successHandler);

        http.sessionManagement().sessionFixation().migrateSession()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED).maximumSessions(1);

        http.logout()
                .logoutUrl("/logout")
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/login?logout");

        http.rememberMe().rememberMeParameter("remember-me")
                .rememberMeCookieName("REMEMBER-ME-COOKIE")
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(86400);

        http.exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler);

        http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepositoryImpl = new JdbcTokenRepositoryImpl();
        tokenRepositoryImpl.setDataSource(dataSource);
        return tokenRepositoryImpl;
    }

    @Override
    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("root").password(passwordEncoder().encode("test")).roles("ADMIN", "USER");
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/static/**", "/css/**", "/js/**", "/resources/**", "/webjars/**");
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
}
