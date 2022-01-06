package com.techprimers.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
@DynamoDBDocument
public class Product {

    @DynamoDBAttribute
    private Integer productId;
    @DynamoDBAttribute
    private String productName;
}
