package com.univtime.informatique.services;

import com.univtime.informatique.dto.composanteDto.ComposanteDto;
import com.univtime.informatique.entities.ComposanteEntity;
import com.univtime.informatique.entities.ModuleEntity;
import com.univtime.informatique.exceptions.ResourceNotFoundException;
import com.univtime.informatique.mappers.ComposanteMapper;
import com.univtime.informatique.repositories.ComposanteRepository;
import com.univtime.informatique.repositories.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ComposanteService {
    @Autowired
    private ComposanteRepository composanteRepository;

    @Autowired
    private ModuleRepository moduleRepository;

    public List<ComposanteDto> findAllComposantes() {
        List<ComposanteEntity> composanteEntities = composanteRepository.findAll();

        return composanteEntities
                .stream()
                .map(ComposanteMapper::toDto)
                .collect(Collectors.toList());
    }

    public ComposanteDto findComposanteById(Integer id) {
        ComposanteEntity composanteEntity = composanteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("La composante avec l'id n'est trouvé : " + id));

        return ComposanteMapper.toDto(composanteEntity);
    }

    public ComposanteDto createComposante(ComposanteDto composanteDto) {
        // Vérifie la clé étrangère de module
        if (composanteDto.getModuleDto().getIdModule() == null) {
            throw new ResourceNotFoundException("L'id du module est obligatoire");
        }

        ComposanteEntity composanteEntity = ComposanteMapper.toEntity(composanteDto);

        Integer idModuleR = composanteDto.getModuleDto().getIdModule();

        ModuleEntity moduleEntity = moduleRepository.findById(idModuleR)
                .orElseThrow(() -> new ResourceNotFoundException("Le module avec l'id n'est pas trouvé : " + composanteDto.getModuleDto().getIdModule()));
        moduleEntity.setIdModule(moduleEntity.getIdModule());

        ComposanteEntity savedComposante = composanteRepository.save(composanteEntity);

        return ComposanteMapper.toDto(savedComposante);
    }

    public ComposanteDto updateComposante(ComposanteDto composanteDto) {
        ComposanteEntity composanteEntity = composanteRepository.findById(composanteDto.getIdComposante())
                .orElseThrow(() -> new ResourceNotFoundException("La composante avec l'id n'est pas trouvé : " + composanteDto.getIdComposante()));

        ComposanteMapper.toEntity(composanteDto);

        if (composanteDto.getModuleDto().getIdModule() != null) {
            Integer currentIdModule = Optional.ofNullable(composanteEntity.getModule().getIdModule())
                    .map(ModuleEntity::getIdModule)
                    .orElse(null);

            if (currentIdModule == null || !currentIdModule.equals(composanteDto.getModuleDto().getIdModule())) {
                ModuleEntity moduleEntity = moduleRepository.findById(composanteDto.getModuleDto().getIdModule())
                        .orElseThrow(() -> new ResourceNotFoundException("Le module avec l'id n'est pas trouvé : " + composanteDto.getModuleDto().getIdModule()));
                composanteEntity.setModule(moduleEntity);
            }
        }

        ComposanteEntity updatedComposante = composanteRepository.save(composanteEntity);

        return ComposanteMapper.toDto(updatedComposante);
    }

    public void deleteComposanteById(Integer id) {
        composanteRepository.deleteById(id);
    }
}
