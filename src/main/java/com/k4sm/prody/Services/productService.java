package com.k4sm.prody.Services;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.k4sm.prody.DTOs.FakeProductDTO;
import com.k4sm.prody.Models.Catagory;
import com.k4sm.prody.Models.Product;

@Service
public class productService implements productServiceInterface {
    
    private RestTemplate restTemplate;

    private ModelMapper modelMapper = new ModelMapper();

    public productService() {
        this.restTemplate = new RestTemplate();
        this.modelMapper = new ModelMapper();
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

        if (fakeStoreProductDTOS == null) {
            return new ArrayList<>();
        }

        for (FakeProductDTO fakeStoreProductDTO : fakeStoreProductDTOS) {
            Product product = this.modelMapper.map(fakeStoreProductDTO, Product.class);
            products.add(product);
        }

        return products;

    }

    public Product getProductById(int id) {
        String url = "https://fakestoreapi.com/products/" + id;

        FakeProductDTO fakeStoreProductDTO = restTemplate.getForObject(
                url,
                FakeProductDTO.class);

        if (fakeStoreProductDTO == null) {
            return new Product();
            // TODO add exception.
        }

        Product product = this.modelMapper.map(fakeStoreProductDTO, Product.class);

        return product;
    }

    public List<Catagory> getAllCategories() {
        String url = "https://fakestoreapi.com/products/categories";

        ParameterizedTypeReference<List<String>> responseType = new ParameterizedTypeReference<>() {
        };

        ResponseEntity<List<String>> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                responseType);

        List<String> categories = responseEntity.getBody();

        List<Catagory> categoryList = new ArrayList<>();

        if (categories == null) {
            return new ArrayList<>();
        }

        for (String category : categories) {
            Catagory newcat = new Catagory();
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

        List<Product> products = new ArrayList<>();

        if (fakeStoreProductDTOS == null) {
            return new ArrayList<>();
        }

        for (FakeProductDTO fakeStoreProductDTO : fakeStoreProductDTOS) {
            Product product = this.modelMapper.map(fakeStoreProductDTO, Product.class);
            products.add(product);
        }

        return products;
    }

    public Product createProduct(Product product) {
        String url = "https://fakestoreapi.com/products";

        FakeProductDTO fakeStoreProductDTO = this.modelMapper.map(product, FakeProductDTO.class);

        restTemplate.postForObject(
                url,
                fakeStoreProductDTO,
                FakeProductDTO.class);

        return product;
    }

    public String updateProduct(int id, Product product) {
        FakeProductDTO fakeStoreProductDTO = this.modelMapper.map(product, FakeProductDTO.class);

        String url = "https://fakestoreapi.com/products/" + id;

        restTemplate.put(
                url,
                fakeStoreProductDTO);

        return "Product with id " + id + " updated successfully.";
    }

    public String deleteProduct(int id) {
        String url = "https://fakestoreapi.com/products/" + id;

        restTemplate.delete(url);

        return "Product with id " + id + " deleted successfully.";
    }
}
