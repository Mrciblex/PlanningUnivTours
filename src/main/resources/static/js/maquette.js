const AppState = {
    nbSemaines: 0,
    selections: {
        modules: [],
        composantes: [],
        professeurs: [],
        groupes: []
    },
    maquetteLignes: []
};

let currentEditingLigneId = null;
let currentSelectionType = '';
let assignationCounter = 0;

const typeConfig = {
    modules: { title: 'Importer des Modules', displayProp: 'nomModule', idProp: 'idModule' },
    professeurs: { title: 'Importer des Enseignants', displayProp: 'nomProf', idProp: 'idProf' }
};

document.addEventListener('DOMContentLoaded', () => {
    const body = document.querySelector('body');
    AppState.nbSemaines = parseInt(body.getAttribute('data-nbsem')) || 15;
    initializeAutoData();
    renderAll();
});

// --- Ajout des fonctions pour l'overlay de chargement ---
function showLoadingOverlay() {
    let overlay = document.getElementById('loadingOverlay');
    if (!overlay) {
        overlay = document.createElement('div');
        overlay.id = 'loadingOverlay';
        // Style pour prendre tout l'écran, bloquer les clics, et mettre un fond noir transparent
        overlay.style.position = 'fixed';
        overlay.style.top = '0';
        overlay.style.left = '0';
        overlay.style.width = '100vw';
        overlay.style.height = '100vh';
        overlay.style.backgroundColor = 'rgba(0, 0, 0, 0.5)';
        overlay.style.zIndex = '9999';
        overlay.style.display = 'flex';
        overlay.style.justifyContent = 'center';
        overlay.style.alignItems = 'center';
        overlay.style.color = 'white';
        overlay.style.fontSize = '1.5rem';
        overlay.style.fontWeight = 'bold';
        overlay.innerHTML = '<span>Sauvegarde en cours...</span>';
        document.body.appendChild(overlay);
    }
    overlay.style.display = 'flex';
}

function hideLoadingOverlay() {
    const overlay = document.getElementById('loadingOverlay');
    if (overlay) {
        overlay.style.display = 'none';
    }
}
// --------------------------------------------------------

function getModuleIdFromComp(c) {
    if (c.idModule !== undefined && c.idModule !== null) return c.idModule;
    if (c.moduleDto && c.moduleDto.idModule !== undefined) return c.moduleDto.idModule;
    if (c.module && c.module.idModule !== undefined) return c.module.idModule;
    if (c.moduleId !== undefined && c.moduleId !== null) return c.moduleId;
    return null;
}

function initializeAutoData() {
    if (availableData.groupes) {
        AppState.selections.groupes = availableData.groupes.map(g => ({
            id: g.idGroupe || g.id,
            displayName: g.nomGroupe,
            sousGroupes: g.sousGroupeDto || g.sousGroupes || g.sousGroupeEntities || []
        }));
    }

    if (currentPromo) {
        let promoModules = [];
        if (Array.isArray(currentPromo.promoEstComposeeDto)) {
            promoModules = currentPromo.promoEstComposeeDto.map(pec => pec.moduleDto || pec.module).filter(m => m != null);
        } else if (Array.isArray(currentPromo.modules)) {
            promoModules = currentPromo.modules;
        } else if (Array.isArray(currentPromo.moduleDto)) {
            promoModules = currentPromo.moduleDto;
        }

        AppState.selections.modules = promoModules.map(m => ({
            id: m.idModule || m.id,
            displayName: m.nomModule
        }));
    } else {
        AppState.selections.modules = [];
    }

    AppState.selections.professeurs = [];
    updateAutoComposantes();
    loadExistingMaquette();
}

function updateAutoComposantes() {
    const selectedModuleIds = AppState.selections.modules.map(m => m.id);
    if (availableData.composantes) {
        AppState.selections.composantes = availableData.composantes
            .filter(c => {
                const modId = getModuleIdFromComp(c);
                return selectedModuleIds.includes(modId);
            })
            .map(c => ({
                id: c.idComposante || c.id,
                displayName: c.nomComposante,
                idModule: getModuleIdFromComp(c)
            }));
    }
}

