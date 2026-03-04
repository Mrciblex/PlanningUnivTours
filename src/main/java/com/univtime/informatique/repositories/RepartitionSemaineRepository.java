package com.univtime.informatique.repositories;

import com.univtime.informatique.entities.RepartitionSemaineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface RepartitionSemaineRepository extends JpaRepository<RepartitionSemaineEntity, Integer> {
    @Modifying(clearAutomatically = true)
    @Query(value = "DELETE FROM CM WHERE idrepartitionsemaine = :id", nativeQuery = true)
    void deleteCMByRepartitionId(@Param("id") Integer id);

    @Modifying(clearAutomatically = true)
    @Query(value = "DELETE FROM TD WHERE idrepartitionsemaine = :id", nativeQuery = true)
    void deleteTDByRepartitionId(@Param("id") Integer id);

    @Modifying(clearAutomatically = true)
    @Query(value = "DELETE FROM TP WHERE idrepartitionsemaine = :id", nativeQuery = true)
    void deleteTPByRepartitionId(@Param("id") Integer id);

    @Modifying(clearAutomatically = true)
    @Query(value = "DELETE FROM repartitionsemaine WHERE idrepartitionsemaine = :id", nativeQuery = true)
    void deleteOnlyRepartition(@Param("id") Integer id);
}
