package com.univtime.informatique.repositories;

import com.univtime.informatique.entities.ModuleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromoRepository extends JpaRepository<ModuleEntity, Integer> {

}
