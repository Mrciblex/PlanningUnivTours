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

/* Gestionnaire des groupes */
function openGroupePopUp() {
    document.getElementById('groupeAdd').classList.add('active');
}

function editGroupePopUp(btn) {

    document.getElementById("groupeIdU").value = btn.dataset.id;
    document.getElementById("nomU").value = btn.dataset.nom;
    document.getElementById("nbetuUpdate").value = btn.dataset.nbetu;

    document.getElementById("groupeUpdate").classList.add('active');
}

function deleteGroupePopUp(btn) {
    document.getElementById("nomD").textContent = btn.dataset.nom;
    document.getElementById("idD").textContent = btn.dataset.id;

    document.getElementById("idGroupe").value = btn.dataset.id;

    document.getElementById("groupeDelete").classList.add('active');
}


// Fermer les pop up
function closePopUpAdd() {
    document.getElementById('groupeAdd').classList.remove('active');
}

function closePopUpUpdate() {
    document.getElementById('groupeUpdate').classList.remove('active');
}

function closePopUpDelete() {
    document.getElementById('groupeDelete').classList.remove('active');
}

/* Gestionnaire des sous-groupes */
function openSousGroupePopUp() {
    document.getElementById('sousgroupeAdd').classList.add('active');
}

function editSousGroupePopUp(btn) {

    const id = btn.dataset.id;
    const nom = btn.dataset.nom;

    document.getElementById("groupeSGIdU").value = id;
    document.getElementById("nomSGU").value = nom;
    document.getElementById("nbetuSGU").value = nbetu;

    document.getElementById("sousgroupeUpdate").classList.add('active');
}

function deleteSousGroupePopUp(btn) {

    document.getElementById("nomSGD").textContent = btn.dataset.nom;

    document.getElementById("sousgroupeDelete").classList.add('active');
}

// Fermer les pop up
function closePopUpSGAdd() {
    document.getElementById('sousgroupeAdd').classList.remove('active');
}

function closePopUpSGUpdate() {
    document.getElementById('sousgroupeUpdate').classList.remove('active');
}

function closePopUpSGDelete() {
    document.getElementById('sousgroupeDelete').classList.remove('active');
}
