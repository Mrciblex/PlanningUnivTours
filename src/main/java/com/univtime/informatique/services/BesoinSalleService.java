package com.univtime.informatique.services;

import com.univtime.informatique.dto.besoinSalleDto.BesoinSalleDto;
import com.univtime.informatique.entities.BesoinSalleEntity;
import com.univtime.informatique.entities.ComposanteEntity;
import com.univtime.informatique.entities.SalleEntity;
import com.univtime.informatique.entities.ids.BesoinSalleId;
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

    @Autowired
    SalleService salleService;

    @Autowired
    ComposanteService composanteService;

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
        // Vérifie la clé étrangère de salle et composante
        if (besoinSalleDto.getSalleDto().getIdSalle() == null) {
            throw new ResourceNotFoundException("L'id de la salle est obligatoire");
        }

        if (besoinSalleDto.getComposanteDto().getIdComposante() == null) {
            throw new ResourceNotFoundException("L'id de la composante est obligatoire");
        }

        BesoinSalleEntity besoinSalleEntity = BesoinSalleMapper.toEntity(besoinSalleDto);

        // Salle
        Integer idSalleR = besoinSalleDto.getSalleDto().getIdSalle();

        SalleEntity salleEntity = salleService.findSalleEntityById(idSalleR);
        salleEntity.setIdSalle(salleEntity.getIdSalle());

        // Composante
        Integer idComposanteR = besoinSalleDto.getComposanteDto().getIdComposante();

        ComposanteEntity composanteEntity = composanteService.findComposanteEntityById(idComposanteR);
        composanteEntity.setIdComposante(composanteEntity.getIdComposante());

        BesoinSalleEntity savedBesoinSalle = besoinSalleRepository.save(besoinSalleEntity);

        return BesoinSalleMapper.toDto(savedBesoinSalle);
    }

    public BesoinSalleDto updateBesoinSalle(BesoinSalleDto besoinSalleDto) {
        BesoinSalleEntity besoinSalleEntity = findBesoinSalleEntityById(besoinSalleDto.getBesoinSalleId());

        BesoinSalleMapper.toEntity(besoinSalleDto);

        // Salle
        if (besoinSalleDto.getSalleDto().getIdSalle() != null) {
            Integer currentIdSalle = besoinSalleDto.getSalleDto().getIdSalle();

            if (currentIdSalle == null || !currentIdSalle.equals(besoinSalleDto.getSalleDto().getIdSalle())) {
                SalleEntity salleEntity = salleService.findSalleEntityById(besoinSalleDto.getSalleDto().getIdSalle());
                besoinSalleEntity.setSalle(salleEntity);
            }
        }

        // Composante
        if (besoinSalleDto.getComposanteDto().getIdComposante() != null) {
            Integer currentIdComposante = besoinSalleDto.getComposanteDto().getIdComposante();

            if (currentIdComposante == null || !currentIdComposante.equals(besoinSalleDto.getComposanteDto().getIdComposante())) {
                ComposanteEntity composanteEntity = composanteService.findComposanteEntityById(besoinSalleDto.getComposanteDto().getIdComposante());
                besoinSalleEntity.setComposante(composanteEntity);
            }
        }

        BesoinSalleEntity updatedBesoinSalle = besoinSalleRepository.save(besoinSalleEntity);

        return BesoinSalleMapper.toDto(updatedBesoinSalle);
    }

    public void deleteBesoinSalleById(BesoinSalleId besoinSalleId) {
        findBesoinSalleEntityById(besoinSalleId);
        besoinSalleRepository.deleteById(besoinSalleId);
    }
}
