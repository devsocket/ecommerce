package com.devsocket.ecommerce.order;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
        "com.devsocket.ecommerce"
})
public class OrderServiceApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(OrderServiceApplication.class, args);
    }
}
