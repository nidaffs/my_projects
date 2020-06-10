package com.nidaff.rest.configurations;

import com.nidaff.rest.utils.FacebookConnectionSignUp;
import com.nidaff.rest.utils.FacebookSignInAdapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    private AccessDeniedHandler accessDeniedHandler;

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests().antMatchers("/", "/signup", "/signin/**", "/css/**", "/images/**")
                .permitAll().and().formLogin().loginPage("/login").usernameParameter("email").permitAll().and()
                .authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN").antMatchers("/users/**", "/books/**")
                .hasAnyRole("ADMIN", "USER").anyRequest().authenticated().and().logout().invalidateHttpSession(true)
                .clearAuthentication(true).logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/").permitAll().and().exceptionHandling().accessDeniedHandler(accessDeniedHandler);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
        builder.jdbcAuthentication().dataSource(dataSource).authoritiesByUsernameQuery(
                "SELECT user.email as email, role.role_name as role FROM user INNER JOIN user_has_role ON user.id = user_has_role.user_id INNER JOIN role ON user_has_role.role_id = role.id WHERE user.email = ?")
                .usersByUsernameQuery("SELECT email, password, 1 as enabled FROM user WHERE user.email = ?")
                .passwordEncoder(new BCryptPasswordEncoder());
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

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
        
    }
    
}
