package com.k4sm.prody.Services;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.k4sm.prody.Models.Category;
import com.k4sm.prody.Models.Product;

@Repository
public interface productServiceInterface {
    List<Product> getAllProducts();

    Product getProductById(int id);

    List<Product> getProductsByCategory(String category);

    List<Category> getAllCategories();

    Product createProduct(Product product);

    Product updateProduct(int id, Product product);

    String deleteProduct(int id);
}
