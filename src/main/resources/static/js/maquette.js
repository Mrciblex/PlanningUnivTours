// Maquette
// Ouverture de la pop up pour ajouter une maquette de matière
function openMaquetteMatierePopUp() {
    document.getElementById('matiereModal').classList.add('active');
}

// Fermeture de la Pop Up
window.closeMaquetteMatierePopUp = function() {
    document.getElementById('matiereModal').classList.remove('active');
};

// Ajout d'une nouvelle ligne d'enseignant
window.addEnseignantRow = function(type) {
    const container = document.getElementById(`${type}Professeurs`);
    if (!container) {
        return;
    }

    let counter;

    if (type === 'cm') {
        counter = cmCounter++;
    } else if (type === 'td') {
        counter = tdCounter++;
    } else {
        counter = tpCounter++;
    }

    const rowId = `${type}_row_${counter}`;
    const row = document.createElement('div');
    row.className = `enseignant-row ${type}-row`;
    row.id = rowId;

    let rowHTML = `
                <select class="form-select" name="${type}_enseignant_${counter}" required>
                    <option value="">Choisir un enseignant</option>
            `;

    // Ajout d'un enseignant depuis la sélection
    selections.enseignant.forEach(enseignant => {
        rowHTML += `<option value="${enseignant.id}">${enseignant.name}</option>`;
    });

    rowHTML += `</select>`;

    // Pour les TD / groupe
    if (type === 'td') {
        rowHTML += `
                    <select class="form-select" name="${type}_groupe_${counter}" required>
                        <option value="">Groupe</option>
                `;
        selections.groupe.forEach(g => {
            rowHTML += `<option value="${g.id}">${g.name}</option>`;
        });
        rowHTML += `</select>`;
    }

    // Pour les TP / sous-groupe
    if (type === 'tp') {
        rowHTML += `
                    <select class="form-select" name="${type}_sousgroupe_${counter}" required>
                        <option value="">Sous-groupe</option>
                `;
        selections.sousgroupe.forEach(sg => {
            rowHTML += `<option value="${sg.id}">${sg.name}</option>`;
        });
        rowHTML += `</select>`;
    }

    // Nouvelle ckeckbox
    rowHTML += `
                <div class="duration-checkboxes">
                    <label class="duration-checkbox">
                        <input type="radio" name="${type}_duration_${counter}" value="1.5" required>
                        <span>1h30</span>
                    </label>
                    <label class="duration-checkbox">
                        <input type="radio" name="${type}_duration_${counter}" value="2">
                        <span>2h</span>
                    </label>
                </div>
            `;

    rowHTML += `
                <button type="button" class="btn-remove-row" onclick="removeEnseignantRow('${rowId}')" title="Retirer">
                    ×
                </button>
            `;

    row.innerHTML = rowHTML;
    container.appendChild(row);
};

// Supprimer un ligne enseignant
window.removeEnseignantRow = function(rowId) {
    const row = document.getElementById(rowId);
    if (row) row.remove();
};

// Initialize weeks grid
function initWeeksGrid() {
    const grid = document.getElementById('weeksGrid');
    if (!grid) return;

    grid.innerHTML = '';

    for (let week = 1; week <= totalWeeks; week++) {
        const weekCard = document.createElement('div');
        weekCard.className = 'week-card';
        weekCard.innerHTML = `
                    <div class="week-card-title">Semaine ${week}</div>
                    <div class="week-hours">
                        <div class="week-hour-row">
                            <span class="type-badge type-cm">CM</span>
                            <input type="number" class="hours-input" name="cm_week_${week}" min="0" max="10" value="0">
                        </div>
                        <div class="week-hour-row">
                            <span class="type-badge type-td">TD</span>
                            <input type="number" class="hours-input" name="td_week_${week}" min="0" max="10" value="0">
                        </div>
                        <div class="week-hour-row">
                            <span class="type-badge type-tp">TP</span>
                            <input type="number" class="hours-input" name="tp_week_${week}" min="0" max="10" value="0">
                        </div>
                    </div>
                `;
        grid.appendChild(weekCard);
    }
}

