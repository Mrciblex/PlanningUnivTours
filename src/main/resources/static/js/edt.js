// EDT
const TypeDate = Object.freeze({
    LOCALDATE: "LD",
    LOCALDATETIME: "LDT"
});

const debutSemestre = numSemestre === 1 ?
    parseDate(promoData.debutS1Promo, TypeDate.LOCALDATE)
    : parseDate(promoData.debutS2Promo, TypeDate.LOCALDATE);
const finSemestre = numSemestre === 1 ?
    parseDate(promoData.finS1Promo, TypeDate.LOCALDATE)
    : parseDate(promoData.finS2Promo, TypeDate.LOCALDATE);
let currentWeekStart = getWeekStart(debutSemestre);

let selectedDate = debutSemestre;

function getWeekStart(date) {
    const d = new Date(date);
    const day = d.getDay();
    const diff = d.getDate() - day + (day === 0 ? -6 : 1); // Ajustement le dimanche
    return new Date(d.setDate(diff));
}

// Outil de parsing pour gérer les LocalDateTime qui arrivent depuis Spring Boot
function parseDate(dateInput, type = TypeDate.LOCALDATETIME) {
    if (!dateInput) return new Date();
    return type === TypeDate.LOCALDATETIME ?
        new Date(dateInput.year, dateInput.monthValue - 1, dateInput.dayOfMonth, dateInput.hour, dateInput.minute, dateInput.second)
        : new Date(dateInput.year, dateInput.monthValue - 1, dateInput.dayOfMonth);
}

function updateWeekInfo() {
    const weekStart = new Date(currentWeekStart);
    const weekEnd = new Date(currentWeekStart);
    weekEnd.setDate(weekEnd.getDate() + 4); // Vendredi

    const options = { day: 'numeric', month: 'long', year: 'numeric' };
    const startStr = weekStart.toLocaleDateString('fr-FR', { day: 'numeric', month: 'long' });
    const endStr = weekEnd.toLocaleDateString('fr-FR', options);

    document.getElementById('weekInfo').textContent = `Semaine du ${startStr} au ${endStr}`;

    document.getElementById('weekNumber').textContent = getWeekNumber().toString();
}

function getWeekNumber() {
    //console.log(promoData);
    const start = getWeekStart(debutSemestre);
    const current = getWeekStart(currentWeekStart);

    // Différence en millisecondes
    const diffInMs = current.getTime() - start.getTime();

    const weekDiff = Math.floor(diffInMs / (1000 * 60 * 60 * 24 * 7));
    return weekDiff + 1;
}

function previousWeek() {
    const targetDate = new Date(currentWeekStart);
    targetDate.setDate(targetDate.getDate() - 7);
    if (targetDate.getTime() < getWeekStart(debutSemestre).getTime()) return;
    currentWeekStart = targetDate;
    updateWeekInfo();
    loadCourses(); // Recharge instantanément sans rafraichir !
}

function nextWeek() {
    const targetDate = new Date(currentWeekStart);
    targetDate.setDate(targetDate.getDate() + 7);
    if (targetDate.getTime() > getWeekStart(finSemestre).getTime()) return;
    currentWeekStart = targetDate;
    updateWeekInfo();
    loadCourses(); // Recharge instantanément sans rafraichir !
}

