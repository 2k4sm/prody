package com.k4sm.prody.realServices;

import com.k4sm.prody.entity.CategoryEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class categoryService {

    private categoryServiceInterface categoryServiceInterface;

    public categoryService(categoryServiceInterface categoryServiceInterface) {
        this.categoryServiceInterface = categoryServiceInterface;
    }

    public List<CategoryEntity> getAllCategories() {
        return this.categoryServiceInterface.findAll();
    }


}