function loadExistingMaquette() {
    if (!currentPromo) return;
    const lignesMap = new Map();

    function addProfIfNeeded(profDto) {
        if (!profDto) return;
        const profId = profDto.idProf || profDto.id;
        if (profId && !AppState.selections.professeurs.some(p => p.id === profId)) {
            AppState.selections.professeurs.push({
                id: profId,
                displayName: `${profDto.nomProf || ''} ${profDto.prenomProf || ''}`.trim()
            });
        }
    }

    function addModuleAndCompIfNeeded(compId) {
        if (!AppState.selections.composantes.some(c => c.id === compId) && availableData.composantes) {
            const rawComp = availableData.composantes.find(c => (c.idComposante || c.id) === compId);
            if (rawComp) {
                const modId = getModuleIdFromComp(rawComp);
                if (modId && availableData.modules && !AppState.selections.modules.some(m => m.id === modId)) {
                    const rawMod = availableData.modules.find(m => (m.idModule || m.id) === modId);
                    if (rawMod) {
                        AppState.selections.modules.push({
                            id: modId,
                            displayName: rawMod.nomModule
                        });
                    }
                }
                AppState.selections.composantes.push({
                    id: compId,
                    displayName: rawComp.nomComposante,
                    idModule: modId
                });
            }
        }
    }

    function getLigne(compId) {
        addModuleAndCompIfNeeded(compId);
        if (!lignesMap.has(compId)) {
            let compObj = AppState.selections.composantes.find(c => c.id === compId);
            if (!compObj) return null;
            lignesMap.set(compId, {
                id: Math.floor(Date.now() + Math.random() * 10000),
                composante: compObj,
                assignationsMap: new Map()
            });
        }
        return lignesMap.get(compId);
    }

    function addVolume(ligne, type, profDto, cibleId, cibleNom, numSemaine, volume) {
        if (!ligne || !profDto) return;
        addProfIfNeeded(profDto);
        const profId = profDto.idProf || profDto.id;
        const profNom = `${profDto.nomProf || ''} ${profDto.prenomProf || ''}`.trim();
        const assignKey = `${type}_${profId}_${cibleId}`;
        if (!ligne.assignationsMap.has(assignKey)) {
            ligne.assignationsMap.set(assignKey, {
                type: type,
                profId: profId,
                profNom: profNom,
                cibleId: cibleId,
                cibleNom: cibleNom,
                volumes: {}
            });
        }
        ligne.assignationsMap.get(assignKey).volumes[numSemaine] = volume;
    }

    if (typeof existingCms !== 'undefined') {
        existingCms.forEach(cm => {
            const compId = cm.composanteDto?.idComposante || cm.composante?.idComposante;
            const numSemaine = cm.repartitionSemaineDto?.numSemaine || cm.repartitionSemaine?.numSemaine;
            const volume = cm.repartitionSemaineDto?.qteTypeCours || cm.repartitionSemaine?.qteTypeCours;
            const prof = cm.professeurDto || cm.professeur;
            if (compId && prof && numSemaine && volume > 0) {
                const ligne = getLigne(compId);
                addVolume(ligne, 'CM', prof, 'PROMO', 'Promotion entière', numSemaine, volume);
            }
        });
    }

    if (typeof existingTds !== 'undefined') {
        existingTds.forEach(td => {
            const compId = td.composanteDto?.idComposante || td.composante?.idComposante;
            const numSemaine = td.repartitionSemaineDto?.numSemaine || td.repartitionSemaine?.numSemaine;
            const volume = td.repartitionSemaineDto?.qteTypeCours || td.repartitionSemaine?.qteTypeCours;
            const prof = td.professeurDto || td.professeur;
            const groupe = td.groupeDto || td.groupe;
            if (compId && prof && numSemaine && volume > 0 && groupe) {
                const ligne = getLigne(compId);
                const cibleId = `GRP_${groupe.idGroupe || groupe.id}`;
                const cibleNom = `Groupe : ${groupe.nomGroupe}`;
                addVolume(ligne, 'TD', prof, cibleId, cibleNom, numSemaine, volume);
            }
        });
    }

    if (typeof existingTps !== 'undefined') {
        existingTps.forEach(tp => {
            const compId = tp.composanteDto?.idComposante || tp.composante?.idComposante;
            const numSemaine = tp.repartitionSemaineDto?.numSemaine || tp.repartitionSemaine?.numSemaine;
            const volume = tp.repartitionSemaineDto?.qteTypeCours || tp.repartitionSemaine?.qteTypeCours;
            const prof = tp.professeurDto || tp.professeur;
            const sg = tp.sousGroupeDto || tp.sousGroupe;
            if (compId && prof && numSemaine && volume > 0 && sg) {
                const ligne = getLigne(compId);
                const cibleId = `SG_${sg.idSousGroupe || sg.id}`;
                const cibleNom = `Sous-Groupe : ${sg.nomSousGroupe}`;
                addVolume(ligne, 'TP', prof, cibleId, cibleNom, numSemaine, volume);
            }
        });
    }

    AppState.maquetteLignes = Array.from(lignesMap.values()).map(l => ({
        id: l.id,
        composante: l.composante,
        assignations: Array.from(l.assignationsMap.values())
    }));
}

