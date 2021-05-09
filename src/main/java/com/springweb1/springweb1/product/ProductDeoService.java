package com.springweb1.springweb1.product;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class ProductDeoService {
   private static List<Product> catalogs = new ArrayList<>();
   private static int productCount = 7;
    static {
        catalogs.add(new Product(1, "Apple 12 mini","smart_phone", "instalment payment with subscription 30$ per month"));
        catalogs.add(new Product(2, "Samsung galaxy s21","smart_phone", "instalment payment with subscription 41$ per month"));
        catalogs.add(new Product(3, "Apple iphone pro","smart_phone", "instalment payment with subscription 47$ per month"));
        catalogs.add(new Product(4, "Microsoft surface pro 7","tablets", "payment instalment from 43$ per month"));
        catalogs.add(new Product(5, "Samsung galaxy tab","tablets", "payment instalment from 52$ per month"));
        catalogs.add(new Product(6, "Microsoft surface laptop","notebook", "payment instalment from 41$ per month"));
        catalogs.add(new Product(7, "Mac book air","notebook", "payment instalment from 42$ per month"));

    }

    public List<Product> findAllProducts()
    {
        return catalogs;
    }

    public Product saveProduct(Product product){

        if (product.getId() == null) {
            product.setId(++productCount);
        }
        catalogs.add(product);
        return product;
    }

    public Product findProduct(Integer id){
        for (Product product : catalogs) {
            if (product.getId() == id){
                return product;
            }
        }
        return null;
    }

    public Boolean deleteProduct(Integer id){
        Product product = findProduct(id);
       return  catalogs.remove(product);
    }
}
