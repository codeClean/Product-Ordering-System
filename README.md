# Product-Ordering-System
# Basic management of product catalog and ordering process of product

All the neccessary interaction with the api can be found here: http://localhost:8080/v2/api-docs as a json format (after runing it on your local machine) 

Or use http://localhost:8080/swagger-ui/index.html to visualize it in a UI: the basic controllers are product-controller, Stock-controler and order-Controller.

This project can be improved by adding some additional features such as internationalization, authentication and authorization, concurrency issue, implementation of lazy loading, and minimize API calls.
 
## Some basic url samples regarding the requirment

# manage product catalog
        ◦ allow adding new products to catalog: 
                  method: POST
                  url: http://localhost:8080/products
                  body: {
                       "name": "new  Apple 12 mini",
                       "type": "smart_phone",
                        "description": "new instalment payment with subscription 70$ per month"
                      }   
    
        ◦ allow removing products from catalog
                 method: DELETE
                 url:http://localhost:8080/products/2
                 
        ◦ get information about stock for product
                  method: GET
                  url: http://localhost:8080/stocksByProduct/0/Samsung galaxy

        ◦ stock product (increase the number of available items of a product)
                 method: POST
                 url: http://localhost:8080/stocks/0
                 Body:  [ {
                      "name": "new samsung 12 mini",
                      "type": "smart_phone",
                      "description": "new instalment payment with subscription 30$ per month"
                      }] 
                      
 # ordering of products (prio 2)
        ◦ only known products are orderable: (check also for non existing product) url description: /orders/{stockId}/{quantity}
      
                 method: POST
                 url: http://localhost:8080/orders/0/2 
                 Body:{
                          "id": 2,
                          "name": "new order Apple 12 mini",
                          "type": "smart_phone",
                          "description": "instalment payment with subscription 30$ per month"
                      }
                      
        ◦ check on stock is done: on each orde, after the product is delivered the stock is updated
        ◦ possible Exceptions can be treated (Cancel Order….): this can happen by deliberate cancellation or if an order is not performed correctly.
                 method: PUT
                 url: http://localhost:8080/deliverOrder/14152
        ◦ orders have a state (running, delivered, cancelled….): taken care of using Enum
        ◦ stock updated on delivery of order: (get the id of the order first)
                 method: PUT
                 url: http://localhost:8080/cancelOrder/14152
        ◦ show status and content of orders
                 method: GET
                 url:http://localhost:8080/orders



