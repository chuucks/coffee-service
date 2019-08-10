package org.codesolt.mongokubernetesdemo.repository;


import org.codesolt.mongokubernetesdemo.model.Coffee;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CoffeeRepository extends MongoRepository<Coffee, String> {}
