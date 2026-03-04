package com.univtime.informatique.services;

import com.univtime.informatique.dto.composanteDto.ComposanteDto;
import com.univtime.informatique.entities.ComposanteEntity;
import com.univtime.informatique.entities.CoursEntity;
import com.univtime.informatique.entities.ModuleEntity;
import com.univtime.informatique.exceptions.ResourceNotFoundException;
import com.univtime.informatique.mappers.ComposanteMapper;
import com.univtime.informatique.repositories.ComposanteRepository;
import com.univtime.informatique.repositories.CoursRepository;
import com.univtime.informatique.repositories.ParticipeARepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ComposanteService {
    private final ParticipeARepository participeARepository;
    private final ComposanteRepository composanteRepository;
    private final CoursRepository coursRepository;
    private final ModuleService moduleService;

    public ComposanteService(ParticipeARepository participeARepository, ComposanteRepository composanteRepository, CoursRepository coursRepository,
                             ModuleService moduleService) {
        this.participeARepository = participeARepository;
        this.composanteRepository = composanteRepository;
        this.coursRepository = coursRepository;
        this.moduleService = moduleService;
    }

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
        if (composanteDto.getModuleDto() == null || composanteDto.getModuleDto().getIdModule() == null) {
            throw new ResourceNotFoundException("L'id du module est obligatoire");
        }

        ComposanteEntity composanteEntity = ComposanteMapper.toEntity(composanteDto);

        Integer idModuleR = composanteDto.getModuleDto().getIdModule();

        ModuleEntity moduleEntity = moduleService.findModuleEntityById(idModuleR);
        composanteEntity.setModule(moduleEntity);
        ComposanteEntity savedComposante = composanteRepository.save(composanteEntity);

        return ComposanteMapper.toDto(savedComposante);
    }

    public ComposanteDto updateComposante(ComposanteDto composanteDto) {
        ComposanteEntity composanteEntity = findComposanteEntityById(composanteDto.getIdComposante());

        // ComposanteMapper.toEntity(composanteDto);
        composanteEntity.setNomComposante(composanteDto.getNomComposante());
        composanteEntity.setVolumeHoraireTotal(composanteDto.getVolumeHoraireTotal());
        composanteEntity.setVolumeHoraireCM(composanteDto.getVolumeHoraireCM());
        composanteEntity.setVolumeHoraireTD(composanteDto.getVolumeHoraireTD());
        composanteEntity.setVolumeHoraireTP(composanteDto.getVolumeHoraireTP());
        composanteEntity.setBlocHoraireCM(composanteDto.getBlocHoraireCM());
        composanteEntity.setBlocHoraireTD(composanteDto.getBlocHoraireTD());
        composanteEntity.setBlocHoraireTP(composanteDto.getBlocHoraireTP());

        if (composanteDto.getModuleDto() != null && composanteDto.getModuleDto().getIdModule() != null) {
            Integer newIdModule = composanteDto.getModuleDto().getIdModule();
            Integer currentIdModule = (composanteEntity.getModule() != null)
                    ? composanteEntity.getModule().getIdModule()
                    : null;

            if (!newIdModule.equals(currentIdModule)) {
                ModuleEntity moduleEntity = moduleService.findModuleEntityById(newIdModule);
                composanteEntity.setModule(moduleEntity);
            }
        }

        ComposanteEntity updatedComposante = composanteRepository.save(composanteEntity);

        return ComposanteMapper.toDto(updatedComposante);
    }

    @Transactional
    public void deleteComposanteById(Integer id) {

        List<CoursEntity> coursList = coursRepository.findByComposante_IdComposante(id);

        for (CoursEntity cours : coursList) {
            participeARepository.deleteByCours_IdCours(cours.getIdCours());
        }

        coursRepository.deleteByComposante_IdComposante(id);

        composanteRepository.deleteById(id);
    }
}
