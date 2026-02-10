package com.univtime.informatique.services;

import com.univtime.informatique.repositories.GroupeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupeService {
    @Autowired
    private GroupeRepository groupeRepository;
}