function openSelectionModal(type) {
    if(!typeConfig[type]) return;
    currentSelectionType = type;
    const config = typeConfig[type];
    document.getElementById('selectionModalTitle').textContent = config.title;
    const checkboxList = document.getElementById('checkboxList');
    checkboxList.innerHTML = '';
    const availableItems = availableData[type] || [];
    const selectedIds = AppState.selections[type].map(item => item.id);
    if (availableItems.length === 0) {
        checkboxList.innerHTML = `<div class="empty-selection">Aucune donnée disponible.</div>`;
    } else {
        availableItems.forEach(item => {
            const itemId = item[config.idProp] || item.id;
            const displayName = type === 'professeurs' ? `${item.nomProf} ${item.prenomProf || ''}`.trim() : item[config.displayProp];
            const isChecked = selectedIds.includes(itemId) ? 'checked' : '';
            const div = document.createElement('div');
            div.className = 'checkbox-item';
            div.innerHTML = `
                <input type="checkbox" id="chk_${type}_${itemId}" value="${itemId}" ${isChecked}>
                <label for="chk_${type}_${itemId}">${displayName}</label>
            `;
            checkboxList.appendChild(div);
        });
    }
    document.getElementById('selectionModal').classList.add('active');
}

function closeSelectionModal() {
    document.getElementById('selectionModal').classList.remove('active');
    currentSelectionType = '';
}

function confirmSelection() {
    if (!currentSelectionType) return;
    const config = typeConfig[currentSelectionType];
    const availableItems = availableData[currentSelectionType] || [];
    const checkboxes = document.querySelectorAll('#checkboxList input[type="checkbox"]');
    const newSelection = [];
    checkboxes.forEach(chk => {
        if (chk.checked) {
            const itemId = parseInt(chk.value);
            const originalItem = availableItems.find(i => {
                const id = i[config.idProp] || i.id;
                return id === itemId;
            });
            if (originalItem) {
                const standardizedItem = { ...originalItem };
                standardizedItem.id = itemId;
                standardizedItem.displayName = currentSelectionType === 'professeurs'
                    ? `${originalItem.nomProf} ${originalItem.prenomProf || ''}`.trim()
                    : originalItem[config.displayProp];
                newSelection.push(standardizedItem);
            }
        }
    });
    AppState.selections[currentSelectionType] = newSelection;
    if (currentSelectionType === 'modules') updateAutoComposantes();
    renderAll();
    closeSelectionModal();
}

function resetMaquette() {
    if(confirm("Êtes-vous sûr ? Cela rechargera la maquette telle qu'elle est sauvegardée en base.")) {
        AppState.selections.modules = [];
        AppState.selections.composantes = [];
        AppState.selections.professeurs = [];
        //AppState.selections.groupes = [];
        AppState.maquetteLignes = [];
        currentEditingLigneId = null;
        assignationCounter = 0;
        //initializeAutoData();
        renderAll();
    }
}

function removeSelection(type, id) {
    AppState.selections[type] = AppState.selections[type].filter(item => item.id !== id);
    if (type === 'modules') updateAutoComposantes();
    renderAll();
}

function removeMaquetteLigne(id) {
    AppState.maquetteLignes = AppState.maquetteLignes.filter(ligne => ligne.id !== id);
    renderTableauMaquette();
}

function renderAll() {
    renderSidebarList('modules', 'selectedModules');
    renderSidebarList('composantes', 'selectedComposantes');
    renderSidebarList('professeurs', 'selectedEnseignants');
    renderGroupesSidebar();
    renderTableauMaquette();
}

