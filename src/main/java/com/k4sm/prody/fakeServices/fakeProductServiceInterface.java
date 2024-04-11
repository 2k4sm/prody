package com.k4sm.prody.fakeServices;

import com.k4sm.prody.models.Category;
import com.k4sm.prody.models.Product;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface fakeProductServiceInterface {
  List<Product> getAllProducts();

  Product getProductById(int id);

  List<Product> getProductsByCategory(String category);

  List<Category> getAllCategories();

  Product createProduct(Product product);

  Product updateProduct(int id, Product product);

  String deleteProduct(int id);
}
