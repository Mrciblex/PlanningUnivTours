package com.univtime.informatique.services;

import com.univtime.informatique.dto.promoEstComposeeDto.PromoEstComposeeDto;
import com.univtime.informatique.entities.*;
import com.univtime.informatique.entities.ids.PromoEstComposeeId;
import com.univtime.informatique.exceptions.ResourceNotFoundException;
import com.univtime.informatique.mappers.PromoEstComposeeMapper;
import com.univtime.informatique.repositories.PromoEstComposeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PromoEstComposeeService {
    private final PromoEstComposeeRepository promoEstComposeeRepository;

    private final PromoService promoService;

    private final ModuleService moduleService;

    public PromoEstComposeeService(PromoEstComposeeRepository promoEstComposeeRepository,
                                   PromoService promoService,
                                   ModuleService moduleService) {
        this.promoEstComposeeRepository = promoEstComposeeRepository;
        this.promoService = promoService;
        this.moduleService = moduleService;
    }

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
        if (promoEstComposeeDto.getPromoDto() == null || promoEstComposeeDto.getPromoDto().getIdPromo() == null) {
            throw new ResourceNotFoundException("L'id de la promo est obligatoire");
        }

        if (promoEstComposeeDto.getModuleDto() == null || promoEstComposeeDto.getModuleDto().getIdModule() == null) {
            throw new ResourceNotFoundException("L'id du module est obligatoire");
        }

        PromoEstComposeeEntity promoEstComposeeEntity = PromoEstComposeeMapper.toEntity(promoEstComposeeDto);

        // Promo
        Integer idPromoR = promoEstComposeeDto.getPromoDto().getIdPromo();
        PromoEntity promoEntity = promoService.findPromoEntityById(idPromoR);
        promoEstComposeeEntity.setPromo(promoEntity);

        // Module
        Integer idModuleR = promoEstComposeeDto.getModuleDto().getIdModule();
        ModuleEntity moduleEntity = moduleService.findModuleEntityById(idModuleR);
        promoEstComposeeEntity.setModule(moduleEntity);

        PromoEstComposeeEntity savedPromoEstComposee = promoEstComposeeRepository.save(promoEstComposeeEntity);

        return PromoEstComposeeMapper.toDto(savedPromoEstComposee);
    }

    public PromoEstComposeeDto updatePromoEstComposee(PromoEstComposeeId oldPromoEstComposeeId, PromoEstComposeeDto newPromoEstComposeeDto) {
        if (!oldPromoEstComposeeId.equals(newPromoEstComposeeDto.getPromoEstComposeeId())){
            deletePromoEstComposeeById(oldPromoEstComposeeId);
            return createPromoEstComposee(newPromoEstComposeeDto);
        }

        return newPromoEstComposeeDto;
    }

    public void deletePromoEstComposeeById(PromoEstComposeeId promoEstComposeeId) {
        findPromoEstComposeeEntityById(promoEstComposeeId);
        promoEstComposeeRepository.deleteById(promoEstComposeeId);
    }
}
