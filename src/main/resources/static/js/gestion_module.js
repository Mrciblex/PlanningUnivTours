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

const cleanBaseUrl = baseUrl.endsWith('/') ? baseUrl : baseUrl + '/';

function closeModal(id) {
    document.getElementById(id).classList.remove('active');
}

function openModuleModal(mode, btn = null) {
    const form = document.getElementById('moduleForm');
    const title = document.getElementById('moduleModalTitle');
    const methodInput = document.getElementById('moduleMethod');

    if (mode === 'add') {
        title.textContent = 'Ajouter un module';
        form.action = cleanBaseUrl + 'gestionnaire-edt/modules/new';
        methodInput.value = 'POST';
        document.getElementById('moduleIdInput').value = '';
        document.getElementById('nomModuleInput').value = '';
    } else {
        title.textContent = 'Modifier un module';
        form.action = cleanBaseUrl + 'gestionnaire-edt/modules/edit';
        methodInput.value = 'PUT';
        document.getElementById('moduleIdInput').value = btn.dataset.id;
        document.getElementById('nomModuleInput').value = btn.dataset.nom;
    }
    document.getElementById('moduleModal').classList.add('active');
}

function openModuleLinkModal(btn) {
    document.getElementById('moduleLinkNomD').textContent = btn.dataset.nom;
    document.getElementById('idModuleLinkInput').value = btn.dataset.id;
    document.getElementById('moduleLinkModal').classList.add('active');
}

function openModuleUnlinkModal(btn) {
    document.getElementById('moduleUnlinkNomD').textContent = btn.dataset.nom;
    document.getElementById('idModuleUnlinkInput').value = btn.dataset.id;
    document.getElementById('moduleUnlinkModal').classList.add('active');
}

function openModuleDeleteModal(btn) {
    document.getElementById('moduleNomD').textContent = btn.dataset.nom;
    document.getElementById('idModuleDeleteInput').value = btn.dataset.id;
    document.getElementById('moduleDelete').classList.add('active');
}

function calculateTotalVH() {
    const vhcm = parseFloat(document.getElementById('vhcmInput').value) || 0;
    const vhtd = parseFloat(document.getElementById('vhtdInput').value) || 0;
    const vhtp = parseFloat(document.getElementById('vhtpInput').value) || 0;
    document.getElementById('vhtInput').value = parseFloat((vhcm + vhtd + vhtp).toFixed(2));
}

function openComposanteModal(mode, btn = null) {
    const form = document.getElementById('composanteForm');
    const title = document.getElementById('composanteModalTitle');
    const methodInput = document.getElementById('composanteMethod');

    if (mode === 'add') {
        title.textContent = 'Ajouter une composante';
        form.action = cleanBaseUrl + 'gestionnaire-edt/modules/composante/new';
        methodInput.value = 'POST';
        document.getElementById('composanteIdInput').value = '';
        document.getElementById('nomComposanteInput').value = '';
        document.getElementById('vhtInput').value = '';
        document.getElementById('vhcmInput').value = '';
        document.getElementById('vhtdInput').value = '';
        document.getElementById('vhtpInput').value = '';
        document.getElementById('blcmInput').value = '';
        document.getElementById('bltdInput').value = '';
        document.getElementById('bltpInput').value = '';
        if(document.getElementById('idModuleSelect').options.length > 0) {
            document.getElementById('idModuleSelect').selectedIndex = 0;
        }
    } else {
        title.textContent = 'Modifier une composante';
        form.action = cleanBaseUrl + 'gestionnaire-edt/modules/composante/edit';
        methodInput.value = 'PUT';
        document.getElementById('composanteIdInput').value = btn.dataset.id;
        document.getElementById('nomComposanteInput').value = btn.dataset.nom;

        document.getElementById('vhtInput').value = parseFloat((btn.dataset.vht / 60).toFixed(2)) || 0;
        document.getElementById('vhcmInput').value = parseFloat((btn.dataset.vhcm / 60).toFixed(2)) || 0;
        document.getElementById('vhtdInput').value = parseFloat((btn.dataset.vhtd / 60).toFixed(2)) || 0;
        document.getElementById('vhtpInput').value = parseFloat((btn.dataset.vhtp / 60).toFixed(2)) || 0;
        document.getElementById('blcmInput').value = parseFloat((btn.dataset.blcm / 60).toFixed(2)) || 0;
        document.getElementById('bltdInput').value = parseFloat((btn.dataset.bltd / 60).toFixed(2)) || 0;
        document.getElementById('bltpInput').value = parseFloat((btn.dataset.bltp / 60).toFixed(2)) || 0;

        document.getElementById('idModuleSelect').value = btn.dataset.idmodule;
    }
    document.getElementById('composanteModal').classList.add('active');
}

