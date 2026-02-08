package com.univtime.informatique.services;

import com.univtime.informatique.repositories.CoursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoursService {
    @Autowired
    private CoursRepository coursRepository;
}
