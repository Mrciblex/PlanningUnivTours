package com.univtime.informatique.repositories;

import com.univtime.informatique.entities.CoursEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CoursRepository extends JpaRepository<CoursEntity, Integer> {
    @Query(value = "SELECT DISTINCT c.* FROM Cours c " +
            "INNER JOIN ParticipeA pa ON c.idCours = pa.idCours " +
            "INNER JOIN SousGroupes sg ON pa.idSousGroupe = sg.idSousGroupe " +
            "INNER JOIN Groupes g ON sg.idGroupe = g.idGroupe " +
            "INNER JOIN Promos p ON g.idPromo = p.idPromo " +
            "WHERE p.idPromo = :idPromo " +
            "AND ( " +
            "  (:numSemestre = 1 AND c.heureDebutCours BETWEEN p.debutS1Promo AND p.finS1Promo) " +
            "  OR " +
            "  (:numSemestre = 2 AND c.heureDebutCours BETWEEN p.debutS2Promo AND p.finS2Promo) " +
            ")",
           nativeQuery = true)
    public List<CoursEntity> findByIdPromoBySemestre(@Param("idPromo") Integer idPromo, @Param("numSemestre") Integer numSemestre);
}
