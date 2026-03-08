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

// Index

// Make functions global
window.toggleDropdown = function() {
    const dropdown = document.getElementById('selection_promo_recente');
    dropdown.classList.toggle('active');
};

// Setup event listeners when DOM is ready
document.addEventListener('DOMContentLoaded', function() {
    const ancienBtn = document.getElementById('ancienEdtBtn');
    const nouvelBtn = document.getElementById('nouvelEdtBtn');
    const dropdown = document.getElementById('selection_promo_recente');

    //console.log('DOM loaded, elements:', ancienBtn, nouvelBtn, dropdown);

    // Ancien EDT - toggle dropdown
    ancienBtn.addEventListener('click', function(e) {
        e.stopPropagation();
        console.log('Ancien EDT clicked');
        toggleDropdown();
    });

    // Nouvel EDT - redirect
    nouvelBtn.addEventListener('click', function(e) {
        e.stopPropagation();
        console.log('Nouvel EDT clicked');
        createNewEDT();
    });

    // Close dropdown when clicking outside
    document.addEventListener('click', function(event) {
        if (!dropdown.contains(event.target) &&
            !ancienBtn.contains(event.target) &&
            !nouvelBtn.contains(event.target)) {
            dropdown.classList.remove('active');
        }
    });
});