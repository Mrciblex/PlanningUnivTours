package com.univtime.informatique.services;

import com.univtime.informatique.dto.composanteDto.ComposanteDto;
import com.univtime.informatique.entities.ComposanteEntity;
import com.univtime.informatique.entities.ModuleEntity;
import com.univtime.informatique.exceptions.ResourceNotFoundException;
import com.univtime.informatique.mappers.ComposanteMapper;
import com.univtime.informatique.repositories.ComposanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ComposanteService {
    @Autowired
    private ComposanteRepository composanteRepository;

    @Autowired
    private ModuleService moduleService;

    public List<ComposanteDto> findAllComposantes() {
        List<ComposanteEntity> composanteEntities = composanteRepository.findAll();

        return composanteEntities
                .stream()
                .map(ComposanteMapper::toDto)
                .collect(Collectors.toList());
    }
    public ComposanteDto findComposanteDtoById(Integer id) {
        ComposanteEntity composanteEntity = composanteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("La composante avec l'id n'est trouvé : " + id));

        return ComposanteMapper.toDto(composanteEntity);
    }

    public List<ComposanteDto> findComposantesDtoByIdPromo(Integer idPromo) {
        List<ComposanteEntity> composanteEntity = composanteRepository.findByIdPromo(idPromo);

        return composanteEntity.stream()
                .map(ComposanteMapper::toDto)
                .collect(Collectors.toList());
    }

    public ComposanteEntity findComposanteEntityById(Integer id) {
        return composanteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("La composante avec l'id n'est trouvé : " + id));
    }

    public ComposanteDto createComposante(ComposanteDto composanteDto) {
        // Vérifie la clé étrangère de module
        if (composanteDto.getModuleDto().getIdModule() == null) {
            throw new ResourceNotFoundException("L'id du module est obligatoire");
        }

        ComposanteEntity composanteEntity = ComposanteMapper.toEntity(composanteDto);

        Integer idModuleR = composanteDto.getModuleDto().getIdModule();

        ModuleEntity moduleEntity = moduleService.findModuleEntityById(idModuleR);
        moduleEntity.setIdModule(moduleEntity.getIdModule());

        ComposanteEntity savedComposante = composanteRepository.save(composanteEntity);

        return ComposanteMapper.toDto(savedComposante);
    }

    public ComposanteDto updateComposante(ComposanteDto composanteDto) {
        ComposanteEntity composanteEntity = findComposanteEntityById(composanteDto.getIdComposante());

        ComposanteMapper.toEntity(composanteDto);

        if (composanteDto.getModuleDto().getIdModule() != null) {
            Integer currentIdModule = composanteEntity.getModule().getIdModule();

            if (currentIdModule == null || !currentIdModule.equals(composanteDto.getModuleDto().getIdModule())) {
                ModuleEntity moduleEntity = moduleService.findModuleEntityById(composanteDto.getModuleDto().getIdModule());
                composanteEntity.setModule(moduleEntity);
            }
        }

        ComposanteEntity updatedComposante = composanteRepository.save(composanteEntity);

        return ComposanteMapper.toDto(updatedComposante);
    }

    public void deleteComposanteById(Integer id) {
        findComposanteEntityById(id);
        composanteRepository.deleteById(id);
    }
}
