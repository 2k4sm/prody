package com.k4sm.prody.controllers;

import java.util.ArrayList;
import java.util.List;

import com.k4sm.prody.dtos.RealCreateProductDTO;
import com.k4sm.prody.entity.CategoryEntity;
import com.k4sm.prody.entity.ProductEntity;
import com.k4sm.prody.realServices.categoryService;
import com.k4sm.prody.realServices.realProductServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.k4sm.prody.models.Category;
import com.k4sm.prody.models.Product;

@RestController
public class realProdyController {
    @Autowired
    private realProductServiceImpl realProductService;
    @Autowired
    private categoryService categoryService;

    private final ModelMapper modelMapper;

    public realProdyController(realProductServiceImpl realProductService, categoryService categoryService) {
        this.realProductService = realProductService;
        this.modelMapper = new ModelMapper();
        this.categoryService = categoryService;
    }

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        List<ProductEntity> productEntities = realProductService.getAllProducts();

        List<Product> products = new ArrayList<>();

        for (ProductEntity productEntity : productEntities) {
            Product product = this.modelMapper.map(productEntity, Product.class);
            products.add(product);
        }

        return products;
    }

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable int id) {
        ProductEntity productEntity = this.realProductService.getProductById(id);
        return this.modelMapper.map(productEntity, Product.class);
    }

    @GetMapping("/products/categories")
    public List<Category> getAllCategories() {
        List<CategoryEntity> categoryEntities = this.categoryService.getAllCategories();

        List<Category> categories = new ArrayList<>();

        for (CategoryEntity categoryEntity : categoryEntities) {
            Category category = this.modelMapper.map(categoryEntity, Category.class);
            categories.add(category);
        }

        return categories;
    }

    @GetMapping("/products/categories/{category}")
    public List<Product> getProductsByCategory(@PathVariable String category) {
        List<ProductEntity> productEntities = this.realProductService.getProductsByCategory(category);
        List<Product> products = new ArrayList<>();
        for (ProductEntity productEntity : productEntities) {
            Product product = this.modelMapper.map(productEntity, Product.class);
            products.add(product);
        }

        return products;
    }


    @PostMapping("/products")
    public Product createProduct(@RequestBody RealCreateProductDTO product) {
        ProductEntity productEntity = this.realProductService.createProduct(product);
        return this.modelMapper.map(productEntity, Product.class);
    }

    @PutMapping("/products/{id}")
    public Product updateProduct(@PathVariable("id") int id, @RequestBody RealCreateProductDTO product) {
        ProductEntity productEntity = this.realProductService.updateProduct(id,product);
        return this.modelMapper.map(productEntity, Product.class);
    }

    @DeleteMapping("/products/{id}")
    public Product deleteProduct(@PathVariable("id") int id) {
        return this.modelMapper.map(this.realProductService.deleteProduct(id), Product.class);
    }
}
