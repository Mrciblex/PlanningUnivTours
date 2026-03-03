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

    @Query("SELECT SUM(r.qteTypeCours * (comp.blocHoraireTD / 60.0)) " +
            "FROM TDEntity td " +
            "JOIN td.repartitionSemaine r " +
            "JOIN td.composante comp " +
            "WHERE td.composante.idComposante = :idComp " +
            "AND td.groupe.promo.idPromo = :idPromo " +
            "AND r.numSemaine BETWEEN :startW AND :endW")
    Double sumVolumeTDBySemestre(@Param("idPromo") Integer idPromo,
                                 @Param("idComp") Integer idComp,
                                 @Param("startW") int startW,
                                 @Param("endW") int endW);
}
