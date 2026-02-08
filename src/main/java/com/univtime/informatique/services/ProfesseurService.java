package com.univtime.informatique.services;

import com.univtime.informatique.repositories.ProfesseurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfesseurService {
    @Autowired
    private ProfesseurRepository professeurRepository;
}
