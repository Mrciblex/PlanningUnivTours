package com.univtime.informatique.services;

import com.univtime.informatique.repositories.JourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JourService {
    @Autowired
    private JourRepository jourRepository;
}
