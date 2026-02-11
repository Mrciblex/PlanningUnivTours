package com.univtime.informatique.services;

import com.univtime.informatique.dto.tpDto.TPDto;
import com.univtime.informatique.entities.TPEntity;
import com.univtime.informatique.exceptions.ResourceNotFoundException;
import com.univtime.informatique.mappers.TPMapper;
import com.univtime.informatique.repositories.TPRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TPService {
    @Autowired
    private TPRepository tpRepository;

    public List<TPDto> findAllTPs() {
        List<TPEntity> tpEntities = tpRepository.findAll();

        return tpEntities
                .stream()
                .map(TPMapper::toDto)
                .collect(Collectors.toList());
    }

    public TPDto findTPDtoById(Integer id) {
        TPEntity tpEntity = tpRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Le TP avec l'id n'est trouvé : " + id));

        return TPMapper.toDto(tpEntity);
    }

    public TPEntity findTPEntityById(Integer id) {
        return tpRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Le TP avec l'id n'est trouvé : " + id));
    }

    public void deleteTPById(Integer id) {
        findTPEntityById(id);
        tpRepository.deleteById(id);
    }
}
