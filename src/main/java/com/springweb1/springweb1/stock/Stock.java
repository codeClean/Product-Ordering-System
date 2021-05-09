package com.springweb1.springweb1.stock;

import com.springweb1.springweb1.product.Product;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

public class Stock {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Integer id;
    String location;
    List<Product> product;

    public Stock() {
    }

    public Stock(Integer id, String location, List<Product> product) {
        this.id = id;
        this.location = location;
        this.product = product;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }
}
