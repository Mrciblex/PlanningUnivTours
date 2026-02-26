package com.univtime.informatique;

import com.univtime.informatique.algorithme.AlgorithmeResponse;
import com.univtime.informatique.algorithme.GenerationAlgorithme;
import com.univtime.informatique.helpers.MomentBanalise;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
}
