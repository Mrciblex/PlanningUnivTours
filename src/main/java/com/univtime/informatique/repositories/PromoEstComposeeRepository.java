package com.univtime.informatique.repositories;

import com.univtime.informatique.entities.PromoEstComposeeEntity;
import com.univtime.informatique.entities.ids.PromoEstComposeeId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromoEstComposeeRepository extends JpaRepository<PromoEstComposeeEntity, PromoEstComposeeId> {

}
