package org.codesolt.mongokubernetesdemo.repository;

import org.codesolt.mongokubernetesdemo.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, String> {}
