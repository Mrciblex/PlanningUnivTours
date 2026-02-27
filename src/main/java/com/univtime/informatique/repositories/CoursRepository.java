package com.univtime.informatique.repositories;

import com.univtime.informatique.entities.CoursEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CoursRepository extends JpaRepository<CoursEntity, Integer> {
    @Query(value = "SELECT * FROM Cours c WHERE EXISTS (SELECT 1 FROM ParticipeA pa INNER JOIN Cours c USING(idCours) INNER JOIN SousGroupes sg USING(idSousGroupe) INNER JOIN Groupes g USING(idGroupe) WHERE g.idPromo = :idPromo)",
           nativeQuery = true)
    public List<CoursEntity> findByIdPromo(@Param("idPromo") Integer idPromo);
}
