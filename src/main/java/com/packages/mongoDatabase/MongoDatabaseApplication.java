package com.packages.mongoDatabase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

//https://www.adictosaltrabajo.com/2019/03/07/securizando-un-api-rest-con-jwt-y-roles/
@SpringBootApplication(scanBasePackages={
		"com.packages.mongoDtabase"},exclude={DataSourceAutoConfiguration.class})
public class MongoDatabaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(MongoDatabaseApplication.class, args);
	}

}
