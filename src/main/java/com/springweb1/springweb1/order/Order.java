package com.springweb1.springweb1.order;

import com.springweb1.springweb1.product.Product;
import com.springweb1.springweb1.stock.Stock;
import io.micrometer.core.lang.NonNull;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class Order {

    @NonNull
    Long id;
    OrderStatus orderStatus;
    Integer stockId; // destination stock
    List<Product> productList;

    public Order(@NonNull Long id, OrderStatus orderStatus, Integer stockId, List<Product> productList) {
        this.id = id;
        this.orderStatus = orderStatus;
        this.stockId = stockId;
        this.productList = productList;
    }


    @NonNull
    public Long getId() {
        return id;
    }

    public void setId(@NonNull Long id) {
        this.id = id;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getStockId() {
        return stockId;
    }

    public void setStockId(Integer stockId) {
        this.stockId = stockId;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
