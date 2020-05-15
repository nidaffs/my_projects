package com.nidaff.rest.configurations;

import java.util.Properties;
import java.util.concurrent.Executor;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableAsync
@EnableScheduling
//@ComponentScan("com.loya.springjpaoracledemo")
public class MailConfiguration {

//	@Value("${mail.debug}")
//	private String mailDebugProperty;
//
//	@Value("${mail.smtp.auth}")
//	private String mailSmtpAuthProperty;
//
//	@Value("${mail.smtp.starttls.enable}")
//	private String mailSmtpStarttlsEnableProperty;
//
//	@Value("${mail.transport.protocol}")
//	private String mailTransportProtocolProperty;
//
//	@Value("${input.encoding}")
//	private String velocityInputEncodingProperty;
//
//	@Value("${output.encoding}")
//	private String velocityOutputEncodingProperty;
//
//	@Value("${resource.loader}")
//	private String velocityResourceLoaderProperty;
//
//	@Value("${class.resource.loader.class}")
//	private String velocityClassResourceLoaderClassProperty;
//
//	@Bean
//	public JavaMailSender mailSender() {
//		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//		mailSender.setHost("smtp.gmail.com");
//		mailSender.setPort(587);
//		mailSender.setUsername("mail here");
//		mailSender.setPassword("password here");
//		Properties javaMailProperties = new Properties();
//		javaMailProperties.put("mail.smtp.starttls.enable", mailSmtpStarttlsEnableProperty);
//		javaMailProperties.put("mail.smtp.auth", mailSmtpAuthProperty);
//		javaMailProperties.put("mail.transport.protocol", mailTransportProtocolProperty);
//		javaMailProperties.put("mail.debug", mailDebugProperty);
//
//		mailSender.setJavaMailProperties(javaMailProperties);
//		return mailSender;
//	}
//
//	@Bean
//	public VelocityEngine velocityEngine() {
//		Properties velocityProperties = new Properties();
//		velocityProperties.setProperty("input.encoding", velocityInputEncodingProperty);
//		velocityProperties.setProperty("output.encoding", velocityOutputEncodingProperty);
//		velocityProperties.setProperty("resource.loader", velocityResourceLoaderProperty);
//		velocityProperties.setProperty("class.resource.loader.class", velocityClassResourceLoaderClassProperty);
//		return new VelocityEngine(velocityProperties);
//	}
//
//	@Bean(name = "threadPoolTaskExecutor")
//	public Executor threadPoolTaskExecutor() {
//		return new ThreadPoolTaskExecutor();
//	}
//
//	@Async("threadPoolTaskExecutor")
//	public void asyncMethodWithConfiguredExecutor() {
//		System.out.println("Execute method with configured executor - " + Thread.currentThread().getName());
//	}

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
