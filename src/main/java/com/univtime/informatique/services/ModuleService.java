package com.univtime.informatique.services;

import com.univtime.informatique.repositories.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModuleService {
    @Autowired
    private ModuleRepository moduleRepository;
}
