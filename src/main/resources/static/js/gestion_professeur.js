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

let disposState = {
    'add': { 1: [], 2: [], 3: [], 4: [], 5: [] },
    'update': { 1: [], 2: [], 3: [], 4: [], 5: [] }
};

const joursMap = { 1: 'Lundi', 2: 'Mardi', 3: 'Mercredi', 4: 'Jeudi', 5: 'Vendredi' };

function timeToMins(timeStr) {
    const [h, m] = timeStr.split(':').map(Number);
    return (h * 60) + m;
}

function minsToTimeStr(mins) {
    const h = Math.floor(mins / 60).toString().padStart(2, '0');
    const m = (mins % 60).toString().padStart(2, '0');
    return `${h}h${m}`;
}

function parseDomTime(timeStr) {
    const [h, m] = timeStr.replace('h', ':').split(':').map(Number);
    return (h * 60) + m;
}

function initDispos(formType) {
    disposState[formType] = { 1: [], 2: [], 3: [], 4: [], 5: [] };
    renderDispos(formType);
}

function addDispoSlot(formType) {
    const jour = parseInt(document.getElementById(`${formType}Jour`).value);
    const startInput = document.getElementById(`${formType}Debut`).value;
    const endInput = document.getElementById(`${formType}Fin`).value;

    if (!startInput || !endInput) return;

    const startMins = timeToMins(startInput);
    const endMins = timeToMins(endInput);

    if (startMins >= endMins) return;

    const overlap = disposState[formType][jour].some(d => (startMins < d.end && endMins > d.start));
    if (overlap) return;

    disposState[formType][jour].push({ start: startMins, end: endMins });
    disposState[formType][jour].sort((a, b) => a.start - b.start);

    document.getElementById(`${formType}Debut`).value = '';
    document.getElementById(`${formType}Fin`).value = '';

    renderDispos(formType);
}

function removeDispo(formType, jour, index) {
    disposState[formType][jour].splice(index, 1);
    renderDispos(formType);
}

function renderDispos(formType) {
    const list = document.getElementById(`${formType}DisposList`);
    const hidden = document.getElementById(`${formType}DisposHidden`);

    list.innerHTML = '';
    hidden.innerHTML = '';

    let jourIndex = 0;

    for (let j = 1; j <= 5; j++) {
        if (disposState[formType][j].length > 0) {

            hidden.insertAdjacentHTML('beforeend', `<input type="hidden" name="jours[${jourIndex}].jourSemaine" value="${j}">`);

            disposState[formType][j].forEach((dispo, idx) => {
                const div = document.createElement('div');
                div.className = 'flex justify-between items-center bg-[#455A64] border border-[#546E7A] p-2 rounded text-white text-sm';
                div.innerHTML = `
                    <span><strong class="text-[#00A99B]">${joursMap[j]}</strong> : ${minsToTimeStr(dispo.start)} - ${minsToTimeStr(dispo.end)}</span>
                    <button type="button" class="text-red-400 font-bold px-2" onclick="removeDispo('${formType}', ${j}, ${idx})">✕</button>
                `;
                list.appendChild(div);

                hidden.insertAdjacentHTML('beforeend', `<input type="hidden" name="jours[${jourIndex}].disponibiliteDto[${idx}].heureDebutDispo" value="${dispo.start}">`);
                hidden.insertAdjacentHTML('beforeend', `<input type="hidden" name="jours[${jourIndex}].disponibiliteDto[${idx}].heureFinDispo" value="${dispo.end}">`);
            });
            jourIndex++;
        }
    }
}

function openProfPopUp() {
    initDispos('add');
    document.getElementById('formAdd').reset();
    document.getElementById('profAdd').classList.add('active');
}

function closePopUpAdd() {
    document.getElementById('profAdd').classList.remove('active');
}

function editProfPopUp(btn) {
    const id = btn.dataset.id;
    const nom = btn.dataset.nom;
    const prenom = btn.dataset.prenom;
    const intervenant = btn.dataset.intervenant;

    document.getElementById('updateId').value = id;
    document.getElementById('updateNom').value = nom;
    document.getElementById('updatePrenom').value = prenom;

    if (intervenant === 'true') {
        document.getElementById('updateIntervenantOui').checked = true;
    } else {
        document.getElementById('updateIntervenantNon').checked = true;
    }

    initDispos('update');

    const tr = btn.closest('tr');
    const tds = tr.querySelectorAll('td');

    for (let i = 3; i <= 7; i++) {
        const jourSemaine = i - 2;
        const badges = tds[i].querySelectorAll('.badge-dispo');

        badges.forEach(badge => {
            const text = badge.innerText.trim();
            if (text.includes('-')) {
                const parts = text.split('-');
                disposState['update'][jourSemaine].push({
                    start: parseDomTime(parts[0].trim()),
                    end: parseDomTime(parts[1].trim())
                });
            }
        });
    }
    renderDispos('update');

    document.getElementById('profUpdate').classList.add('active');
}

function closePopUpUpdate() {
    document.getElementById('profUpdate').classList.remove('active');
}

function deleteProfPopUp(btn) {
    const id = btn.dataset.id;
    const nom = btn.dataset.nom;

    document.getElementById('deleteNom').textContent = nom;
    document.getElementById('deleteId').value = id;

    document.getElementById('profDelete').classList.add('active');
}

function closePopUpDelete() {
    document.getElementById('profDelete').classList.remove('active');
}

function filterTable() {
    const input = document.getElementById("searchInput").value.toLowerCase();
    const rows = document.querySelectorAll("#profTable tbody tr");

    rows.forEach(row => {
        if(row.cells.length > 1) {
            const nom = row.cells[0].textContent.toLowerCase();
            const prenom = row.cells[1].textContent.toLowerCase();
            if (nom.includes(input) || prenom.includes(input)) {
                row.style.display = "";
            } else {
                row.style.display = "none";
            }
        }
    });
}