package com.univtime.informatique.services;

import com.univtime.informatique.dto.promoEstComposeeDto.PromoEstComposeeDto;
import com.univtime.informatique.entities.PromoEstComposeeEntity;
import com.univtime.informatique.exceptions.ResourceNotFoundException;
import com.univtime.informatique.mappers.PromoEstComposeeMapper;
import com.univtime.informatique.repositories.PromoEstComposeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PromoEstComposeeService {
    @Autowired
    private PromoEstComposeeRepository promoEstComposeeRepository;

    public List<PromoEstComposeeDto> findAllPromoEstComposees() {
        List<PromoEstComposeeEntity> promoEstComposeeEntities = promoEstComposeeRepository.findAll();

        return promoEstComposeeEntities
                .stream()
                .map(PromoEstComposeeMapper::toDto)
                .collect(Collectors.toList());
    }

    public PromoEstComposeeDto findPromoEstComposeeDtoById(Integer id) {
        PromoEstComposeeEntity promoEstComposeeEntity = promoEstComposeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("La promo est composee avec l'id n'est trouvé : " + id));

        return PromoEstComposeeMapper.toDto(promoEstComposeeEntity);
    }

    public PromoEstComposeeEntity findPromoEstComposeeEntityById(Integer id) {
        return promoEstComposeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("La promo est composee avec l'id n'est trouvé : " + id));
    }

    public void deletePromoEstComposeeById(Integer id) {
        findPromoEstComposeeEntityById(id);
        promoEstComposeeRepository.deleteById(id);
    }
}
