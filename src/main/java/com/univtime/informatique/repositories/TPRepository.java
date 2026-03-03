package com.univtime.informatique.repositories;

import com.univtime.informatique.entities.TPEntity;
import com.univtime.informatique.entities.ids.TPId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TPRepository extends JpaRepository<TPEntity, TPId> {

    @Query(value = "SELECT DISTINCT tp.* FROM TP tp INNER JOIN SousGroupes sg USING (idSousGroupe) INNER JOIN Groupes grp USING (idGroupe) INNER JOIN Promos p USING (idPromo) WHERE p.idPromo = :idPromo;",
           nativeQuery = true)
    public List<TPEntity> findByIdPromo(@Param("idPromo") Integer idPromo);
}
