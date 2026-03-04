package com.univtime.informatique.algorithme;

import com.univtime.informatique.dto.coursDto.CoursDto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public record AlgorithmeResponse(
        List<CoursDto> implementedCours,
        HashMap<Integer, List<CoursDto>> unImplementedCours,
        long executionTime
) {
    public String getExecutionTime(){
        long minutes = (executionTime / 1000) / 60;
        long seconds = (executionTime / 1000) % 60;
        long milliseconds = executionTime % 1000;

        return String.format("%02d:%02d:%03d", minutes, seconds, milliseconds);
    }

    /**
     * Affiche les cours placés, triés chronologiquement et groupés par jour.
     */
    public String afficherCoursPlaces() {
        if (implementedCours == null || implementedCours.isEmpty()) {
            return "=== COURS PLACÉS ===\nAucun cours n'a été placé.\n";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("\n=========================================\n");
        sb.append("              COURS PLACÉS               \n");
        sb.append("=========================================\n");

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("EEEE dd/MM/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        // 1. On trie tous les cours par ordre chronologique
        List<CoursDto> sortedCours = implementedCours.stream()
                .filter(c -> c.getHeureDebutCours() != null)
                .sorted(Comparator.comparing(CoursDto::getHeureDebutCours))
                .toList();

        LocalDate currentDate = null;

        // 2. On boucle et on affiche
        for (CoursDto cours : sortedCours) {
            LocalDate coursDate = cours.getHeureDebutCours().toLocalDate();

            // Si c'est un nouveau jour, on affiche l'en-tête de la date
            if (currentDate == null || !currentDate.equals(coursDate)) {
                currentDate = coursDate;
                sb.append("\n--- ").append(currentDate.format(dateFormatter).toUpperCase()).append(" ---\n");
            }

            String debut = cours.getHeureDebutCours().format(timeFormatter);
            String fin = cours.getHeureFinCours().format(timeFormatter);

            // Formatage des sous-groupes (sécurité si la liste est null)
            String groupes = cours.getParticipeADto() != null
                    ? cours.getParticipeADto().stream()
                    .map(p -> "SG" + p.getIdSousGroupe()) // Remplace par getNomSousGroupe() si dispo
                    .collect(Collectors.joining(", "))
                    : "Aucun";

            sb.append(String.format("[%s - %s] %-2s | Mat:%-3s | Prof:%-3s | Grp: %s\n",
                    debut, fin,
                    cours.getTypeCoursEnum(),
                    cours.getComposanteDto().getIdComposante(),
                    cours.getProfesseurDto().getIdProf(),
                    groupes));
        }

        return sb.toString();
    }

    /**
     * Affiche les cours impossibles à placer, triés et groupés par numéro de semaine.
     */
    public String afficherCoursImpossibles() {
        if (unImplementedCours == null || unImplementedCours.isEmpty()) {
            return "\n=== COURS IMPOSSIBLES ===\nAucun cours impossible à placer ! 🎉\n";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("\n=========================================\n");
        sb.append("      COURS IMPOSSIBLES À PLACER         \n");
        sb.append("=========================================\n");

        // 1. On trie les numéros de semaines
        List<Integer> sortedWeeks = unImplementedCours.keySet().stream()
                .sorted()
                .toList();

        // 2. On boucle sur chaque semaine et on affiche les cours orphelins
        for (Integer week : sortedWeeks) {
            sb.append("\n--- SEMAINE ").append(week).append(" ---\n");

            List<CoursDto> coursList = unImplementedCours.get(week);
            for (CoursDto cours : coursList) {
                String groupes = cours.getParticipeADto() != null
                        ? cours.getParticipeADto().stream()
                        .map(p -> "SG" + p.getIdSousGroupe())
                        .collect(Collectors.joining(", "))
                        : "Aucun";

                sb.append(String.format("- %-2s | Mat:%-3s | Prof:%-3s | Grp: %s\n",
                        cours.getTypeCoursEnum(),
                        cours.getComposanteDto().getIdComposante(),
                        cours.getProfesseurDto().getIdProf(),
                        groupes));
            }
        }

        return sb.toString();
    }
}