function loadCourses() {
    // Nettoyer l'affichage actuel
    document.querySelectorAll('.courses-container').forEach(el => el.remove());

    if (typeof coursData === 'undefined' || !Array.isArray(coursData)) return;

    // Définir les limites de la semaine affichée
    const weekStart = new Date(currentWeekStart);
    weekStart.setHours(0, 0, 0, 0);
    const weekEnd = new Date(weekStart);
    weekEnd.setDate(weekEnd.getDate() + 5); // Jusqu'au vendredi soir

    // Filtrer uniquement les cours de la semaine
    const currentWeekCourses = coursData.filter(c => {
        const debut = parseDate(c.heureDebutCours);
        return debut >= weekStart && debut < weekEnd;
    });

    //console.log(currentWeekCourses)

    // Dessiner les cours
    currentWeekCourses.forEach(c => {
        const debut = parseDate(c.heureDebutCours);
        const fin = parseDate(c.heureFinCours);

        const dayOfWeek = debut.getDay(); // 1 = Lundi, 5 = Vendredi
        if (dayOfWeek < 1 || dayOfWeek > 5) return;

        const startHour = debut.getHours();
        const startMin = debut.getMinutes();
        const endHour = fin.getHours();
        const endMin = fin.getMinutes();

        const totalStartMins = startHour * 60 + startMin;
        const totalEndMins = endHour * 60 + endMin;
        const durationMins = totalEndMins - totalStartMins;

        // Trouver le slot de 15 minutes le plus proche pour l'ancrage
        const slotMins = Math.floor(totalStartMins / 15) * 15;
        const slotHour = Math.floor(slotMins / 60);
        const slotMin = slotMins % 60;

        // Formatage pour correspondre à l'attribut data-time (ex: "8:00" ou "8:15")
        const timeString = `${slotHour}:${slotMin === 0 ? '00' : (slotMin < 10 ? '0' + slotMin : slotMin)}`;

        const targetCell = document.querySelector(`td[data-day="${dayOfWeek}"][data-time="${timeString}"]`);

        if (targetCell) {
            // Calculer les dimensions (15px = 15 minutes)
            const offsetMins = totalStartMins - slotMins;
            const heightPx = (durationMins * 15) / 15 - 1;
            const marginTopPx = (offsetMins * 15) / 15;

            // Préparation des classes CSS
            const typeClass = c.typeCoursEnum ? `type-${c.typeCoursEnum.toLowerCase()}` : '';
            const nomComposante = c.composanteDto ? c.composanteDto.nomComposante : 'Cours';

            // Création du conteneur de cours (gère les collisions par défaut en flex row)
            let container = targetCell.querySelector('.courses-container');
            if (!container) {
                container = document.createElement('div');
                container.className = 'courses-container';
                container.style.position = 'absolute';
                container.style.top = '0';
                container.style.left = '0';
                container.style.right = '0';
                container.style.display = 'flex';
                container.style.flexDirection = 'row';
                container.style.gap = '2px';
                container.style.zIndex = '10';
                targetCell.appendChild(container);
            }

            // Création du bloc de cours
            const courseDiv = document.createElement('div');
            courseDiv.className = `course-block ${typeClass}`;
            courseDiv.style.position = 'relative';
            courseDiv.style.flex = '1';
            courseDiv.style.marginTop = `${marginTopPx}px`;
            courseDiv.style.height = `${heightPx}px`;

            courseDiv.onclick = (e) => {
                e.stopPropagation();
                alert('Modifier ce cours (ID: ' + c.idCours + ')');
            };

            const titleDiv = document.createElement('div');
            titleDiv.className = 'course-title';
            titleDiv.textContent = `${nomComposante} - ${c.typeCoursEnum || ''}`;

            const detailsDiv = document.createElement('div');
            detailsDiv.className = 'course-details';
            const formatTime = (d) => d.toLocaleTimeString('fr-FR', {hour: '2-digit', minute:'2-digit'});
            detailsDiv.innerHTML = `<span>${formatTime(debut)} - ${formatTime(fin)}</span>`;

            courseDiv.appendChild(titleDiv);
            courseDiv.appendChild(detailsDiv);

            container.appendChild(courseDiv);
        }
    });
}

// Gestion des boutons d'actions

window.addCourse = function() {
    alert('Ajouter un cours - Modal à implémenter');
};

window.addCourseAt = function(cell) {
    const day = cell.dataset.day;
    const time = cell.dataset.time;
    console.log('Add course at day ' + day + ' at ' + time);
    alert('Ajouter un cours le jour ' + day + ' à ' + time);
};

window.openSettings = function() {
    alert('Paramètres - Pop Up à implémenter');
};

// --- POP-UP ET EXPORTS ---

window.exportEDT = function() {
    document.getElementById('exportPopUp').style.display = 'flex';
};

window.closeExportModal = function() {
    document.getElementById('exportPopUp').style.display = 'none';
};

window.exportToCSV = function() {
    const weekInfo = document.getElementById('weekInfo').textContent;
    const weekNum = document.getElementById('weekNumber').textContent;
    const csv = [];
    csv.push([`Emploi du temps - ${weekInfo}`]);
    csv.push([]);
    const headers = ['Horaire', 'Lundi', 'Mardi', 'Mercredi', 'Jeudi', 'Vendredi'];
    csv.push(headers);

    const rows = document.querySelectorAll('.calendar-table tbody tr');
    rows.forEach(row => {
        const cells = row.querySelectorAll('th, td');
        const rowData = [];
        cells.forEach(cell => {
            // Uniquement garder l'heure, sans les divs de cours pour le tableur
            if(cell.tagName === 'TH') rowData.push(cell.textContent.trim());
            else rowData.push('');
        });
        csv.push(rowData);
    });

    const csvContent = csv.map(row => row.join(',')).join('\n');
    const blob = new Blob([csvContent], { type: 'text/csv;charset=utf-8;' });
    const link = document.createElement('a');
    link.href = URL.createObjectURL(blob);
    link.download = `EDT_Semaine_${weekNum}.csv`;
    link.click();
    closeExportModal();
};

window.exportToExcel = function() {
    const weekInfo = document.getElementById('weekInfo').textContent;
    const weekNum = document.getElementById('weekNumber').textContent;
    let html = '<html><head><meta charset="utf-8"></head><body>';
    html += `<h1>Emploi du temps - ${weekInfo}</h1>`;
    html += '<table border="1">';
    const table = document.querySelector('.calendar-table');
    html += table.innerHTML;
    html += '</table></body></html>';

    const blob = new Blob([html], { type: 'application/vnd.ms-excel' });
    const link = document.createElement('a');
    link.href = URL.createObjectURL(blob);
    link.download = `EDT_Semaine_${weekNum}.xls`;
    link.click();
    closeExportModal();
};

