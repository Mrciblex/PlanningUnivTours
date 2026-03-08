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

package com.univtime.informatique.config;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class ConsoleStartupListener implements ApplicationListener<ApplicationReadyEvent> {

    private final Environment environment;

    // On injecte l'environnement pour lire les propriétés (port, path, etc.)
    public ConsoleStartupListener(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        // Récupération dynamique du port (8080 par défaut si non défini)
        String port = environment.getProperty("server.port", "8080");

        // Récupération du chemin (vide par défaut si non défini)
        String contextPath = environment.getProperty("server.servlet.context-path", "");

        System.out.println();
        System.out.println("=================================================");
        System.out.println("SERVEUR LANCÉ AVEC SUCCÈS !");

        // L'URL s'adapte automatiquement
        System.out.println("Accès : http://localhost:" + port + contextPath);

        System.out.println("=================================================");
        System.out.println();
    }
}