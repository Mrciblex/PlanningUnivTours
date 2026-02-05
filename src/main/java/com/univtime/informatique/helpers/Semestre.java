package com.univtime.informatique.helpers;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public record Semestre(
        LocalDate debut,
        LocalDate fin,
        long nbJours,
        long nbSemaines
) {
    public Semestre(LocalDate debut, LocalDate fin) {
        // On calcule les semaines du lundi au dimanche pas que 7 + 7 + 7...
        // Donc si c'est pas par défaut setup comme ça, on corrige pour avoir un bon calcul du numSemaine
        LocalDate firstWeek = debut.with(DayOfWeek.MONDAY);
        LocalDate lastWeek = fin.with(DayOfWeek.MONDAY);
        this(debut, fin, ChronoUnit.DAYS.between(debut, fin) + 1, ChronoUnit.WEEKS.between(firstWeek, lastWeek) + 1);
    }
}
