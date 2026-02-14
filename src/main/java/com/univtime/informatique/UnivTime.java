package com.univtime.informatique;

import com.univtime.informatique.algorithme.GenerationAlgorithme;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UnivTime {

    public static void main(String[] args) {
        SpringApplication.run(UnivTime.class, args);
        System.out.println("Application lancé..");
        // Le code de test de l'algorithme a été temporairement retiré pour assurer la compilation.
    }

    @Bean
    public CommandLineRunner testAlgo(GenerationAlgorithme algo) {
        return args -> {
            System.out.println("=== DÉBUT DU TEST DE L'ALGO ===");

            long startTime = System.nanoTime();
            // Appelle ta méthode ici
            algo.generatePlanning(2, 1, null);
            long endTime = System.nanoTime();
            long totalMs = (endTime - startTime) / 1_000_000;

            long minutes = (totalMs / 1000) / 60;
            long seconds = (totalMs / 1000) % 60;
            long milliseconds = totalMs % 1000;

            String formattedTime = String.format("%02d:%02d:%03d", minutes, seconds, milliseconds);
            System.out.println("Temps d'exécution : " + formattedTime);

            System.out.println("=== FIN DU TEST ===");
        };
    }
}