function renderSidebarList(stateKey, containerId) {
    const container = document.getElementById(containerId);
    if (!container) return;
    container.innerHTML = '';
    const items = AppState.selections[stateKey];
    if (items.length === 0) {
        container.innerHTML = `<div class="empty-selection">Aucun élément</div>`;
        return;
    }
    items.forEach(item => {
        const div = document.createElement('div');
        div.className = 'selected-item flex justify-between items-center';
        const btnRemove = (stateKey === 'modules' || stateKey === 'professeurs')
            ? `<button class="btn-remove" onclick="removeSelection('${stateKey}', ${item.id})" title="Retirer">x</button>`
            : '';
        div.innerHTML = `<span>${item.displayName}</span>${btnRemove}`;
        container.appendChild(div);
    });
}

function renderGroupesSidebar() {
    const container = document.getElementById('selectedGroupes');
    if (!container) return;
    container.innerHTML = '';
    if (AppState.selections.groupes.length === 0) {
        container.innerHTML = `<div class="empty-selection">Aucun groupe pour cette promotion</div>`;
        return;
    }
    AppState.selections.groupes.forEach(g => {
        let sgHtml = (g.sousGroupes || []).map(sg => `<span class="sg-selected-item">${sg.nomSousGroupe || 'SG'}</span>`).join(' • ');
        container.innerHTML += `
            <div class="selected-item flex-col items-start gap-1">
                <div class="selected-item-title font-bold text-sm" style="color:#00A99B">${g.displayName}</div>
                <div class="flex flex-wrap gap-1">${sgHtml}</div>
            </div>
        `;
    });
}

function renderTableauMaquette() {
    const tbody = document.getElementById('planningTableBody');
    if (!tbody) return;
    tbody.innerHTML = '';
    if (AppState.maquetteLignes.length === 0) {
        tbody.innerHTML = `<tr><td colspan="${AppState.nbSemaines + 3}" class="text-center py-12 text-red-600">Aucune matière configurée dans la maquette.</td></tr>`;
        return;
    }
    AppState.maquetteLignes.forEach(ligne => {
        const tr = document.createElement('tr');
        let html = `<td class="matiere-cell">${ligne.composante.displayName}</td>`;
        html += `<td class="prof-cell">`;
        ligne.assignations.forEach(assign => {
            let colorClass = assign.type === 'CM' ? 'type-cm' : (assign.type === 'TD' ? 'type-td' : 'type-tp');
            html += `<div class="mb-1"><span class="type-badge ${colorClass}">${assign.type}</span> <b>${assign.profNom}</b> <span class="text-[10px] opacity-70">(${assign.cibleNom})</span></div>`;
        });
        html += `</td>`;
        for(let s = 1; s <= AppState.nbSemaines; s++) {
            let cellContent = '';
            ligne.assignations.forEach(assign => {
                const vol = assign.volumes[s] || 0;
                if(vol > 0) {
                    let colorClass = assign.type === 'CM' ? 'type-cm' : (assign.type === 'TD' ? 'type-td' : 'type-tp');
                    cellContent += `<div class="mb-1"><span class="type-badge ${colorClass}">${vol}</span></div>`;
                }
            });
            if(!cellContent) cellContent = '-';
            html += `<td class="text-center text-xs align-top pt-4">${cellContent}</td>`;
        }
        html += `
            <td style="vertical-align: top; padding-top: 14px;">
                <div class="flex flex-col gap-2">
                    <button onclick="editMaquetteLigne(${ligne.id})" class="btn-action btn-edit">Modifier</button>
                    <button onclick="removeMaquetteLigne(${ligne.id})" class="btn-action btn-delete">Supprimer</button>
                </div>
            </td>`;
        tr.innerHTML = html;
        tbody.appendChild(tr);
    });
}

function openMaquetteMatierePopUp() {
    if(AppState.selections.composantes.length === 0) {
        alert("Aucune composante disponible. Veuillez importer un module d'abord.");
        return;
    }
    currentEditingLigneId = null;
    const select = document.getElementById('matiereComposante');
    select.innerHTML = '<option value="">Sélectionner une composante</option>';
    AppState.selections.composantes.forEach(comp => {
        select.innerHTML += `<option value="${comp.id}">${comp.displayName}</option>`;
    });
    document.getElementById('assignationsContainer').innerHTML = '';
    assignationCounter = 0;
    addAssignationRow();
    document.querySelector('#matiereModal .modal-title').textContent = "Configurer une matière";
    document.querySelector('#matiereForm .btn-save').textContent = "Valider et Ajouter au tableau";
    document.getElementById('matiereModal').classList.add('active');
}

