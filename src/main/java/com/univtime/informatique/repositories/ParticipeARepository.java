package com.univtime.informatique.repositories;

import com.univtime.informatique.entities.ParticipeAEntity;
import com.univtime.informatique.entities.ids.ParticipeAId;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

public interface ParticipeARepository extends JpaRepository<ParticipeAEntity, ParticipeAId> {

    @Modifying
    @Transactional
    void deleteAllByCoursIdCours(Integer idCours);
}
