package com.nidaff.api;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.nidaff.api")
@EntityScan("com.nidaff.entity")
public class DaoConfiguration {

}
