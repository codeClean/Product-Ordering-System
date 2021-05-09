package com.springweb1.springweb1.stock;

import com.springweb1.springweb1.product.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StockDeoService {
    private static List<Product> products = new ArrayList<>();
    private static List<Stock> stocks = new ArrayList<>();
    private Integer stockCount = 0;

    static {
        products.add(new Product(1, "Apple 12 mini", "smart_phone", "instalment payment with subscription 30$ per month"));
        products.add(new Product(2, "Samsung galaxy s21", "smart_phone", "instalment payment with subscription 41$ per month"));
        products.add(new Product(3, "Apple iphone pro", "smart_phone", "instalment payment with subscription 47$ per month"));
        products.add(new Product(4, "Microsoft surface pro 7", "tablets", "payment instalment from 43$ per month"));
        products.add(new Product(5, "Samsung galaxy tab", "tablets", "payment instalment from 52$ per month"));
        products.add(new Product(6, "Microsoft surface laptop", "notebook", "payment instalment from 41$ per month"));
        products.add(new Product(7, "Mac book air", "notebook", "payment instalment from 42$ per month"));
        Stock stock1 = new Stock();
        stock1.setId(0);
        stock1.setLocation("Amsterdam");
        stock1.setProduct(products);
        stocks.add(stock1);
    }

    public List<Stock> findAllStock() {
        return stocks;
    }

    public Stock saveStock(Stock stock) {

        Stock stock1 = null;
        if (stock.getId() == null) {
            stock.setId(++stockCount);
           stock1 = (stocks.add(stock)) ? stock : null;
        }
        return stock1;

    }

    public Stock findStock(Integer id) {
        for (Stock stock : stocks) {
            if (stock.getId() == id) {
                return stock;
            }
        }
        return null;
    }

    public Boolean deleteStock(Integer id) {
        Stock stock = findStock(id);
        return stocks.remove(stock);
    }
}
