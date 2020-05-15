package com.nidaff.rest.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.mem.InMemoryUsersConnectionRepository;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;

@Configuration
public class FacebookConfig {

    @Bean
    public ConnectionFactoryLocator connectionFactoryLocator() {
        ConnectionFactoryRegistry connectionFactoryRegistry = new ConnectionFactoryRegistry();
        connectionFactoryRegistry.addConnectionFactory(facebookConnectionFactory());
        return connectionFactoryRegistry;
    }

    @Bean
    public FacebookConnectionFactory facebookConnectionFactory() {
        FacebookConnectionFactory facebookConnectionFactory = new FacebookConnectionFactory("244300870146717",
                "e30d0526542c5f901672ab3875c62797");
        facebookConnectionFactory.setScope("email, public_profile");
        return facebookConnectionFactory;
    }

    @Bean
    public UsersConnectionRepository usersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        return new InMemoryUsersConnectionRepository(connectionFactoryLocator);
    }
    
}
