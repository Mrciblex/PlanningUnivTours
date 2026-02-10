package com.univtime.informatique.services;

import com.univtime.informatique.dto.composanteDto.ComposanteDto;
import com.univtime.informatique.entities.ComposanteEntity;
import com.univtime.informatique.mappers.ComposanteMapper;
import com.univtime.informatique.repositories.ComposanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComposanteService {
    @Autowired
    private ComposanteRepository composanteRepository;

    public List<ComposanteDto> findAllComposantes() {
        List<ComposanteEntity> composantes = composanteRepository.findAll();

        return composantes.
                stream()
                .map(ComposanteMapper::toDto)
                .collect(Collectors.toList());
    }

    public ComposanteDto findComposanteById(Integer id) {
        /*
        ComposanteEntity composante = composanteRepository.findById(id)
                .orElse(() -> new Exception("La composante avec l'id n'est trouvé : " + id)); // Exception perso

        return ComposanteMapper.toDto(composante);
        */
        return null;
    }

    @Transactional
    public ComposanteDto createComposante(ComposanteDto composante) {
        // Vérifier les clés étrangères obligatoires

        ComposanteEntity composanteEntity = ComposanteMapper.toEntity(composante);

        ComposanteEntity savedComposante = composanteRepository.save(composanteEntity);
        return ComposanteMapper.toDto(savedComposante);
    }

}