window.exportToPDF = function() {
    closeExportModal();
    const printWindow = window.open('', '', 'height=600,width=800');
    const weekInfo = document.getElementById('weekInfo').textContent;

    printWindow.document.write('<html><head><title>Emploi du temps</title>');
    printWindow.document.write('<style>');
    printWindow.document.write('body { font-family: Arial, sans-serif; margin: 20px; }');
    printWindow.document.write('h1 { text-align: center; color: #333; }');
    printWindow.document.write('table { width: 100%; border-collapse: collapse; margin-top: 20px; }');
    printWindow.document.write('th, td { border: 1px solid #ddd; padding: 8px; text-align: center; position: relative; }');
    printWindow.document.write('th { background-color: #5b7fc7; color: white; }');
    printWindow.document.write('tbody th { background-color: #2c3e50; color: white; }');
    printWindow.document.write('.course-block { background: #5b7fc7; border: 1px solid #fff; font-size: 10px; color: white; }');
    printWindow.document.write('.courses-container { position: absolute; left: 0; right: 0; top: 0; display: flex; }');
    printWindow.document.write('@media print { @page { margin: 1cm; } }');
    printWindow.document.write('</style></head><body>');
    printWindow.document.write(`<h1>Emploi du temps - ${weekInfo}</h1>`);

    const table = document.querySelector('.calendar-table').cloneNode(true);
    printWindow.document.write(table.outerHTML);

    printWindow.document.write('</body></html>');
    printWindow.document.close();

    setTimeout(() => {
        printWindow.print();
        printWindow.close();
    }, 250);
};

// --- MINI CALENDRIER ---

let miniCalendarDate = debutSemestre;

function renderMiniCalendar() {
    const year = miniCalendarDate.getFullYear();
    const month = miniCalendarDate.getMonth();

    const monthNames = ['Janvier', 'Février', 'Mars', 'Avril', 'Mai', 'Juin',
        'Juillet', 'Août', 'Septembre', 'Octobre', 'Novembre', 'Décembre'];
    document.getElementById('calendarMonth').textContent = `${monthNames[month]} ${year}`;

    const firstDay = new Date(year, month, 1);
    const lastDay = new Date(year, month + 1, 0);
    const daysInMonth = lastDay.getDate();
    const startDay = firstDay.getDay() === 0 ? 6 : firstDay.getDay() - 1;

    let html = '';
    const prevMonthDays = new Date(year, month, 0).getDate();

    let dayCount = 0;
    let currentDay = 1;
    let nextMonthDay = 1;

    for (let week = 0; week < 6; week++) {
        html += '<tr>';
        for (let day = 0; day < 7; day++) {
            if (week === 0 && day < startDay) {
                const prevDay = prevMonthDays - startDay + day + 1;
                html += `<td class="other-month">${prevDay}</td>`;
            } else if (currentDay > daysInMonth) {
                html += `<td class="other-month">${nextMonthDay++}</td>`;
            } else {
                const isToday = currentDay === selectedDate.getDate() && month === selectedDate.getMonth() && year === selectedDate.getFullYear();
                const classes = isToday ? 'today mini-calendar-box' : 'mini-calendar-box';
                html += `<td class="${classes}" onclick="selectDate(${year}, ${month}, ${currentDay}, this)">${currentDay}</td>`;
                currentDay++;
            }
            dayCount++;
        }
        html += '</tr>';
        if (currentDay > daysInMonth && nextMonthDay > 7) break;
    }
    document.getElementById('calendarDates').innerHTML = html;
}

function previousMonth() {
    const targetDate = new Date(miniCalendarDate);
    targetDate.setMonth(targetDate.getMonth() - 1);

    const targetMonth = new Date(targetDate.getFullYear(), targetDate.getMonth(), 1);
    const startMonth = new Date(debutSemestre.getFullYear(), debutSemestre.getMonth(), 1);

    if (targetMonth.getTime() < startMonth.getTime()) return;

    miniCalendarDate = targetDate;
    renderMiniCalendar();
}

function nextMonth() {
    const targetDate = new Date(miniCalendarDate);
    targetDate.setMonth(targetDate.getMonth() + 1);

    const targetMonth = new Date(targetDate.getFullYear(), targetDate.getMonth(), 1);
    const startMonth = new Date(finSemestre.getFullYear(), finSemestre.getMonth(), 1);

    if (targetMonth.getTime() > startMonth.getTime()) return;

    miniCalendarDate = targetDate;
    renderMiniCalendar();
}

function selectDate(year, month, day, el) {
    selectedDate = new Date(year, month, day);
    currentWeekStart = getWeekStart(selectedDate);

    document.querySelector(".today").classList.remove("today");
    el.classList.add("today");
    updateWeekInfo();
    loadCourses(); // Met à jour l'EDT sans rechargement de page
}

// Initialisation au chargement
document.addEventListener('DOMContentLoaded', () => {
    updateWeekInfo();
    renderMiniCalendar();
    loadCourses(); // Charge l'emploi du temps de la semaine en cours
});