package com.univtime.informatique.repositories;

import com.univtime.informatique.entities.TPEntity;
import com.univtime.informatique.entities.ids.TPId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TPRepository extends JpaRepository<TPEntity, TPId> {

}
