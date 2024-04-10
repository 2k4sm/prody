package com.k4sm.prody.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.k4sm.prody.DTOs.FakeProductDTO;
import com.k4sm.prody.Exceptions.CategoryNotFoundException;
import com.k4sm.prody.Exceptions.ProductNotFoundException;
import com.k4sm.prody.Models.Category;
import com.k4sm.prody.Models.Product;

@Service
public class productService implements productServiceInterface {

    private RestTemplate restTemplate;

    public productService() {
        this.restTemplate = new RestTemplate();
    }

    public List<Product> getAllProducts() {
        String url = "https://fakestoreapi.com/products";

        ParameterizedTypeReference<List<FakeProductDTO>> responseType = new ParameterizedTypeReference<>() {
        };
        ResponseEntity<List<FakeProductDTO>> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                responseType);

        List<FakeProductDTO> fakeStoreProductDTOS = responseEntity.getBody();

        List<Product> products = new ArrayList<>();

        for (FakeProductDTO fakeStoreProductDTO : fakeStoreProductDTOS) {
            products.add(fakeProductToProductMapper(fakeStoreProductDTO));
        }

        return products;

    }

    public Product getProductById(int id) {
        String url = "https://fakestoreapi.com/products/" + id;

        FakeProductDTO fakeStoreProductDTO = restTemplate.getForObject(
                url,
                FakeProductDTO.class);

        if (fakeStoreProductDTO == null) {
            throw new ProductNotFoundException(String.format("product with id: %s not found", id));
        }

        Product product = fakeProductToProductMapper(fakeStoreProductDTO);

        return product;
    }

    public List<Category> getAllCategories() {
        String url = "https://fakestoreapi.com/products/categories";

        String[] categories = restTemplate.getForObject(url, String[].class);

        List<Category> categoryList = new ArrayList<>();

        for (String category : categories) {
            Category newcat = new Category();
            newcat.setId((int) (Math.random() * 1000));
            newcat.setName(category);
            categoryList.add(newcat);
        }

        return categoryList;
    }

    public List<Product> getProductsByCategory(String category) {

        String url = "https://fakestoreapi.com/products/category/" + category;

        ParameterizedTypeReference<List<FakeProductDTO>> responseType = new ParameterizedTypeReference<>() {
        };
        ResponseEntity<List<FakeProductDTO>> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                responseType);

        List<FakeProductDTO> fakeStoreProductDTOS = responseEntity.getBody();

        if (fakeStoreProductDTOS.isEmpty()) {
            throw new ProductNotFoundException(String.format("products with catagory: %s not found", category));
        }

        List<Product> products = new ArrayList<>();

        for (FakeProductDTO fakeStoreProductDTO : fakeStoreProductDTOS) {
            Product product = fakeProductToProductMapper(fakeStoreProductDTO);
            products.add(product);
        }

        return products;
    }

    public Product createProduct(Product product) {
        String url = "https://fakestoreapi.com/products";

        FakeProductDTO fakeStoreProductDTO = productToFakeProduct(product);
        restTemplate.postForObject(
                url,
                fakeStoreProductDTO,
                FakeProductDTO.class);

        Product newProduct = fakeProductToProductMapper(fakeStoreProductDTO);

        return newProduct;
    }

    public Product updateProduct(int id, Product product) {
        String url = "https://fakestoreapi.com/products/" + id;

        FakeProductDTO updatedProduct = restTemplate.exchange(url, HttpMethod.PUT,
                new HttpEntity<FakeProductDTO>(productToFakeProduct(product)), FakeProductDTO.class).getBody();

        return fakeProductToProductMapper(updatedProduct);
    }

    public String deleteProduct(int id) {
        String url = "https://fakestoreapi.com/products/" + id;

        restTemplate.delete(url);

        return "Product with id " + id + " deleted successfully.";
    }

    public Product fakeProductToProductMapper(FakeProductDTO fakeStoreProductDTO) {
        Product product = new Product();
        product.setId(fakeStoreProductDTO.getId());
        product.setTitle(fakeStoreProductDTO.getTitle());
        product.setDescription(fakeStoreProductDTO.getDescription());
        product.setPrice(fakeStoreProductDTO.getPrice());
        product.setImage(fakeStoreProductDTO.getImage());
        product.setCategory(new Category(fakeStoreProductDTO.getId(), fakeStoreProductDTO.getCategory()));

        return product;

    }

    public FakeProductDTO productToFakeProduct(Product product) {
        FakeProductDTO fakeProductDTO = new FakeProductDTO();
        fakeProductDTO.setId(product.getId());
        fakeProductDTO.setTitle(product.getTitle());
        fakeProductDTO.setDescription(product.getDescription());
        fakeProductDTO.setPrice(product.getPrice());
        fakeProductDTO.setImage(product.getImage());
        fakeProductDTO.setCategory(product.getCategory().getName());

        return fakeProductDTO;
    }
}
