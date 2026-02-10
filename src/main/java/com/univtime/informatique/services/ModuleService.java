package com.univtime.informatique.services;

import com.univtime.informatique.dto.moduleDto.ModuleDto;
import com.univtime.informatique.entities.ModuleEntity;
import com.univtime.informatique.exceptions.ResourceNotFoundException;
import com.univtime.informatique.mappers.ModuleMapper;
import com.univtime.informatique.repositories.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ModuleService {
    @Autowired
    private ModuleRepository moduleRepository;

    public List<ModuleDto> findAllModules() {
        List<ModuleEntity> moduleEntities = moduleRepository.findAll();

        return moduleEntities
                .stream()
                .map(ModuleMapper::toDto)
                .collect(Collectors.toList());
    }

    public ModuleDto findModuleById(Integer id) {
        ModuleEntity moduleEntity = moduleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Le module avec l'id n'est pas trouvé : " + id));
        return ModuleMapper.toDto(moduleEntity);
    }

    public ModuleDto createModule(ModuleDto moduleDto) {
        ModuleEntity moduleEntity = ModuleMapper.toEntity(moduleDto);

        ModuleEntity savedModule = moduleRepository.save(moduleEntity);

        return ModuleMapper.toDto(moduleEntity);
    }

    public ModuleDto updateModule(ModuleDto moduleDto) {
        ModuleEntity moduleEntity = moduleRepository.findById(moduleDto.getIdModule())
                .orElseThrow(() -> new ResourceNotFoundException("Le module avec l'id n'est pas trouvé : " + moduleDto.getIdModule()));

        ModuleMapper.toEntity(moduleDto);

        ModuleEntity updatedModule = moduleRepository.save(moduleEntity);

        return ModuleMapper.toDto(updatedModule);
    }

    public void deleteModuleById(Integer id) {
        moduleRepository.deleteById(id);
    }
}
