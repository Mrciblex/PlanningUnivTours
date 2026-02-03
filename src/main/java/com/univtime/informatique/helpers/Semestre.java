package com.univtime.informatique.helpers;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public record Semestre(
        LocalDate debut,
        LocalDate fin,
        long dureeJours
) {
    Semestre(LocalDate debut, LocalDate fin) {
        this(debut, fin, ChronoUnit.DAYS.between(debut, fin) + 1);
    }
}
