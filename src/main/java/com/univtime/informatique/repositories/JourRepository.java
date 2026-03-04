package com.univtime.informatique.repositories;

import com.univtime.informatique.entities.JourEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JourRepository extends JpaRepository<JourEntity, Integer> {
    List<JourEntity> findByProfesseurIdProf(Integer idProf);
}
