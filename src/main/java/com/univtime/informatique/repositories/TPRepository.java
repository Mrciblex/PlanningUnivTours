package com.univtime.informatique.repositories;

import com.univtime.informatique.entities.DisponibiliteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TPRepository extends JpaRepository<DisponibiliteEntity, Long> {

}
