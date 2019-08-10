package org.codesolt.mongokubernetesdemo.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@Builder
public class Order {

    @Id
    private String id;
    private Coffee coffee;
    private int quantity;
    private Double total;
}

