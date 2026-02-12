package com.univtime.informatique.repositories;

import com.univtime.informatique.entities.CMEntity;
import com.univtime.informatique.entities.ids.CMId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CMRepository extends JpaRepository<CMEntity, CMId> {

}
