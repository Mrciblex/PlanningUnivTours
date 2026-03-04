package com.univtime.informatique.repositories;

import com.univtime.informatique.entities.GroupeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GroupeRepository extends JpaRepository<GroupeEntity, Integer> {
    List<GroupeEntity> findByPromo_IdPromo(Integer idPromo);
}
