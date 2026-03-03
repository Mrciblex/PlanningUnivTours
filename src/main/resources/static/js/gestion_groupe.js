/* Gestionnaire des groupes */
function openGroupePopUp() {
    document.getElementById('groupeAdd').classList.add('active');
}

function editGroupePopUp(btn) {

    const id = btn.dataset.id;
    const nom = btn.dataset.nom;

    document.getElementById("groupeIdU").value = id;
    document.getElementById("nomU").value = nom;
    document.getElementById("nbetuU").value = nbetu;

    document.getElementById("groupeUpdate").classList.add('active');
}

function deleteGroupePopUp(btn) {

    document.getElementById("nomD").textContent = btn.dataset.nom;

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
