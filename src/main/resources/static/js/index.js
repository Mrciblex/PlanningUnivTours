// Index

// Make functions global
window.toggleDropdown = function() {
    const dropdown = document.getElementById('selection_promo_recente');
    dropdown.classList.toggle('active');
};
/*
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
*/
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
    console.log('Pop Up close:', this.className);
}*/

// EDT
// Current date tracking
let currentDate = new Date();
let currentWeekStart = getWeekStart(currentDate);

function getWeekStart(date) {
    const d = new Date(date);
    const day = d.getDay();
    const diff = d.getDate() - day + (day === 0 ? -6 : 1); // Adjust when day is Sunday
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

// Sidebar actions
window.goToCalendrier = function() {
    window.location.href = '/';
};

window.addCourse = function() {
    alert('Ajouter un cours - Modal à implémenter');
};

window.addCourseAt = function(cell) {
    const day = cell.dataset.day;
    const time = cell.dataset.time;

    console.log('Add course at day ' + day + ' at ' + time);
    // TODO: Open modal with pre-filled day and time
    alert('Ajouter un cours le jour ' + day + ' à ' + time);
};

window.manageTeachers = function() {
    window.location.href = '/professeur';
};

window.manageGroups = function() {
    window.location.href = '/groupe';
};

window.openSettings = function() {
    alert('Paramètres - Modal à implémenter');
};

window.exportEDT = function() {
    alert('Exporter - Modal à implémenter');
};

// Mini calendrier
let miniCalendrierDate = new Date();

function renderMiniCalendrier() {
    const year = miniCalendrierDate.getFullYear();
    const month = miniCalendrierDate.getMonth();

    // Update month display
    const monthNames = ['Janvier', 'Février', 'Mars', 'Avril', 'Mai', 'Juin',
        'Juillet', 'Août', 'Septembre', 'Octobre', 'Novembre', 'Décembre'];
    document.getElementById('calendrierMonth').textContent = `${monthNames[month]} ${year}`;

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

    document.getElementById('calendrierDates').innerHTML = html;
}

function previousMonth() {
    miniCalendrierDate.setMonth(miniCalendrierDate.getMonth() - 1);
    renderMiniCalendrier();
}

function nextMonth() {
    miniCalendrierDate.setMonth(miniCalendrierDate.getMonth() + 1);
    renderMiniCalendrier();
}

function selectDate(year, month, day) {
    const selectedDate = new Date(year, month, day);
    currentWeekStart = getWeekStart(selectedDate);
    updateWeekInfo();
    loadCourses();
}

// Initialisation
updateWeekInfo();
renderMiniCalendrier();
