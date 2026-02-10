package com.univtime.informatique.services;

import com.univtime.informatique.repositories.PromoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PromoService {
    @Autowired
    private PromoRepository promoRepository;
}
