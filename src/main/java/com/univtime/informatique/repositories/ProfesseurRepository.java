package com.univtime.informatique.repositories;

import com.univtime.informatique.entities.ProfesseurEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProfesseurRepository extends JpaRepository<ProfesseurEntity, Integer> {

    @Query(value = "SELECT p.* FROM Professeurs p WHERE EXISTS (SELECT 1 FROM CM cm WHERE cm.idProf = p.idProf AND cm.idPromo = :idPromo) OR EXISTS (SELECT 1 FROM TD td INNER JOIN Groupes g ON td.idGroupe = g.idGroupe WHERE td.idProf = p.idProf AND g.idPromo = :idPromo) OR EXISTS (SELECT 1 FROM TP tp INNER JOIN SousGroupes sg ON tp.idSousGroupe = sg.idSousGroupe INNER JOIN Groupes g ON sg.idGroupe = g.idGroupe WHERE tp.idProf = p.idProf AND g.idPromo = :idPromo);",
           nativeQuery = true)
    public List<ProfesseurEntity> findByIdPromo(@Param("idPromo") Integer idPromo);
}
