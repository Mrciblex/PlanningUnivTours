package com.univtime.informatique.repositories;

import com.univtime.informatique.entities.SousGroupeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SousGroupeRepository extends JpaRepository<SousGroupeEntity, Integer> {

    @Query(value = "SELECT DISTINCT s.* FROM sousgroupes s INNER JOIN Groupes g USING (idGroupe) INNER JOIN Promos p USING (idPromo) WHERE p.idPromo = :idPromo;", nativeQuery = true)
    public List<SousGroupeEntity> findByIdPromo(@Param("idPromo") Integer idPromo);
}
