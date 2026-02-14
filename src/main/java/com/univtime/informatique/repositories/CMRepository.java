package com.univtime.informatique.repositories;

import com.univtime.informatique.entities.CMEntity;
import com.univtime.informatique.entities.ids.CMId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CMRepository extends JpaRepository<CMEntity, CMId> {

    @Query(value = "SELECT DISTINCT * FROM CM WHERE idPromo = :idPromo;",
           nativeQuery = true)
    public List<CMEntity> findByIdPromo(@Param("idPromo") Integer idPromo);
}
