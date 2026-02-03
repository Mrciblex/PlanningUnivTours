package com.univtime.informatique.repositories;

import com.univtime.informatique.entities.TDEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TDRepository extends JpaRepository<TDEntity, Integer> {

}
