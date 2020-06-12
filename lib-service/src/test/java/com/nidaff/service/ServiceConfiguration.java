package com.nidaff.service;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.nidaff.service")
@EntityScan("com.nidaff.entity")
public class ServiceConfiguration {

}