function editMaquetteLigne(id) {
    const ligne = AppState.maquetteLignes.find(l => l.id === id);
    if(!ligne) return;
    currentEditingLigneId = id;
    const select = document.getElementById('matiereComposante');
    select.innerHTML = '<option value="">Sélectionner une composante</option>';
    AppState.selections.composantes.forEach(comp => {
        select.innerHTML += `<option value="${comp.id}">${comp.displayName}</option>`;
    });
    select.value = ligne.composante.id;
    document.getElementById('assignationsContainer').innerHTML = '';
    assignationCounter = 0;
    ligne.assignations.forEach(assign => {
        addAssignationRow(assign);
    });
    document.querySelector('#matiereModal .modal-title').textContent = "Modifier la matière";
    document.querySelector('#matiereForm .btn-save').textContent = "Mettre à jour le tableau";
    document.getElementById('matiereModal').classList.add('active');
}

function closeMaquetteMatierePopUp() {
    document.getElementById('matiereModal').classList.remove('active');
    document.getElementById('matiereForm').reset();
    document.getElementById('assignationsContainer').innerHTML = '';
    currentEditingLigneId = null;
}

function updateCibleOptions(selectId, type) {
    const select = document.getElementById(selectId);
    if (!select) return;
    select.innerHTML = '';
    if (type === 'CM') {
        select.innerHTML = `<option value="PROMO">Promotion entière</option>`;
    } else if (type === 'TD') {
        AppState.selections.groupes.forEach(g => {
            select.innerHTML += `<option value="GRP_${g.id}">Groupe : ${g.displayName}</option>`;
        });
    } else if (type === 'TP') {
        AppState.selections.groupes.forEach(g => {
            (g.sousGroupes || []).forEach(sg => {
                const sgId = sg.idSousGroupe || sg.id;
                select.innerHTML += `<option value="SG_${sgId}">Sous-Groupe : ${sg.nomSousGroupe}</option>`;
            });
        });
    }
}

function addAssignationRow(existingData = null) {
    if(AppState.selections.professeurs.length === 0) {
        alert("Veuillez d'abord importer des enseignants dans la barre latérale.");
        return;
    }
    const container = document.getElementById('assignationsContainer');
    const rowId = assignationCounter++;
    let profOptions = '<option value="">Choisir un professeur</option>';
    AppState.selections.professeurs.forEach(p => {
        const selected = existingData && existingData.profId == p.id ? 'selected' : '';
        profOptions += `<option value="${p.id}" ${selected}>${p.displayName}</option>`;
    });
    let weeksHtml = '';
    for(let s = 1; s <= AppState.nbSemaines; s++) {
        const volVal = existingData && existingData.volumes[s] ? existingData.volumes[s] : 0;
        weeksHtml += `
            <div class="week-input-group">
                <label>S${s}</label>
                <input type="number" min="0" class="vol-input" data-week="${s}" value="${volVal}">
            </div>
        `;
    }
    const typeVal = existingData ? existingData.type : 'CM';
    const div = document.createElement('div');
    div.className = 'assignation-card';
    div.id = `assign-${rowId}`;
    div.innerHTML = `
        <div class="assignation-header">
            <select class="form-select type-select" onchange="updateCibleOptions('cible-${rowId}', this.value)" required>
                <option value="CM" ${typeVal === 'CM' ? 'selected' : ''}>CM</option>
                <option value="TD" ${typeVal === 'TD' ? 'selected' : ''}>TD</option>
                <option value="TP" ${typeVal === 'TP' ? 'selected' : ''}>TP</option>
            </select>
            <select class="form-select prof-select" required>${profOptions}</select>
            <select class="form-select cible-select" id="cible-${rowId}" required></select>
            <button type="button" class="btn-remove-row" onclick="document.getElementById('assign-${rowId}').remove()">x</button>
        </div>
        <div class="weeks-row scrollbar">${weeksHtml}</div>
    `;
    container.appendChild(div);
    updateCibleOptions(`cible-${rowId}`, typeVal);
    if (existingData) {
        const cibleSelect = document.getElementById(`cible-${rowId}`);
        cibleSelect.value = existingData.cibleId;
    }
}

