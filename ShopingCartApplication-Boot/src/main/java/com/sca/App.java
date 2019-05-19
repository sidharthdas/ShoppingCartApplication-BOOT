package com.sca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScan(basePackages="com.sca")        
public class App {

	public static void main(String[] args) {     
		// TODO Auto-generated method stub
		SpringApplication.run(App.class, args);
	}

}
	