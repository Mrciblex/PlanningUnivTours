/*
 * Copyright (c) 2026 Ademi Musa. Tous droits réservés.
 * Projet : univTime - Logiciel de gestion d'emplois du temps.
 *
 * Ce code source et l'algorithme associé sont la propriété exclusive de l'auteur.
 * Toute reproduction, modification ou distribution non autorisée, par quelque moyen que ce soit, est strictement interdite.
 *
 * Ce fichier fait partie du projet univTime, concédé sous licence d'usage
 * restreinte à l'Université de Tours, 37000, en France.
 */

package com.univtime.informatique.repositories;

import com.univtime.informatique.entities.CoursEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
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

    @Transactional
    @Modifying
    void deleteByComposante_IdComposante(@Param("idComposante") Integer idComposante);

    List<CoursEntity> findByComposante_IdComposante(Integer id);
}
