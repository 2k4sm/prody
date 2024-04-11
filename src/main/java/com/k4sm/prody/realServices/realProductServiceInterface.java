package com.k4sm.prody.realServices;

import com.k4sm.prody.entity.ProductEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface realProductServiceInterface extends JpaRepository<ProductEntity, Integer> {

  List<ProductEntity> findProductEntitiesByCategory_Name(String category);
}
