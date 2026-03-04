package com.univtime.informatique.repositories;

import com.univtime.informatique.entities.BesoinSalleEntity;
import com.univtime.informatique.entities.ids.BesoinSalleId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BesoinSalleRepository extends JpaRepository<BesoinSalleEntity, BesoinSalleId> {

}
