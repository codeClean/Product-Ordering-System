package com.springweb1.springweb1.product;

import com.springweb1.springweb1.order.Order;
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
public class ProductController {

    @Autowired
    ProductDeoService service;

    @GetMapping("/products")
    public List<Product> getProducts() {
        return service.findAllProducts();
    }

    @GetMapping("/products/{id}")
    public EntityModel<Product> getProduct(@PathVariable Integer id) throws Exception {
        Product product = service.findProduct(id);
        if (product == null) {
            throw new Exception();
        }

        EntityModel<Product> resource = EntityModel.of(product);

        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getProducts());
        resource.add(linkTo.withRel("all-products"));

        return resource;

    }

    @PostMapping("/products")
    public EntityModel<Product> saveProduct(@Valid @RequestBody Product product) {
        Product product1 = service.saveProduct(product);

        EntityModel<Product> resource = EntityModel.of(product1);

        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getProducts());
        resource.add(linkTo.withRel("all-products"));

        return resource;
    }
    @DeleteMapping("/products/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable Integer id){
        Boolean removed = service.deleteProduct(id);
        if (!removed) {
            throw new ProductNotFoundException("id-"+id+"not found or can not be deleted");
        }

        return ResponseEntity.accepted().build();

    }
}
