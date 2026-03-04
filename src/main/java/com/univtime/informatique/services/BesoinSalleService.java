package com.univtime.informatique.services;

import com.univtime.informatique.dto.besoinSalleDto.BesoinSalleDto;
import com.univtime.informatique.entities.BesoinSalleEntity;
import com.univtime.informatique.entities.ComposanteEntity;
import com.univtime.informatique.entities.SalleEntity;
import com.univtime.informatique.entities.ids.BesoinSalleId;
import com.univtime.informatique.exceptions.ResourceNotFoundException;
import com.univtime.informatique.mappers.BesoinSalleMapper;
import com.univtime.informatique.repositories.BesoinSalleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BesoinSalleService {
    final
    BesoinSalleRepository besoinSalleRepository;

    final
    SalleService salleService;

    final
    ComposanteService composanteService;

    public BesoinSalleService(BesoinSalleRepository besoinSalleRepository,
                              SalleService salleService,
                              ComposanteService composanteService) {
        this.besoinSalleRepository = besoinSalleRepository;
        this.salleService = salleService;
        this.composanteService = composanteService;
    }

    public List<BesoinSalleDto> findAllBesoinSalles() {
        List<BesoinSalleEntity>  besoinSalleEntities = besoinSalleRepository.findAll();

        return besoinSalleEntities
                .stream()
                .map(BesoinSalleMapper::toDto)
                .collect(Collectors.toList());
    }

    public BesoinSalleDto findBesoinSalleDtoById(Integer idSalle, Integer idComposante) {
        BesoinSalleId id = new BesoinSalleId(
            idSalle,
            idComposante
        );
        BesoinSalleEntity besoinSalleEntity = besoinSalleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Le besoin salle avec l'id n'existe pas : " + id));

        return BesoinSalleMapper.toDto(besoinSalleEntity);
    }

    public BesoinSalleDto findBesoinSalleDtoById(BesoinSalleId besoinSalleId) {
        BesoinSalleEntity besoinSalleEntity = besoinSalleRepository.findById(besoinSalleId)
                .orElseThrow(() -> new ResourceNotFoundException("Le besoin salle avec l'id n'existe pas : " + besoinSalleId));

        return BesoinSalleMapper.toDto(besoinSalleEntity);
    }

    public BesoinSalleEntity findBesoinSalleEntityById(Integer idSalle, Integer idComposante) {
        BesoinSalleId id = new BesoinSalleId(
                idSalle,
                idComposante
        );
        return besoinSalleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Le besoin salle avec l'id n'existe pas : " + id));
    }

    public BesoinSalleEntity findBesoinSalleEntityById(BesoinSalleId besoinSalleId) {
        return besoinSalleRepository.findById(besoinSalleId)
                .orElseThrow(() -> new ResourceNotFoundException("Le besoin salle avec l'id n'existe pas : " + besoinSalleId));
    }

    public BesoinSalleDto createBesoinSalle(BesoinSalleDto besoinSalleDto) {
        if (besoinSalleDto.getSalleDto() == null || besoinSalleDto.getSalleDto().getIdSalle() == null) {
            throw new ResourceNotFoundException("L'id de la salle est obligatoire");
        }

        if (besoinSalleDto.getComposanteDto() == null || besoinSalleDto.getComposanteDto().getIdComposante() == null) {
            throw new ResourceNotFoundException("L'id de la composante est obligatoire");
        }

        SalleEntity salleEntity = salleService.findSalleEntityById(besoinSalleDto.getSalleDto().getIdSalle());
        ComposanteEntity composanteEntity = composanteService.findComposanteEntityById(besoinSalleDto.getComposanteDto().getIdComposante());

        BesoinSalleEntity besoinSalleEntity = BesoinSalleMapper.toEntity(besoinSalleDto);

        besoinSalleEntity.setSalle(salleEntity);
        besoinSalleEntity.setComposante(composanteEntity);

        BesoinSalleEntity savedBesoinSalle = besoinSalleRepository.save(besoinSalleEntity);

        return BesoinSalleMapper.toDto(savedBesoinSalle);
    }

    public BesoinSalleDto updateBesoinSalle(BesoinSalleId oldId, BesoinSalleDto newDto) {
        if (!oldId.equals(newDto.getBesoinSalleId())) {
            deleteBesoinSalleById(oldId);
            return createBesoinSalle(newDto);
        }

        BesoinSalleEntity entity = findBesoinSalleEntityById(oldId);
        entity.setTypeBesoin(newDto.getTypeBesoin());

        return BesoinSalleMapper.toDto(besoinSalleRepository.save(entity));
    }

    public void deleteBesoinSalleById(BesoinSalleId besoinSalleId) {
        findBesoinSalleEntityById(besoinSalleId);
        besoinSalleRepository.deleteById(besoinSalleId);
    }
}
