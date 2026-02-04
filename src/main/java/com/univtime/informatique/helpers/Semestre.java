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
    Semestre(LocalDate debut, LocalDate fin) {
        LocalDate firstWeek = debut.with(DayOfWeek.MONDAY);
        LocalDate lastWeek = fin.with(DayOfWeek.MONDAY);
        this(debut, fin, ChronoUnit.DAYS.between(debut, fin) + 1, ChronoUnit.WEEKS.between(firstWeek, lastWeek) + 1);
    }
}
