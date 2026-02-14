package com.univtime.informatique.services;

import com.univtime.informatique.dto.promoDto.PromoDto;
import com.univtime.informatique.entities.PromoEntity;
import com.univtime.informatique.exceptions.ResourceNotFoundException;
import com.univtime.informatique.mappers.PromoMapper;
import com.univtime.informatique.repositories.PromoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PromoService {
    private final PromoRepository promoRepository;

    public PromoService(PromoRepository promoRepository) {
        this.promoRepository = promoRepository;
    }

    public List<PromoDto> findAllPromos() {
        List<PromoEntity> promoEntities = promoRepository.findAll();

        return promoEntities
                .stream()
                .map(PromoMapper::toDto)
                .collect(Collectors.toList());
    }

    public PromoDto findPromoDtoById(Integer id) {
        PromoEntity promoEntity = promoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("La promo avec l'id n'est trouvé : " + id));

        return PromoMapper.toDto(promoEntity);
    }

    public PromoEntity findPromoEntityById(Integer id) {
        return promoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("La promo avec l'id n'est trouvé : " + id));
    }

    public PromoDto createPromo(PromoDto promoDto) {
        PromoEntity promoEntity = PromoMapper.toEntity(promoDto);

        PromoEntity savedPromo = promoRepository.save(promoEntity);

        return PromoMapper.toDto(savedPromo);
    }

    public PromoDto updatePromo(PromoDto promoDto) {
        PromoEntity promoEntity = findPromoEntityById(promoDto.getIdPromo());

        promoEntity.setNomPromo(promoDto.getNomPromo());
        promoEntity.setAnneePromo(promoDto.getAnneePromo());
        promoEntity.setNbEtuPromo(promoDto.getNbEtuPromo());
        promoEntity.setDebutS1Promo(promoDto.getDebutS1Promo());
        promoEntity.setFinS1Promo(promoDto.getFinS1Promo());
        promoEntity.setDebutS2Promo(promoDto.getDebutS2Promo());
        promoEntity.setFinS2Promo(promoDto.getFinS2Promo());
        // Si besoin de update les relations il faut faire une méthode spécifique
        // ou passer par le service de la relation concerné

        // Il ne faut pas utiliser PromoMapper ici car par défaut il créer un nouvel objet et Hibernate
        // ne reconnait alors pas la "même" entité


        PromoEntity savedPromo = promoRepository.save(promoEntity);

        return PromoMapper.toDto(savedPromo);
    }

    public void deletePromoById(Integer id) {
        findPromoEntityById(id);
        promoRepository.deleteById(id);
    }
}
