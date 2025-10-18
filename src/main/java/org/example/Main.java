package org.example;

import dao.IDao;
import entities.Product;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import util.HibernateConfig;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Spring-Hibernate Demo ===");
        
        // Initialize Spring Application Context
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(HibernateConfig.class)) {
            // Get Product DAO from Spring container
            @SuppressWarnings("unchecked")
            IDao<Product> productDao = context.getBean(IDao.class);
        
        // Create a sample product
        Product product = new Product();
        product.setName("Sample Product");
        product.setPrice(99.99);
        
        // Save the product
        productDao.create(product);
        System.out.println("Product created: " + product.getName() + " (ID: " + product.getId() + ")");
        
        // Retrieve all products
        System.out.println("\nAll products:");
        productDao.findAll().forEach(p -> 
            System.out.println("ID: " + p.getId() + ", Name: " + p.getName() + ", Price: " + p.getPrice())
        );
        
            System.out.println("\n=== Demo completed successfully! ===");
        }
    }
}