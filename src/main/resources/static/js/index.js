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

    console.log('DOM loaded, elements:', ancienBtn, nouvelBtn, dropdown);

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

/* Gestionnaire de promo */
function openPromoPopUp() {
    document.getElementById('promoAdd').classList.add('active');
}
/**
function openPromoPopUp() {
    document.getElementById('popUpTitleA').textContent = 'Ajouter une promotion';
    document.getElementById('promoIdA').value = '';
    document.getElementById('nomA').value = '';
    document.getElementById('anneeA').value = '';
    document.getElementById('dateDebutS1A').value = '';
    document.getElementById('dateFinS1A').value = '';
    document.getElementById('dateDebutS2A').value = '';
    document.getElementById('dateFinS2A').value = '';
    document.getElementById('promoAdd').classList.add('active');
//    console.log('Pop Up classes:', this.className);
}*/
function editPromoPopUp(btn) {

    const id = btn.dataset.id;
    const nom = btn.dataset.nom;
    const annee = btn.dataset.annee;
    const debutS1 = btn.dataset.debutS1;
    const finS1 = btn.dataset.finS1;
    const debutS2 = btn.dataset.debutS2;
    const finS2 = btn.dataset.finS2;

    document.getElementById("promoIdU").value = id;
    document.getElementById("nomU").value = nom;
    document.getElementById("anneeU").value = annee;
    document.getElementById("dateDebutS1U").value = debutS1;
    document.getElementById("dateFinS1U").value = finS1;
    document.getElementById("dateDebutS2U").value = debutS2;
    document.getElementById("dateFinS2U").value = finS2;

    document.getElementById("promoUpdate").style.display = "flex";
}
/**
function editPromoPopUp(id, nom, annee, dateDebutS1, dateFinS1, dateDebutS2, dateFinS2) {
    document.getElementById('popUpTitleU').textContent = 'Modifier la promotion';
    document.getElementById('promoIdU').value = id;
    document.getElementById('nomU').value = nom;
    document.getElementById('anneeU').value = annee;
    document.getElementById('dateDebutS1U').value = dateDebutS1 || '';
    document.getElementById('dateFinS1U').value = dateFinS1 || '';
    document.getElementById('dateDebutS2U').value = dateDebutS2 || '';
    document.getElementById('dateFinS2U').value = dateFinS2 || '';
    document.getElementById('promoUpdate').classList.add('active');
}*/

function deletePromoPopUp(btn) {

    document.getElementById("nomD").textContent = btn.dataset.nom;

    document.getElementById("promoDelete").style.display = "flex";
}
/**
function deletePromoPopUp(id, nom, annee, dateDebutS1, dateFinS1, dateDebutS2, dateFinS2) {
    deletePromoId = id;
    document.getElementById('nomD').textContent = nom;
    document.getElementById('anneeD').value = annee;
    document.getElementById('dateDebutS1D').value = dateDebutS1 || '';
    document.getElementById('dateFinS1D').value = dateFinS1 || '';
    document.getElementById('dateDebutS2D').value = dateDebutS2 || '';
    document.getElementById('dateFinS2D').value = dateFinS2 || '';

/*    const s1 = (dateDebutS1 && dateFinS1)
        ? formatDate(dateDebutS1) + ' - ' + formatDate(dateFinS1)
        : 'Non défini';
    document.getElementById('deleteS1').textContent = s1;

    const s2 = (dateDebutS2 && dateFinS2)
        ? formatDate(dateDebutS2) + ' - ' + formatDate(dateFinS2)
        : 'Non défini';
    document.getElementById('deleteS2').textContent = s2;

    document.getElementById('promoDelete').classList.add('active');
}
*/
function closePopUp() {
    document.getElementById('promoAdd').classList.remove('active');
    document.getElementById('promoUpdate').classList.remove('active');
    document.getElementById('promoDelete').classList.remove('active');
}
/**
function closePopUp() {
    document.getElementById('promoAdd' || 'promoUpdate' || 'promoDelete').classList.remove('active');
}*/

// EDT
let currentDate = new Date();
let currentWeekStart = getWeekStart(currentDate);

function getWeekStart(date) {
    const d = new Date(date);
    const day = d.getDay();
    const diff = d.getDate() - day + (day === 0 ? -6 : 1); // Ajustement le dimanche
    return new Date(d.setDate(diff));
}

