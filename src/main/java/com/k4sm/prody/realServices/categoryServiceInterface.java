package com.k4sm.prody.realServices;

import com.k4sm.prody.entity.CategoryEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface categoryServiceInterface extends JpaRepository<CategoryEntity, Integer> {
  Optional<CategoryEntity> findCategoryEntityByName(String category);
}
