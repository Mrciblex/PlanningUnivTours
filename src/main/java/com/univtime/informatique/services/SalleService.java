package com.univtime.informatique.services;

import com.univtime.informatique.dto.salleDto.SalleDto;
import com.univtime.informatique.entities.SalleEntity;
import com.univtime.informatique.exceptions.ResourceNotFoundException;
import com.univtime.informatique.mappers.SalleMapper;
import com.univtime.informatique.repositories.SalleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SalleService {
    @Autowired
    private SalleRepository salleRepository;

    public List<SalleDto> findAllSalles() {
        List<SalleEntity> salleEntities = salleRepository.findAll();

        return salleEntities
                .stream()
                .map(SalleMapper::toDto)
                .collect(Collectors.toList());
    }

    public SalleDto findSalleDtoById(Integer id) {
        SalleEntity salleEntity = salleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("La salle avec l'id n'est pas trouvé : " + id));

        return SalleMapper.toDto(salleEntity);
    }

    public SalleEntity findSalleEntityById(Integer id) {
        return salleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("La salle avec l'id n'est trouvé : " + id));
    }

    public SalleDto createSalle(SalleDto salleDto) {
        SalleEntity salleEntity = SalleMapper.toEntity(salleDto);

        SalleEntity savedSalle = salleRepository.save(salleEntity);

        return SalleMapper.toDto(salleEntity);
    }

    public SalleDto updateSalle(SalleDto salleDto) {
        SalleEntity salleEntity = findSalleEntityById(salleDto.getIdSalle());

        SalleMapper.toEntity(salleDto);

        SalleEntity updatedSalle = salleRepository.save(salleEntity);

        return SalleMapper.toDto(updatedSalle);
    }

    public void deleteSalleById(Integer id) {
        findSalleEntityById(id);
        salleRepository.deleteById(id);
    }
}
