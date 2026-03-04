package com.univtime.informatique.services;

import com.univtime.informatique.dto.repartitionSemaineDto.RepartitionSemaineDto;
import com.univtime.informatique.entities.RepartitionSemaineEntity;
import com.univtime.informatique.exceptions.ResourceNotFoundException;
import com.univtime.informatique.mappers.RepartitionSemaineMapper;
import com.univtime.informatique.repositories.RepartitionSemaineRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RepartitionSemaineService {
    private final RepartitionSemaineRepository repartitionSemaineRepository;

    public RepartitionSemaineService(RepartitionSemaineRepository repartitionSemaineRepository) {
        this.repartitionSemaineRepository = repartitionSemaineRepository;
    }

    public List<RepartitionSemaineDto> findAllRepartitionSemaines() {
        List<RepartitionSemaineEntity> repartitionSemaineEntities = repartitionSemaineRepository.findAll();

        return repartitionSemaineEntities
                .stream()
                .map(RepartitionSemaineMapper::toDto)
                .collect(Collectors.toList());
    }

    public RepartitionSemaineDto findRepartitionSemaineDtoById(Integer id) {
        RepartitionSemaineEntity repartitionSemaineEntity = repartitionSemaineRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("La repartition semaine avec l'id n'est pas trouvé : " + id));
        return RepartitionSemaineMapper.toDto(repartitionSemaineEntity);
    }

    public RepartitionSemaineEntity findRepartitionSemaineEntityById(Integer id) {
        return repartitionSemaineRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("La repartition semaine avec l'id n'est trouvé : " + id));
    }

    public RepartitionSemaineDto createRepartitionSemaine(RepartitionSemaineDto repartitionSemaineDto) {
        RepartitionSemaineEntity repartitionSemaineEntity = RepartitionSemaineMapper.toEntity(repartitionSemaineDto);

        RepartitionSemaineEntity savedRepartitionSemaine = repartitionSemaineRepository.save(repartitionSemaineEntity);

        return RepartitionSemaineMapper.toDto(savedRepartitionSemaine);
    }

    public RepartitionSemaineDto updateRepartitionSemaine(RepartitionSemaineDto repartitionSemaineDto) {
        RepartitionSemaineEntity repartitionSemaineEntity = findRepartitionSemaineEntityById(repartitionSemaineDto.getIdRepartitionSemaine());

        // RepartitionSemaineMapper.toEntity(repartitionSemaineDto);
        repartitionSemaineEntity.setNumSemaine(repartitionSemaineDto.getNumSemaine());
        repartitionSemaineEntity.setQteTypeCours(repartitionSemaineDto.getQteTypeCours());

        RepartitionSemaineEntity updatedRepartitionSemaine = repartitionSemaineRepository.save(repartitionSemaineEntity);

        return RepartitionSemaineMapper.toDto(updatedRepartitionSemaine);
    }

    public void deleteRepartitionSemaineById(Integer id) {
        findRepartitionSemaineEntityById(id);
        repartitionSemaineRepository.deleteById(id);
    }

    @Transactional
    public void forceDeleteRepartition(Integer id) {
        repartitionSemaineRepository.deleteCMByRepartitionId(id);
        repartitionSemaineRepository.deleteTDByRepartitionId(id);
        repartitionSemaineRepository.deleteTPByRepartitionId(id);

        repartitionSemaineRepository.deleteOnlyRepartition(id);
    }
}
