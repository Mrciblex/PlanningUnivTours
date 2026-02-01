package com.univtime.informatique.repositories;

import com.univtime.informatique.entities.BesoinSalleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoursRepository extends JpaRepository<BesoinSalleEntity, Long> {

}