function openComposanteDeleteModal(btn) {
    document.getElementById('composanteNomD').textContent = btn.dataset.nom;
    document.getElementById('idComposanteDeleteInput').value = btn.dataset.id;
    document.getElementById('composanteDelete').classList.add('active');
}

function filterModules() {
    const input = document.getElementById("searchModule").value.toLowerCase();
    const table = document.getElementById("moduleTable");
    const tr = table.getElementsByTagName("tr");
    let visibleCount = 0;

    for (let i = 1; i < tr.length; i++) {
        if (tr[i].id === "noModuleFound") continue;

        const tdName = tr[i].querySelector(".module-name");

        if (tdName) {
            const txtValue = tdName.textContent || tdName.innerText;
            if (txtValue.toLowerCase().indexOf(input) > -1) {
                tr[i].style.display = "";
                visibleCount++;
            } else {
                tr[i].style.display = "none";
            }
        }
    }

    document.getElementById("noModuleFound").style.display = visibleCount === 0 && tr.length > 2 ? "" : "none";
}

function filterComposantes() {
    const input = document.getElementById("searchComposante").value.toLowerCase();
    const table = document.getElementById("composanteTable");
    const tr = table.getElementsByTagName("tr");
    let visibleCount = 0;

    for (let i = 1; i < tr.length; i++) {
        if (tr[i].id === "noComposanteFound") continue;

        const tdName = tr[i].querySelector(".composante-name");
        const tdModule = tr[i].querySelector(".composante-module");

        if (tdName && tdModule) {
            const txtName = tdName.textContent || tdName.innerText;
            const txtModule = tdModule.textContent || tdModule.innerText;

            if (txtName.toLowerCase().indexOf(input) > -1 || txtModule.toLowerCase().indexOf(input) > -1) {
                tr[i].style.display = "";
                visibleCount++;
            } else {
                tr[i].style.display = "none";
            }
        }
    }

    document.getElementById("noComposanteFound").style.display = visibleCount === 0 && tr.length > 2 ? "" : "none";
}

function displayThings(table, state, el){
    const tr = table.getElementsByTagName("tr");
    const display = state ? 'none' : '';
    const listLength = tr.length > 1 ? tr.length - 1 : 1;

    for(let i= 1; i < listLength; i++){
        tr[i].style.display = display;
    }
    if (state){
        el.innerText = "SHOW";
    }else{
        el.innerText = "HIDE";
    }

    return !state;
}

let stateModule = false;
const showModuleBtn = document.getElementById("show-module-btn");
const tableModule = document.getElementById("moduleTable");

let stateComposante = false;
const showComposanteBtn = document.getElementById("show-comp-btn");
const tableComp = document.getElementById("composanteTable");

showModuleBtn.addEventListener("click", () => {
    stateModule = displayThings(
        tableModule,
        stateModule,
        showModuleBtn
    );
});

showComposanteBtn.addEventListener("click", () => {
    stateComposante = displayThings(
        tableComp,
        stateComposante,
        showComposanteBtn
    )
});



