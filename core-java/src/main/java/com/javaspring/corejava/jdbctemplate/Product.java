package com.javaspring.corejava.jdbctemplate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
    private Long id;
    private String name;
    private String category;
    private String photoUrl;
    private String description;
    private Double price;
}
