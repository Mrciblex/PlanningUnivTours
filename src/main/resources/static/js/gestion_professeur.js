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

/* Gestionnaire des professeurs */

// =====================
//  POP UP AJOUTER
// =====================
function openProfPopUp() {
    document.getElementById('profAdd').classList.add('active');
}

// =====================
//  POP UP MODIFIER
// =====================
function editProfPopUp(btn) {
    const id          = btn.dataset.id;
    const nom         = btn.dataset.nom;
    const prenom      = btn.dataset.prenom;
    const intervenant = btn.dataset.intervenant;

    document.getElementById('updateId').value     = id;
    document.getElementById('updateNom').value    = nom;
    document.getElementById('updatePrenom').value = prenom;

    if (intervenant === 'true') {
        document.getElementById('updateIntervenantOui').checked = true;
    } else {
        document.getElementById('updateIntervenantNon').checked = true;
    }

    // POST /gestionnaire-edt/professeurs/{idPromo}/{id}/edit
    document.getElementById('formUpdate').action =
        `${baseUrl}gestionnaire-edt/professeurs/${idPromo}/${id}/edit`;

    document.getElementById('profUpdate').classList.add('active');
}

// =====================
//  POP UP SUPPRIMER
// =====================
function deleteProfPopUp(btn) {
    const id  = btn.dataset.id;
    const nom = btn.dataset.nom;

    document.getElementById('deleteNom').textContent = nom;

    // POST /gestionnaire-edt/professeurs/{idPromo}/{id}/delete
    document.getElementById('formDelete').action =
        `${baseUrl}gestionnaire-edt/professeurs/${idPromo}/${id}/delete`;

    document.getElementById('profDelete').classList.add('active');
}

// Fermer les pop up
function closePopUpAdd() {
    document.getElementById('profAdd').classList.remove('active');
}

function closePopUpUpdate() {
    document.getElementById('profUpdate').classList.remove('active');
}

function closePopUpDelete() {
    document.getElementById('profDelete').classList.remove('active');
}