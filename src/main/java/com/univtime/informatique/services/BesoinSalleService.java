package com.univtime.informatique.services;

import com.univtime.informatique.dto.besoinSalleDto.BesoinSalleDto;
import com.univtime.informatique.entities.BesoinSalleEntity;
import com.univtime.informatique.exceptions.ResourceNotFoundException;
import com.univtime.informatique.mappers.BesoinSalleMapper;
import com.univtime.informatique.repositories.BesoinSalleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BesoinSalleService {
    @Autowired
    BesoinSalleRepository besoinSalleRepository;

    public List<BesoinSalleDto> findAllBesoinSalles() {
        List<BesoinSalleEntity>  besoinSalleEntities = besoinSalleRepository.findAll();

        return besoinSalleEntities
                .stream()
                .map(BesoinSalleMapper::toDto)
                .collect(Collectors.toList());
    }

    public BesoinSalleDto findBesoinSalleDtoById(Integer id) {
        BesoinSalleEntity besoinSalleEntity = besoinSalleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Le besoin salle avec l'id n'existe pas : " + id));

        return BesoinSalleMapper.toDto(besoinSalleEntity);
    }

    public BesoinSalleEntity findBesoinSalleEntityById(Integer id) {
        return besoinSalleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Le besoin salle avec l'id n'existe pas : " + id));
    }

    public void deleteBesoinSalleById(Integer id) {
        findBesoinSalleEntityById(id);
        besoinSalleRepository.deleteById(id);
    }
}
