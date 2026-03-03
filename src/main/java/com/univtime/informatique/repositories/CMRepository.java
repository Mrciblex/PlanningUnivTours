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

    @Query("SELECT SUM(r.qteTypeCours * (comp.blocHoraireCM / 60.0)) " +
            "FROM CMEntity cm " +
            "JOIN cm.repartitionSemaine r " +
            "JOIN cm.composante comp " +
            "WHERE cm.promo.idPromo = :idPromo " +
            "AND cm.composante.idComposante = :idComp " +
            "AND r.numSemaine BETWEEN :startW AND :endW")
    Double sumVolumeCMBySemestre(@Param("idPromo") Integer idPromo,
                                 @Param("idComp") Integer idComp,
                                 @Param("startW") int startW,
                                 @Param("endW") int endW);

}