// Form Submit
const matiereForm = document.getElementById('matiereForm');
if (matiereForm) {
    matiereForm.addEventListener('submit', function(e) {
        e.preventDefault();

        const formData = new FormData(e.target);
        const data = {
            composanteId: formData.get('matiereComposante'),
            moduleId: formData.get('matiereModule'),
            enseignants: {
                cm: [],
                td: [],
                tp: []
            },
            weeks: []
        };

        // Collect CM enseignants
        for (let i = 0; i < cmCounter; i++) {
            const enseignant = formData.get(`cm_enseignant_${i}`);
            const duration = formData.get(`cm_duration_${i}`);
            if (enseignant) {
                data.enseignants.cm.push({ enseignant, duration });
            }
        }

        // Collect TD enseignants
        for (let i = 0; i < tdCounter; i++) {
            const enseignant = formData.get(`td_enseignant_${i}`);
            const groupe = formData.get(`td_groupe_${i}`);
            const duration = formData.get(`td_duration_${i}`);
            if (enseignant) {
                data.enseignants.td.push({ enseignant, groupe, duration });
            }
        }

        // Collect TP enseignants
        for (let i = 0; i < tpCounter; i++) {
            const enseignant = formData.get(`tp_enseignant_${i}`);
            const sousgroupe = formData.get(`tp_sousgroupe_${i}`);
            const duration = formData.get(`tp_duration_${i}`);
            if (enseignant) {
                data.enseignants.tp.push({ enseignant, sousgroupe, duration });
            }
        }

        // Collect weekly volumes
        for (let week = 1; week <= totalWeeks; week++) {
            const cm = parseInt(formData.get(`cm_week_${week}`)) || 0;
            const td = parseInt(formData.get(`td_week_${week}`)) || 0;
            const tp = parseInt(formData.get(`tp_week_${week}`)) || 0;

            if (cm > 0 || td > 0 || tp > 0) {
                data.weeks.push({ week, cm, td, tp });
            }
        }

        console.log('Form submitted:', data);
        closeMatiereModal();
    });
}

// Close modal on outside click
const matiereModal = document.getElementById('matiereModal');
if (matiereModal) {
    matiereModal.addEventListener('click', function(e) {
        if (e.target === this) closeMatiereModal();
    });
}

window.openEditModal = function(id) {
    alert('Modifier matière ' + id);
};

window.deleteMatiere = function(id) {
    if (confirm('Supprimer cette matière ?')) {
        console.log('Delete', id);
    }
};












// test
const selections = {
    module: [],
    composante: [],
    enseignant: [],
    groupe: [],
    sousgroupe: []
};

const availableData = {
    module: [
        { id: 1, name: 'Informatique' },
        { id: 2, name: 'Mathématiques' },
        { id: 3, name: 'Physique' },
        { id: 4, name: 'Chimie' }
    ],
    composante: [
        { id: 1, name: 'UFR Sciences et Techniques' },
        { id: 2, name: 'UFR Lettres et Langues' },
        { id: 3, name: 'IUT' }
    ],
    enseignant: [
        { id: 1, name: 'Pr. Martin Dupont' },
        { id: 2, name: 'Dr. Sophie Bernard' },
        { id: 3, name: 'Dr. Jean Moreau' },
        { id: 4, name: 'Pr. Marie Leroy' }
    ],
    groupe: [
        { id: 1, name: 'Groupe A' },
        { id: 2, name: 'Groupe B' },
        { id: 3, name: 'Groupe C' }
    ],
    sousgroupe: [
        { id: 1, name: 'TD1' },
        { id: 2, name: 'TD2' },
        { id: 3, name: 'TD3' },
        { id: 4, name: 'TP1' },
        { id: 5, name: 'TP2' }
    ]
};

let currentSelectionType = null;

// Ouverture de la Pop Up
window.openSelectionModal = function(type) {
    currentSelectionType = type;
    const titles = {
        module: 'Sélectionner des modules',
        composante: 'Sélectionner des composantes',
        enseignant: 'Sélectionner des enseignants',
        groupe: 'Sélectionner des groupes',
        sousgroupe: 'Sélectionner des sous-groupes'
    };

    document.getElementById('selectionModalTitle').textContent = titles[type];

    // Generate checkboxes
    const checkboxList = document.getElementById('checkboxList');
    checkboxList.innerHTML = '';

    availableData[type].forEach(item => {
        const isChecked = selections[type].some(s => s.id === item.id);
        const div = document.createElement('div');
        div.className = 'checkbox-item';
        div.innerHTML = `
                    <input type="checkbox" id="check_${item.id}" value="${item.id}" ${isChecked ? 'checked' : ''}>
                    <label for="check_${item.id}">${item.name}</label>
                `;
        checkboxList.appendChild(div);
    });

    document.getElementById('selectionModal').classList.add('active');
};

// Fermeture de la Pop Up
window.closeSelectionModal = function() {
    document.getElementById('selectionModal').classList.remove('active');
};