function updateWeekInfo() {
    const weekStart = new Date(currentWeekStart);
    const weekEnd = new Date(currentWeekStart);
    weekEnd.setDate(weekEnd.getDate() + 4); // Vendredi

    const options = { day: 'numeric', month: 'long', year: 'numeric' };
    const startStr = weekStart.toLocaleDateString('fr-FR', { day: 'numeric', month: 'long' });
    const endStr = weekEnd.toLocaleDateString('fr-FR', options);

    document.getElementById('weekInfo').textContent = `Semaine du ${startStr} au ${endStr}`;

    // Calculate week number
    const weekNum = getWeekNumber(weekStart);
    document.getElementById('weekNumber').textContent = weekNum;
}

function getWeekNumber(date) {
    const d = new Date(Date.UTC(date.getFullYear(), date.getMonth(), date.getDate()));
    const dayNum = d.getUTCDay() || 7;
    d.setUTCDate(d.getUTCDate() + 4 - dayNum);
    const yearStart = new Date(Date.UTC(d.getUTCFullYear(), 0, 1));
    return Math.ceil((((d - yearStart) / 86400000) + 1) / 7);
}

function previousWeek() {
    currentWeekStart.setDate(currentWeekStart.getDate() - 7);
    updateWeekInfo();
    loadCourses();
}

function nextWeek() {
    currentWeekStart.setDate(currentWeekStart.getDate() + 7);
    updateWeekInfo();
    loadCourses();
}

function loadCourses() {
    // TODO: Load courses from server
    // fetch('/edt/courses?week=' + currentWeekStart.toISOString())
}

// Gestion des boutons à droite de l'EDT
window.addCourse = function() {
    alert('Ajouter un cours - Pop Up à implémenter');
};

// Maquette
// Ouverture de la pop up pour ajouter une maquette de matière
function openMaquetteMatierePopUp() {
    document.getElementById('matiereModal').classList.add('active');
}

// Fermeture de la Pop Up
window.closeMaquetteMatierePopUp = function() {
    document.getElementById('matiereModal').classList.remove('active');
};