document.getElementById('matiereForm').addEventListener('submit', function(e) {
    e.preventDefault();
    const compId = parseInt(document.getElementById('matiereComposante').value);
    const compObj = AppState.selections.composantes.find(c => c.id === compId);
    const assignations = [];
    const assignCards = document.querySelectorAll('.assignation-card');
    if (assignCards.length === 0) {
        alert("Veuillez ajouter au moins une ligne d'enseignement.");
        return;
    }
    assignCards.forEach(card => {
        const type = card.querySelector('.type-select').value;
        const profSelect = card.querySelector('.prof-select');
        const cibleSelect = card.querySelector('.cible-select');
        const profId = parseInt(profSelect.value);
        const profObj = AppState.selections.professeurs.find(p => p.id === profId);
        const cibleVal = cibleSelect.value;
        const cibleNom = cibleSelect.options[cibleSelect.selectedIndex].text;
        const volumes = {};
        card.querySelectorAll('.vol-input').forEach(input => {
            const week = parseInt(input.getAttribute('data-week'));
            const val = parseInt(input.value) || 0;
            if(val > 0) volumes[week] = val;
        });
        if (Object.keys(volumes).length > 0) {
            assignations.push({
                type: type,
                profId: profId,
                profNom: profObj.displayName,
                cibleId: cibleVal,
                cibleNom: cibleNom,
                volumes: volumes
            });
        }
    });
    if (assignations.length === 0) {
        alert("Veuillez saisir des volumes horaires > 0 pour au moins une semaine.");
        return;
    }
    const existingLigneIndex = AppState.maquetteLignes.findIndex(l => l.composante.id === compId);
    if (currentEditingLigneId !== null) {
        const editIndex = AppState.maquetteLignes.findIndex(l => l.id === currentEditingLigneId);
        if (existingLigneIndex !== -1 && existingLigneIndex !== editIndex) {
            AppState.maquetteLignes[existingLigneIndex].assignations.push(...assignations);
            AppState.maquetteLignes.splice(editIndex, 1);
        } else if (editIndex !== -1) {
            AppState.maquetteLignes[editIndex] = {
                id: currentEditingLigneId,
                composante: compObj,
                assignations: assignations
            };
        }
    } else {
        if (existingLigneIndex !== -1) {
            AppState.maquetteLignes[existingLigneIndex].assignations.push(...assignations);
        } else {
            AppState.maquetteLignes.push({
                id: Date.now(),
                composante: compObj,
                assignations: assignations
            });
        }
    }
    closeMaquetteMatierePopUp();
    renderTableauMaquette();
});

function saveMaquette() {
    if (!currentPromo || !currentPromo.idPromo) {
        alert("Erreur : Impossible d'identifier la promotion actuelle.");
        return;
    }

    // Afficher l'overlay avant de démarrer la requête
    showLoadingOverlay();

    // On formate les données pour le backend
    const payload = {
        idPromo: currentPromo.idPromo,
        lignes: AppState.maquetteLignes.map(ligne => ({
            idComposante: ligne.composante.id,
            assignations: ligne.assignations.map(assign => ({
                type: assign.type, // 'CM', 'TD', ou 'TP'
                idProf: assign.profId,
                cibleId: assign.cibleId, // ex: 'PROMO', 'GRP_1', 'SG_3'
                volumes: assign.volumes // Objet { "1": 2, "2": 4, ... } (semaine -> volume)
            }))
        }))
    };

    fetch(`${baseUrl}gestionnaire-edt/maquette/save`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(payload)
    })
        .then(async response => {
            hideLoadingOverlay(); // Cacher l'overlay à la fin
            if (response.ok) {
                alert("Maquette sauvegardée avec succès !");
                //window.location.reload();
            } else {
                const errText = await response.text();
                alert("Erreur lors de la sauvegarde : " + errText);
            }
        })
        .catch(error => {
            hideLoadingOverlay(); // Cacher l'overlay même en cas d'erreur réseau
            console.error("Erreur de communication :", error);
            alert("Erreur réseau lors de la sauvegarde.");
        });
}