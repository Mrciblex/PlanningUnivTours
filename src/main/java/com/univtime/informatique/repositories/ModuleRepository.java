package com.univtime.informatique.repositories;

import com.univtime.informatique.entities.ModuleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ModuleRepository extends JpaRepository<ModuleEntity, Integer> {
    @Query(value = """
    SELECT DISTINCT m.*
    FROM Modules m
    WHERE 
        EXISTS (
            SELECT 1 
            FROM PromoEstComposee pec
            WHERE pec.idModule = m.idModule
            AND pec.idPromo = :idPromo
        )
        OR EXISTS (
            SELECT 1
            FROM CM cm
            WHERE cm.idComposante IN (
                SELECT c.idComposante
                FROM Composantes c
                WHERE c.idModule = m.idModule
            )
            AND cm.idPromo = :idPromo
        )
        OR EXISTS (
            SELECT 1
            FROM TD td
            INNER JOIN Groupes g ON td.idGroupe = g.idGroupe
            INNER JOIN Composantes c ON td.idComposante = c.idComposante
            WHERE c.idModule = m.idModule
            AND g.idPromo = :idPromo
        )
        OR EXISTS (
            SELECT 1
            FROM TP tp
            INNER JOIN SousGroupes sg ON tp.idSousGroupe = sg.idSousGroupe
            INNER JOIN Groupes g ON sg.idGroupe = g.idGroupe
            INNER JOIN Composantes c ON tp.idComposante = c.idComposante
            WHERE c.idModule = m.idModule
            AND g.idPromo = :idPromo
        )
""", nativeQuery = true)
    List<ModuleEntity> findByIdPromo(@Param("idPromo") Integer idPromo);
}
