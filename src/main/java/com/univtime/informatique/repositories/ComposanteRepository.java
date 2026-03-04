package com.univtime.informatique.repositories;

import com.univtime.informatique.entities.ComposanteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ComposanteRepository extends JpaRepository<ComposanteEntity, Integer> {

    @Query(value = "SELECT DISTINCT c.* FROM Composantes c INNER JOIN Modules m USING (idModule) INNER JOIN PromoEstComposee pc USING (idModule) INNER JOIN Promos p USING (idPromo) WHERE p.idPromo = :idPromo;",
           nativeQuery = true)
    public List<ComposanteEntity> findByIdPromo(@Param("idPromo") Integer idPromo);
}
