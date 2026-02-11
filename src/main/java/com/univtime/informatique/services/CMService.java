package com.univtime.informatique.services;

import com.univtime.informatique.dto.cmDto.CMDto;
import com.univtime.informatique.entities.CMEntity;
import com.univtime.informatique.exceptions.ResourceNotFoundException;
import com.univtime.informatique.mappers.CMMapper;
import com.univtime.informatique.repositories.CMRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CMService {
    @Autowired
    CMRepository cmRepository;

    public List<CMDto> findAllCMs() {
        List<CMEntity> cmEntities = cmRepository.findAll();

        return cmEntities
                .stream()
                .map(CMMapper::toDto)
                .collect(Collectors.toList());
    }

    public CMDto findCMDtoById(Integer id) {
        CMEntity cmEntity = cmRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Le CM avec l'id n'est trouvé : " + id));

        return CMMapper.toDto(cmEntity);
    }

    public CMEntity findCMEntityById(Integer id) {
        return cmRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Le CM avec l'id n'est trouvé : " + id));
    }

    public void deleteCMById(Integer id) {
        findCMEntityById(id);
        cmRepository.deleteById(id);
    }
}
