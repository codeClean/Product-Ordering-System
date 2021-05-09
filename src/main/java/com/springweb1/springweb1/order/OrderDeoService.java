package com.springweb1.springweb1.order;

import com.springweb1.springweb1.product.Product;
import com.springweb1.springweb1.product.ProductDeoService;
import com.springweb1.springweb1.product.ProductNotFoundException;
import com.springweb1.springweb1.stock.Stock;
import com.springweb1.springweb1.stock.StockDeoService;
import com.springweb1.springweb1.stock.StockNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class OrderDeoService {
    @Autowired
    ProductDeoService productDeoService;

    @Autowired
    StockDeoService stockDeoService;
    private static List<Product> products = new ArrayList<>();
    private static List<Order> orderList = new ArrayList<>();

    static {
        products.add(new Product(1, "Apple 12 mini", "smart_phone", "instalment payment with subscription 30$ per month"));

        Long orderId = generateRandomNumber(5);

        orderList.add(new Order(orderId, OrderStatus.RUNNING, 0, products));

    }

    public List<Order> findAllOrders() {
        return orderList;
    }

    public Order orderProduct(Integer stockId, Integer quantity, Product product) {
        if (quantity == null) quantity = 1; // single product order: default
        List<Product> productList = new ArrayList<>();
        Product product1 = productDeoService.findProduct(product.getId());
        Stock stock = stockDeoService.findStock(stockId);
        if (product1 == null) {
            throw new ProductNotFoundException("only known products can be ordered ");
        }
        if (stock == null) {
            throw new StockNotFoundException("stock not found ");
        }
        for (int i = 0; i < quantity; i++) {
            productList.add(product);
        }
        Long orderId = generateRandomNumber(5);

        orderList.add(new Order(orderId, OrderStatus.RUNNING, stockId, productList));

        return findOrder(orderId);
    }

    public Order findOrder(Long id) {
        for (Order order : orderList) {
            if (order.getId().equals(id)) {
                return order;
            }
        }
        return null;
    }

    public Order deliverOrder(Long id) {
        Order order = findOrder(id);
        Integer index =  orderList.indexOf(order);
        order.setOrderStatus(OrderStatus.DELIVERED);
        orderList.set(index,order);
        return order;
    }
    public Order cancelOrder(Long id) {
        Order order = findOrder(id);
        Integer index =  orderList.indexOf(order);
        order.setOrderStatus(OrderStatus.CANCELLED);
        orderList.set(index,order);
        return order;
    }


    public Boolean deleteOrder(Long id) {
        Order order = findOrder(id);
        return orderList.remove(order);
    }

    private static long generateRandomNumber(int n) {
        long min = (long) Math.pow(10, n - 1);
        return ThreadLocalRandom.current().nextLong(min, min * 10);
    }
}

