package com.univtime.informatique.repositories;

import com.univtime.informatique.entities.CMEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CMRepository extends JpaRepository<CMEntity, Integer> {

}
