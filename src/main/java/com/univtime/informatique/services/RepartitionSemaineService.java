package com.univtime.informatique.services;

import com.univtime.informatique.dto.repartitionSemaineDto.RepartitionSemaineDto;
import com.univtime.informatique.entities.RepartitionSemaineEntity;
import com.univtime.informatique.exceptions.ResourceNotFoundException;
import com.univtime.informatique.mappers.RepartitionSemaineMapper;
import com.univtime.informatique.repositories.RepartitionSemaineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RepartitionSemaineService {
    @Autowired
    private RepartitionSemaineRepository repartitionSemaineRepository;

    public List<RepartitionSemaineDto> findAllRepartitionSemaines() {
        List<RepartitionSemaineEntity> repartitionSemaineEntities = repartitionSemaineRepository.findAll();

        return repartitionSemaineEntities
                .stream()
                .map(RepartitionSemaineMapper::toDto)
                .collect(Collectors.toList());
    }

    public RepartitionSemaineDto findRepartitionSemaineById(Integer id) {
        RepartitionSemaineEntity repartitionSemaineEntity = repartitionSemaineRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("La repartition semaine avec l'id n'est pas trouvé : " + id));
        return RepartitionSemaineMapper.toDto(repartitionSemaineEntity);
    }

    public RepartitionSemaineDto createRepartitionSemaine(RepartitionSemaineDto repartitionSemaineDto) {
        RepartitionSemaineEntity repartitionSemaineEntity = RepartitionSemaineMapper.toEntity(repartitionSemaineDto);

        RepartitionSemaineEntity savedRepartitionSemaine = repartitionSemaineRepository.save(repartitionSemaineEntity);

        return RepartitionSemaineMapper.toDto(repartitionSemaineEntity);
    }

    public RepartitionSemaineDto updateRepartitionSemaine(RepartitionSemaineDto repartitionSemaineDto) {
        RepartitionSemaineEntity repartitionSemaineEntity = repartitionSemaineRepository.findById(repartitionSemaineDto.getIdRepartitionSemaine())
                .orElseThrow(() -> new ResourceNotFoundException("La repartition semaine avec l'id n'est pas trouvé : " + repartitionSemaineDto.getIdRepartitionSemaine()));

        RepartitionSemaineMapper.toEntity(repartitionSemaineDto);

        RepartitionSemaineEntity updatedRepartitionSemaine = repartitionSemaineRepository.save(repartitionSemaineEntity);

        return RepartitionSemaineMapper.toDto(updatedRepartitionSemaine);
    }

    public void deleteRepartitionSemaineById(Integer id) {
        repartitionSemaineRepository.deleteById(id);
    }
}
