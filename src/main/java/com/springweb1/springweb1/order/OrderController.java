package com.springweb1.springweb1.order;

import com.springweb1.springweb1.product.Product;
import com.springweb1.springweb1.stock.StockController;
import com.springweb1.springweb1.stock.StockDeoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.reactive.WebFluxLinkBuilder.methodOn;

@RestController
public class OrderController {

    @Autowired
    OrderDeoService service;

    @Autowired
    StockController stockController;

    @GetMapping("/orders")
    public List<Order> getOrders() {
        return service.findAllOrders();
    }

    @GetMapping("/orders/{id}")
    public EntityModel<Order> getOrder(@PathVariable Long id) throws Exception {
        Order order = service.findOrder(id);
        if (order == null) {
            throw new Exception();
        }

        EntityModel<Order> resource = EntityModel.of(order);

        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getOrders());
        resource.add(linkTo.withRel("all-orders"));

        return resource;

    }

    @PostMapping("/orders/{stockId}/{quantity}")
    public EntityModel<Order> orderProduct(@Valid @PathVariable Integer stockId, @PathVariable Integer quantity, @RequestBody Product product) {
        Order order = service.orderProduct(stockId, quantity, product);
        if (order == null) throw new OrderNotFoundException(" something went wrong while ordering  ");
        EntityModel<Order> resource = EntityModel.of(order);

        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getOrders());
        resource.add(linkTo.withRel("all-orders"));

        return resource;
    }

    @DeleteMapping("/orders/{id}")
    public ResponseEntity<Object> deleteDelete(@PathVariable Long id) {
        Boolean removed = service.deleteOrder(id);
        if (!removed) {
            throw new OrderNotFoundException("id- " + id + " not found or can not be deleted");
        }

        return ResponseEntity.accepted().build();

    }

    @PutMapping("deliverOrder/{id}")
    public EntityModel<Order> deliverOrder(@Valid @PathVariable Long id) throws Exception {
        Order order = service.deliverOrder(id);
        if (order == null) throw new OrderNotFoundException(" something went wrong while delivering the order ");

        // add the product to the stock

        stockController.addProductListInStock(order.getStockId(), order.getProductList());
        EntityModel<Order> resource = EntityModel.of(order);

        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getOrder(id));
        resource.add(linkTo.withRel("updated order"));

        return resource;
    }

    @PutMapping("cancelOrder/{id}")
    public EntityModel<Order> cancelOrder(@Valid @PathVariable Long id) throws Exception {
        Order order = service.cancelOrder(id);
        if (order == null) throw new OrderNotFoundException("can not cancel un ordered product");

        EntityModel<Order> resource = EntityModel.of(order);

        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getOrder(id));
        resource.add(linkTo.withRel("updated order"));

        return resource;
    }

}
