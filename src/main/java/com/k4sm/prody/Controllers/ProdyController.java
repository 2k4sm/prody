package com.k4sm.prody.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.k4sm.prody.Models.Catagory;
import com.k4sm.prody.Models.Product;
import com.k4sm.prody.Services.productServiceInterface;

@RestController
public class ProdyController {

    @Autowired
    private productServiceInterface productservice;

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return this.productservice.getAllProducts();
    }

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable int id) {
        return this.productservice.getProductById(id);
    }

    @GetMapping("/products/categories")
    public List<Catagory> getAllCategories() {
        return this.productservice.getAllCategories();
    }

    @GetMapping("/products/categories/{category}")
    public List<Product> getProductsByCategory(@PathVariable String category) {
        return this.productservice.getProductsByCategory(category);
    }

    @PostMapping("/products")
    public Product createProduct(@RequestBody Product product) {
        return this.productservice.createProduct(product);
    }

    @PutMapping("/products/{id}")
    public String updateProduct(@PathVariable("id") int id, @RequestBody Product product) {
        return this.productservice.updateProduct(id, product);
    }

    @DeleteMapping("/products/{id}")
    public String deleteProduct(@PathVariable("id") int id) {
        return this.productservice.deleteProduct(id);
    }
}