// Add Enseignant Row
window.addEnseignantRow = function(type) {
    const container = document.getElementById(`${type}Enseignants`);
    if (!container) return;

    let counter;

    if (type === 'cm') counter = cmCounter++;
    else if (type === 'td') counter = tdCounter++;
    else counter = tpCounter++;

    const rowId = `${type}_row_${counter}`;
    const row = document.createElement('div');
    row.className = `enseignant-row ${type}-row`;
    row.id = rowId;

    let rowHTML = `
                <select class="form-select" name="${type}_enseignant_${counter}" required>
                    <option value="">Choisir un enseignant</option>
            `;

    // Add enseignants from selections
    selections.enseignant.forEach(ens => {
        rowHTML += `<option value="${ens.id}">${ens.name}</option>`;
    });

    rowHTML += `</select>`;

    // For TD: add groupe dropdown
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

    // For TP: add sous-groupe dropdown
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

    // Add duration checkboxes
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

// Remove Enseignant Row
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

// Paramètre
window.openSettings = function() {
    alert('Paramètres - Pop Up à implémenter');
};

// Pop Up Exportation
window.exportEDT = function() {
    document.getElementById('exportPopUp').style.display = 'flex';
};

window.closeExportModal = function() {
    document.getElementById('exportPopUp').style.display = 'none';
};

// LES EXPORTATIONS
// CSV
window.exportToCSV = function() {
    const weekInfo = document.getElementById('weekInfo').textContent;
    const weekNum = document.getElementById('weekNumber').textContent;

    // Get calendar data
    const csv = [];
    csv.push([`Emploi du temps - ${weekInfo}`]);
    csv.push([]);

    // Header row
    const headers = ['Horaire', 'Lundi', 'Mardi', 'Mercredi', 'Jeudi', 'Vendredi'];
    csv.push(headers);

    // Get all time rows
    const rows = document.querySelectorAll('.calendar-table tbody tr');
    rows.forEach(row => {
        const cells = row.querySelectorAll('th, td');
        const rowData = [];
        cells.forEach(cell => {
            rowData.push(cell.textContent.trim() || '');
        });
        csv.push(rowData);
    });

    // Convert to CSV string
    const csvContent = csv.map(row => row.join(',')).join('\n');

    // Download
    const blob = new Blob([csvContent], { type: 'text/csv;charset=utf-8;' });
    const link = document.createElement('a');
    link.href = URL.createObjectURL(blob);
    link.download = `EDT_Semaine_${weekNum}.csv`;
    link.click();

    closeExportModal();
};

// Excel
window.exportToExcel = function() {
    const weekInfo = document.getElementById('weekInfo').textContent;
    const weekNum = document.getElementById('weekNumber').textContent;

    // Create a workbook
    let html = '<html><head><meta charset="utf-8"></head><body>';
    html += `<h1>Emploi du temps - ${weekInfo}</h1>`;
    html += '<table border="1">';

    // Get table HTML
    const table = document.querySelector('.calendar-table');
    html += table.innerHTML;

    html += '</table></body></html>';

    // Create blob and download
    const blob = new Blob([html], { type: 'application/vnd.ms-excel' });
    const link = document.createElement('a');
    link.href = URL.createObjectURL(blob);
    link.download = `EDT_Semaine_${weekNum}.xls`;
    link.click();

    closeExportModal();
};

// PDF
window.exportToPDF = function() {
    closeExportModal();

    // Create a new window with printable content
    const printWindow = window.open('', '', 'height=600,width=800');
    const weekInfo = document.getElementById('weekInfo').textContent;

    printWindow.document.write('<html><head><title>Emploi du temps</title>');
    printWindow.document.write('<style>');
    printWindow.document.write('body { font-family: Arial, sans-serif; margin: 20px; }');
    printWindow.document.write('h1 { text-align: center; color: #333; }');
    printWindow.document.write('table { width: 100%; border-collapse: collapse; margin-top: 20px; }');
    printWindow.document.write('th, td { border: 1px solid #ddd; padding: 8px; text-align: center; }');
    printWindow.document.write('th { background-color: #5b7fc7; color: white; }');
    printWindow.document.write('tbody th { background-color: #2c3e50; color: white; }');
    printWindow.document.write('@media print { @page { margin: 1cm; } }');
    printWindow.document.write('</style></head><body>');
    printWindow.document.write(`<h1>Emploi du temps - ${weekInfo}</h1>`);

    // Copy table
    const table = document.querySelector('.calendar-table').cloneNode(true);
    printWindow.document.write(table.outerHTML);

    printWindow.document.write('</body></html>');
    printWindow.document.close();

    // Wait for content to load, then print
    setTimeout(() => {
        printWindow.print();
        printWindow.close();
    }, 250);
};

// Mini calendrier
let miniCalendarDate = new Date();

function renderMiniCalendar() {
    const year = miniCalendarDate.getFullYear();
    const month = miniCalendarDate.getMonth();

    // Update month display
    const monthNames = ['Janvier', 'Février', 'Mars', 'Avril', 'Mai', 'Juin',
        'Juillet', 'Août', 'Septembre', 'Octobre', 'Novembre', 'Décembre'];
    document.getElementById('calendarMonth').textContent = `${monthNames[month]} ${year}`;

    // Get first day of month and number of days
    const firstDay = new Date(year, month, 1);
    const lastDay = new Date(year, month + 1, 0);
    const daysInMonth = lastDay.getDate();
    const startDay = firstDay.getDay() === 0 ? 6 : firstDay.getDay() - 1; // Monday = 0

    // Generate dates
    let html = '';
    const today = new Date();
    const prevMonthDays = new Date(year, month, 0).getDate();

    let dayCount = 0;
    let currentDay = 1;
    let nextMonthDay = 1;

    // Generate rows (6 weeks max)
    for (let week = 0; week < 6; week++) {
        html += '<tr>';

        for (let day = 0; day < 7; day++) {
            if (week === 0 && day < startDay) {
                // Previous month
                const prevDay = prevMonthDays - startDay + day + 1;
                html += `<td class="other-month">${prevDay}</td>`;
            } else if (currentDay > daysInMonth) {
                // Next month
                html += `<td class="other-month">${nextMonthDay++}</td>`;
            } else {
                // Current month
                const isToday = currentDay === today.getDate() && month === today.getMonth() && year === today.getFullYear();
                const classes = isToday ? 'today' : '';
                html += `<td class="${classes}" onclick="selectDate(${year}, ${month}, ${currentDay})">${currentDay}</td>`;
                currentDay++;
            }
            dayCount++;
        }

        html += '</tr>';

        // Stop if we've shown all days
        if (currentDay > daysInMonth && nextMonthDay > 7) break;
    }

    document.getElementById('calendarDates').innerHTML = html;
}

function previousMonth() {
    miniCalendarDate.setMonth(miniCalendarDate.getMonth() - 1);
    renderMiniCalendar();
}

function nextMonth() {
    miniCalendarDate.setMonth(miniCalendarDate.getMonth() + 1);
    renderMiniCalendar();
}

function selectDate(year, month, day) {
    const selectedDate = new Date(year, month, day);
    currentWeekStart = getWeekStart(selectedDate);
    updateWeekInfo();
    loadCourses();
}

// Initialisation
updateWeekInfo();
renderMiniCalendar();
