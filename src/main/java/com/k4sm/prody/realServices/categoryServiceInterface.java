package com.k4sm.prody.realServices;

import org.springframework.data.jpa.repository.JpaRepository;

import com.k4sm.prody.entity.CategoryEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface categoryServiceInterface extends JpaRepository<CategoryEntity, Integer> {
    Optional<CategoryEntity> findCategoryEntityByName(String category);
}
