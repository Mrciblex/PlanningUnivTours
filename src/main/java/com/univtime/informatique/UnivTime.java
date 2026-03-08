/*
 * Copyright (c) 2026 Ademi Musa. Tous droits réservés.
 * Projet : univTime - Logiciel de gestion d'emplois du temps.
 *
 * Ce code source et l'algorithme associé sont la propriété exclusive de l'auteur.
 * Toute reproduction, modification ou distribution non autorisée, par quelque moyen que ce soit, est strictement interdite.
 *
 * Ce fichier fait partie du projet univTime, concédé sous licence d'usage
 * restreinte à l'Université de Tours, 37000, en France.
 */

package com.univtime.informatique;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UnivTime {

    public static void main(String[] args) {
        SpringApplication.run(UnivTime.class, args);
        System.out.println("Application lancé..");
        // Le code de test de l'algorithme a été temporairement retiré pour assurer la compilation.
    }

    /*
    @Bean
    public CommandLineRunner testAlgo(GenerationAlgorithme algo) {
        return args -> {
            System.out.println("=== DÉBUT DU TEST DE L'ALGO ===");

            List<MomentBanalise> mb = new ArrayList<>();
            //mb.add(new MomentBanalise("TEST", LocalDateTime.of(2025, 9, 1, 0, 0), LocalDateTime.of(2025, 9, 2, 9, 0)));

            // Appelle ta méthode ici
            AlgorithmeResponse response = algo.generatePlanning(2, 1, mb);

            System.out.println(response.afficherCoursPlaces());
            System.out.println(response.afficherCoursImpossibles());
            System.out.println("Temps d'exécution : " + response.getExecutionTime());

            System.out.println("=== FIN DU TEST ===");
        };
    }
     */
}
