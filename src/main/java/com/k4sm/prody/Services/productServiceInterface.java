package com.k4sm.prody.Services;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.k4sm.prody.Models.Catagory;
import com.k4sm.prody.Models.Product;

@Repository
public interface productServiceInterface {
    List<Product> getAllProducts();

    Product getProductById(int id);

    List<Product> getProductsByCategory(String category);

    List<Catagory> getAllCategories();

    Product createProduct(Product product);

    String updateProduct(int id, Product product);

    String deleteProduct(int id);
}
