package com.k4sm.prody.realServices;

import com.k4sm.prody.entity.CategoryEntity;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class categoryService {

  private final categoryServiceInterface categoryServiceInterface;

  public categoryService(categoryServiceInterface categoryServiceInterface) {
    this.categoryServiceInterface = categoryServiceInterface;
  }

  public List<CategoryEntity> getAllCategories() {
    return this.categoryServiceInterface.findAll();
  }
}
