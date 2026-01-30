package com.univtime.informatique;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootApplication
public class UnivTime {
    public static void main(String[] args) {
        //SpringApplication.run(UnivTime.class, args);
        System.out.println("Application lancé..");
        System.out.println("----------------- TEST ALGO -----------------");


    }

    record MomentBanalise (
            String nom,
            LocalDateTime debut,
            LocalDateTime fin
    ) {}

    record Semestre(
            LocalDate debut,
            LocalDate fin
    ) {}



}
