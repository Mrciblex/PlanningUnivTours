package com.univtime.informatique.repositories;

import com.univtime.informatique.entities.TDEntity;
import com.univtime.informatique.entities.ids.TDId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TDRepository extends JpaRepository<TDEntity, TDId> {

}
