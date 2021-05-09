package com.springweb1.springweb1.product;

import io.micrometer.core.lang.NonNull;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

public class Product {

    @NonNull
    Integer id;
    @Size(min = 2, message = "Name should be at least 2 characters")
    String name;
    @Size(max = 20, message = "type should be at most 10 characters")
    String type;
    @NotNull
    String description;

    public Product(Integer id, String name,  String type, @NotNull String description) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
