package com.k4sm.prody.realServices;

import java.util.List;
import java.util.Optional;

import com.k4sm.prody.entity.CategoryEntity;
import com.k4sm.prody.exceptions.ProductNotFoundException;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.k4sm.prody.dtos.RealCreateProductDTO;
import com.k4sm.prody.entity.ProductEntity;

@Service
@Primary
public class realProductServiceImpl  {

    private realProductServiceInterface productserviceInterface;

    private categoryServiceInterface categoryServiceInterface;

    public realProductServiceImpl(realProductServiceInterface realProductServiceInterface,categoryServiceInterface categoryServiceInterface) {
        this.productserviceInterface = realProductServiceInterface;
        this.categoryServiceInterface = categoryServiceInterface;
    }

    public List<ProductEntity> getAllProducts() {

        return this.productserviceInterface.findAll();
    }

    public ProductEntity getProductById(int id) {
        Optional<ProductEntity> product = this.productserviceInterface.findById(id);

        if (product.isEmpty()){
            throw new ProductNotFoundException(String.format("Product with id %s not found",id));
        }

        return product.get();
    }

    public List<ProductEntity> getProductsByCategory(String category) {

        return this.productserviceInterface.findProductEntitiesByCategory_Name(category);
    }

    public ProductEntity createProduct(RealCreateProductDTO product) {
        Optional<CategoryEntity> categoryVal = this.categoryServiceInterface.findCategoryEntityByName(product.getCategory());
        if (categoryVal.isEmpty() ){
            CategoryEntity category = new CategoryEntity();
            category.setName(product.getCategory());
            this.categoryServiceInterface.save(category);
        }

        ProductEntity newproduct = new ProductEntity();

        newproduct.setTitle(product.getTitle());
        newproduct.setPrice(product.getPrice());
        newproduct.setImage(product.getImage());
        newproduct.setDescription(product.getDescription());
        newproduct.setCategory(categoryVal.get());

        return this.productserviceInterface.save(newproduct);
    }

    public ProductEntity updateProduct(int id, RealCreateProductDTO product) {
        Optional<CategoryEntity> categoryVal = this.categoryServiceInterface.findCategoryEntityByName(product.getCategory());
        if (categoryVal.isEmpty() ){
            CategoryEntity category = new CategoryEntity();
            category.setName(product.getCategory());
            this.categoryServiceInterface.save(category);
        }

        ProductEntity newproduct = new ProductEntity();

        newproduct.setTitle(product.getTitle());
        newproduct.setPrice(product.getPrice());
        newproduct.setImage(product.getImage());
        newproduct.setDescription(product.getDescription());
        newproduct.setCategory(categoryVal.get());

        return this.productserviceInterface.save(newproduct);
    }

    public String deleteProduct(int id) {
        if (this.productserviceInterface.findById(id).isEmpty()){
            throw new ProductNotFoundException("Product with id " + id + " not found");
        }

        this.productserviceInterface.deleteById(id);

        return "Product with id " + id + " deleted";
    }

}
