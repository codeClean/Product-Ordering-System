package com.springweb1.springweb1.stock;


import com.springweb1.springweb1.product.Product;
import com.springweb1.springweb1.product.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.lang.reflect.Array;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.reactive.WebFluxLinkBuilder.methodOn;

@RestController
public class StockController {

    @Autowired
    StockDeoService service;

    @GetMapping("/stocks")
    public List<Stock> getStock() {
        return service.findAllStock();
    }

    @GetMapping("/stocks/{id}")
    public EntityModel<Stock> getStock(@PathVariable Integer id) throws Exception {
        Stock stock = service.findStock(id);
        if (stock == null) {
            throw new StockNotFoundException("stock id-" + id + "not found ");
        }

        EntityModel<Stock> resource = EntityModel.of(stock);

        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getStock());
        resource.add(linkTo.withRel("all-stocks"));

        return resource;

    }

    // get information about stock for product
    @GetMapping("/stocksByProduct/{id}/{productName}")
    public List<Product> getStockByProduct(@PathVariable Integer id, @PathVariable String productName) throws Exception {
        Stock stock = service.findStock(id);
        if (stock == null) {
            throw new StockNotFoundException("stock id-" + id + "not found ");
        }

        List<Product> productList = new ArrayList<>();

        // check for product name
        for (Product product : stock.getProduct()) {

            if (product.getName().contains(productName)) productList.add(product);

        }
        if (productList.size() == 0) {
            throw new ProductNotFoundException("product " + productName + " not found in stock id-" + id);
        }

        return productList;

    }


    @PostMapping("/stocks/{id}")
    public EntityModel<Stock> addProductListInStock(@Valid @PathVariable Integer id, @RequestBody List<Product> productList) {
        Stock stock = service.findStock(id);

        if (stock == null) {
            throw new StockNotFoundException("stock not found");
        }

        stock.getProduct().addAll(productList);


        EntityModel<Stock> resource = EntityModel.of(stock);

        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getStock());
        resource.add(linkTo.withRel("all-stocks"));

        return resource;
    }

    @PostMapping("/stocks")
    public EntityModel<Stock> saveStock(@Valid @RequestBody Stock stock) {
        Stock stock1 = service.saveStock(stock);
        if (stock1 == null) {
            throw new StockNotFoundException(" something went wrong while creating a stock");
        }

        EntityModel<Stock> resource = EntityModel.of(stock1);

        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getStock());
        resource.add(linkTo.withRel("all-stocks"));

        return resource;
    }

    @DeleteMapping("/stocks/{id}")
    public ResponseEntity<Object> deleteStock(@PathVariable Integer id) {
        Boolean removed = service.deleteStock(id);
        if (!removed) {
            throw new StockNotFoundException("id-" + id + "not found or can not be deleted");
        }

        return ResponseEntity.accepted().build();

    }
}
