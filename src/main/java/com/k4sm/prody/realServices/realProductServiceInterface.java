package com.k4sm.prody.realServices;

import com.k4sm.prody.Entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface realProductServiceInterface extends JpaRepository<ProductEntity,Integer> {

    List<ProductEntity> findProductEntitiesByCategory_Name(String category);
}
