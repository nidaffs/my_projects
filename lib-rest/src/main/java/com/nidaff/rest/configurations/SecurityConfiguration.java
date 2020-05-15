package com.nidaff.rest.configurations;

import com.nidaff.rest.utils.FacebookConnectionSignUp;
import com.nidaff.rest.utils.FacebookSignInAdapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.mem.InMemoryUsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInController;

import javax.sql.DataSource;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private ConnectionFactoryLocator connectionFactoryLocator;

    @Autowired
    private UsersConnectionRepository usersConnectionRepository;

    @Autowired
    private FacebookConnectionSignUp facebookConnectionSignUp;

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/", "/signup", "/signin/**", "/main", "/books/", "/css/**", "/images/**").permitAll()
                .antMatchers("/admin/**").hasAnyRole("ADMIN").anyRequest().authenticated().and().formLogin()
                .loginPage("/login").permitAll().and().logout().invalidateHttpSession(true).clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/bye").permitAll().and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
        builder.jdbcAuthentication().dataSource(dataSource).authoritiesByUsernameQuery(
                "SELECT user.login, role.role_name as role FROM user INNER JOIN user_has_role ON user.id = user_has_role.user_id INNER JOIN role ON user_has_role.role_id = role.id WHERE user.login = ?")
                .usersByUsernameQuery("select login, password, 1 as enabled from user where login = ?");
    }

    @Bean
    public ProviderSignInController providerSignInController(ConnectionFactoryLocator connectionFactoryLocator,
            FacebookConnectionSignUp facebookConnectionSignUp, UsersConnectionRepository usersConnectionRepository) {
        ((InMemoryUsersConnectionRepository) usersConnectionRepository).setConnectionSignUp(facebookConnectionSignUp);
        ProviderSignInController providerSignInController = new ProviderSignInController(connectionFactoryLocator,
                usersConnectionRepository, new FacebookSignInAdapter());
        providerSignInController.setPostSignInUrl("/books/");
        return providerSignInController;

    }
    
}
