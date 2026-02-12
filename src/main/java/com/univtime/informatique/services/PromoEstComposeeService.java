package com.univtime.informatique.services;

import com.univtime.informatique.dto.promoEstComposeeDto.PromoEstComposeeDto;
import com.univtime.informatique.entities.*;
import com.univtime.informatique.entities.ids.PromoEstComposeeId;
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

    @Autowired
    private PromoService promoService;

    @Autowired
    private ModuleService moduleService;

    public List<PromoEstComposeeDto> findAllPromoEstComposees() {
        List<PromoEstComposeeEntity> promoEstComposeeEntities = promoEstComposeeRepository.findAll();

        return promoEstComposeeEntities
                .stream()
                .map(PromoEstComposeeMapper::toDto)
                .collect(Collectors.toList());
    }

    public PromoEstComposeeDto findPromoEstComposeeDtoById(Integer idPromo, Integer idModule) {
        PromoEstComposeeId id = new PromoEstComposeeId(
                idPromo,
                idModule
        );
        PromoEstComposeeEntity promoEstComposeeEntity = promoEstComposeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("La promo est composee avec l'id n'est trouvé : " + id));

        return PromoEstComposeeMapper.toDto(promoEstComposeeEntity);
    }

    public PromoEstComposeeDto findPromoEstComposeeDtoById(PromoEstComposeeId promoEstComposeeId) {
        PromoEstComposeeEntity promoEstComposeeEntity = promoEstComposeeRepository.findById(promoEstComposeeId)
                .orElseThrow(() -> new ResourceNotFoundException("La promo est composee avec l'id n'existe pas : " + promoEstComposeeId));

        return PromoEstComposeeMapper.toDto(promoEstComposeeEntity);
    }

    public PromoEstComposeeEntity findPromoEstComposeeEntityById(Integer idPromo, Integer idModule) {
        PromoEstComposeeId id = new PromoEstComposeeId(
                idPromo,
                idModule
        );
        return promoEstComposeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("La promo est composee avec l'id n'est trouvé : " + id));
    }

    public PromoEstComposeeEntity findPromoEstComposeeEntityById(PromoEstComposeeId promoEstComposeeId) {
        return promoEstComposeeRepository.findById(promoEstComposeeId)
                .orElseThrow(() -> new ResourceNotFoundException("La promo est composee avec l'id n'existe pas : " + promoEstComposeeId));
    }
    
    public PromoEstComposeeDto createPromoEstComposee(PromoEstComposeeDto promoEstComposeeDto) {
        // Vérifie la clé étrangère de promo et module
        if (promoEstComposeeDto.getPromoDto().getIdPromo() == null) {
            throw new ResourceNotFoundException("L'id de la promo est obligatoire");
        }

        if (promoEstComposeeDto.getModuleDto().getIdModule() == null) {
            throw new ResourceNotFoundException("L'id du module est obligatoire");
        }

        PromoEstComposeeEntity promoEstComposeeEntity = PromoEstComposeeMapper.toEntity(promoEstComposeeDto);

        // Promo
        Integer idPromoR = promoEstComposeeDto.getPromoDto().getIdPromo();

        PromoEntity promoEntity = promoService.findPromoEntityById(idPromoR);
        promoEntity.setIdPromo(promoEntity.getIdPromo());

        // Module
        Integer idModuleR = promoEstComposeeDto.getModuleDto().getIdModule();

        ModuleEntity moduleEntity = moduleService.findModuleEntityById(idModuleR);
        moduleEntity.setIdModule(moduleEntity.getIdModule());

        PromoEstComposeeEntity savedPromoEstComposee = promoEstComposeeRepository.save(promoEstComposeeEntity);

        return PromoEstComposeeMapper.toDto(savedPromoEstComposee);
    }

    public PromoEstComposeeDto updatePromoEstComposee(PromoEstComposeeDto promoEstComposeeDto) {
        PromoEstComposeeEntity promoEstComposeeEntity = findPromoEstComposeeEntityById(promoEstComposeeDto.getPromoEstComposeeId());
        
        PromoEstComposeeMapper.toEntity(promoEstComposeeDto);

        // Promo
        if (promoEstComposeeDto.getPromoDto().getIdPromo() != null) {
            Integer currentIdPromo = promoEstComposeeDto.getPromoDto().getIdPromo();

            if (currentIdPromo == null || !currentIdPromo.equals(promoEstComposeeDto.getPromoDto().getIdPromo())) {
                PromoEntity promoEntity = promoService.findPromoEntityById(promoEstComposeeDto.getPromoDto().getIdPromo());
                promoEstComposeeEntity.setPromo(promoEntity);
            }
        }

        // Module
        if (promoEstComposeeDto.getModuleDto().getIdModule() != null) {
            Integer currentIdModule = promoEstComposeeDto.getModuleDto().getIdModule();

            if (currentIdModule == null || !currentIdModule.equals(promoEstComposeeDto.getModuleDto().getIdModule())) {
                ModuleEntity moduleEntity = moduleService.findModuleEntityById(promoEstComposeeDto.getModuleDto().getIdModule());
                promoEstComposeeEntity.setModule(moduleEntity);
            }
        }
        
        PromoEstComposeeEntity updatedPromoEstComposee = promoEstComposeeRepository.save(promoEstComposeeEntity);
        
        return PromoEstComposeeMapper.toDto(updatedPromoEstComposee);
    }

    public void deletePromoEstComposeeById(PromoEstComposeeId promoEstComposeeId) {
        findPromoEstComposeeEntityById(promoEstComposeeId);
        promoEstComposeeRepository.deleteById(promoEstComposeeId);
    }
}
