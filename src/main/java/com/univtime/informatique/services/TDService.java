package com.univtime.informatique.services;

import com.univtime.informatique.dto.tdDto.TDDto;
import com.univtime.informatique.entities.TDEntity;
import com.univtime.informatique.exceptions.ResourceNotFoundException;
import com.univtime.informatique.mappers.TDMapper;
import com.univtime.informatique.repositories.TDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TDService {
    @Autowired
    private TDRepository tdRepository;

    public List<TDDto> findAllTDs() {
        List<TDEntity> tdEntities = tdRepository.findAll();

        return tdEntities
                .stream()
                .map(TDMapper::toDto)
                .collect(Collectors.toList());
    }

    public TDDto findTDDtoById(Integer id) {
        TDEntity tdEntity = tdRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Le TD avec l'id n'est trouvé : " + id));

        return TDMapper.toDto(tdEntity);
    }

    public TDEntity findTDEntityById(Integer id) {
        return tdRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Le TD avec l'id n'est trouvé : " + id));
    }

    public void deleteTDById(Integer id) {
        findTDEntityById(id);
        tdRepository.deleteById(id);
    }
}
