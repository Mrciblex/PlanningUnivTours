package com.univtime.informatique.repositories;

import com.univtime.informatique.entities.TDEntity;
import com.univtime.informatique.entities.ids.TDId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TDRepository extends JpaRepository<TDEntity, TDId> {

    @Query(value = "SELECT DISTINCT td.* FROM TD td INNER JOIN Groupes g USING (idGroupe) INNER JOIN Promos p USING (idPromo) WHERE p.idPromo = :idPromo;",
           nativeQuery = true)
    public List<TDEntity> findByIdPromo(@Param("idPromo") Integer idPromo);
}
