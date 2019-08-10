package org.codesolt.mongokubernetesdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class MongoKubernetesDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MongoKubernetesDemoApplication.class, args);
	}

}
