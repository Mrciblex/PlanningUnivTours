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

    // AFFICHER DATA A COTE DES TH JOUR
    // RENDRE EXCEL ET CSV BIEN
    // PDF DEJA BIEN
    document.querySelector(".th-day-1").

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
            courseDiv.style.height = `${heightPx + 19}px`;

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

// Récupère toutes les semaines entre le début et la fin du semestre
function getToutesLesSemaines() {
    let current = getWeekStart(debutSemestre);
    const end = getWeekStart(finSemestre);
    const weeks = [];
    while (current.getTime() <= end.getTime()) {
        weeks.push(new Date(current));
        current.setDate(current.getDate() + 7);
    }
    return weeks;
}

// Fonction centrale pour générer la matrice des cours d'une semaine (gère rowspan et colspan)
function getWeekMatrixData(weekStart) {
    const weekEnd = new Date(weekStart);
    weekEnd.setDate(weekEnd.getDate() + 4);

    const weekCourses = coursData.filter(c => {
        const debut = parseDate(c.heureDebutCours);
        const debutStr = new Date(debut.getFullYear(), debut.getMonth(), debut.getDate());
        return debutStr >= weekStart && debutStr <= weekEnd;
    });

    const days = [];
    // Boucle sur Lundi(1) à Vendredi(5)
    for (let d = 1; d <= 5; d++) {
        const dayDate = new Date(weekStart);
        dayDate.setDate(dayDate.getDate() + (d - 1));
        const dayCourses = weekCourses.filter(c => parseDate(c.heureDebutCours).getDay() === d);

        // Trier par heure de début
        dayCourses.sort((a, b) => parseDate(a.heureDebutCours).getTime() - parseDate(b.heureDebutCours).getTime());

        // Répartition en "pistes" (tracks) pour gérer les cours qui se chevauchent (côte à côte)
        const tracks = [];
        dayCourses.forEach(c => {
            const startMins = parseDate(c.heureDebutCours).getHours() * 60 + parseDate(c.heureDebutCours).getMinutes();
            const endMins = parseDate(c.heureFinCours).getHours() * 60 + parseDate(c.heureFinCours).getMinutes();

            let placed = false;
            for (let t = 0; t < tracks.length; t++) {
                // Vérifie si le cours chevauche un autre cours sur cette piste
                const overlap = tracks[t].some(tc => {
                    const tcStartMins = parseDate(tc.heureDebutCours).getHours() * 60 + parseDate(tc.heureDebutCours).getMinutes();
                    const tcEndMins = parseDate(tc.heureFinCours).getHours() * 60 + parseDate(tc.heureFinCours).getMinutes();
                    return startMins < tcEndMins && endMins > tcStartMins;
                });
                if (!overlap) {
                    tracks[t].push(c);
                    placed = true;
                    break;
                }
            }
            if (!placed) tracks.push([c]); // Nouvelle colonne s'il n'y a pas de place
        });

        if (tracks.length === 0) tracks.push([]); // Au moins une piste par jour
        days.push({ date: dayDate, tracks: tracks });
    }

    const START_MINS = 7 * 60;
    const TOTAL_SLOTS = 53; // De 7h00 à 20h00, pas de 15 min

    // Création de la grille vierge
    const cellMap = Array(TOTAL_SLOTS).fill(null).map(() =>
        Array(5).fill(null).map((_, dayIdx) =>
            Array(days[dayIdx].tracks.length).fill(null)
        )
    );

    // Remplissage avec les fusions (rowspan)
    days.forEach((day, dayIdx) => {
        day.tracks.forEach((track, trackIdx) => {
            track.forEach(course => {
                const startMins = parseDate(course.heureDebutCours).getHours() * 60 + parseDate(course.heureDebutCours).getMinutes();
                const endMins = parseDate(course.heureFinCours).getHours() * 60 + parseDate(course.heureFinCours).getMinutes();

                const startSlot = Math.max(0, Math.floor((startMins - START_MINS) / 15));
                const endSlot = Math.min(TOTAL_SLOTS, Math.floor((endMins - START_MINS) / 15));
                const rowspan = endSlot - startSlot;

                if (rowspan > 0 && startSlot < TOTAL_SLOTS) {
                    cellMap[startSlot][dayIdx][trackIdx] = { type: 'start', rowspan, course };
                    for (let s = startSlot + 1; s < endSlot; s++) {
                        if (s < TOTAL_SLOTS) cellMap[s][dayIdx][trackIdx] = { type: 'covered' };
                    }
                }
            });
        });
    });

    const title = `Semaine du ${weekStart.toLocaleDateString('fr-FR')} au ${weekEnd.toLocaleDateString('fr-FR')}`;
    return { title, days, cellMap, TOTAL_SLOTS, START_MINS };
}

// Générateur HTML partagé pour Excel et PDF
function generateHTMLTable(weekData) {
    let html = `<table border="1" style="border-collapse: collapse; table-layout: fixed; width: 100%; text-align: center; font-family: Arial, sans-serif; font-size: 11px;">`;

    // THEAD : En-têtes de colonnes (avec colspan selon le nombre de pistes)
    html += `<thead><tr><th style="width: 60px; background-color: #00A99B; color: white;">Heure</th>`;
    const totalTracks = weekData.days.reduce((sum, day) => sum + day.tracks.length, 0);
    const baseColWidth = 100 / totalTracks; // Pourcentage équitable

    weekData.days.forEach(day => {
        const colspan = day.tracks.length;
        const dateStr = day.date.toLocaleDateString('fr-FR', { weekday: 'long', day: '2-digit', month: '2-digit' });
        html += `<th colspan="${colspan}" style="background-color: #00A99B; color: white; padding: 5px;">${dateStr}</th>`;
    });
    html += `</tr></thead><tbody>`;

    // TBODY : Lignes de 15 minutes
    for (let s = 0; s < weekData.TOTAL_SLOTS; s++) {
        html += `<tr>`;
        const currentMins = weekData.START_MINS + s * 15;
        const hour = Math.floor(currentMins / 60);
        const min = currentMins % 60;
        const timeStr = `${hour}:${min === 0 ? '00' : (min < 10 ? '0' + min : min)}`;

        // Affichage de l'heure
        html += `<th style="background-color: #2c3e50; color: white; width: 60px; height: 18px;">${timeStr}</th>`;

        // Affichage des cellules
        for (let dayIdx = 0; dayIdx < 5; dayIdx++) {
            const tracksCount = weekData.days[dayIdx].tracks.length;
            const width = baseColWidth * tracksCount;

            for (let trackIdx = 0; trackIdx < tracksCount; trackIdx++) {
                const cell = weekData.cellMap[s][dayIdx][trackIdx];

                if (cell === null) {
                    html += `<td style="border: 1px solid #ddd;"></td>`;
                } else if (cell.type === 'start') {
                    const c = cell.course;
                    const nom = c.composanteDto ? c.composanteDto.nomComposante : 'Cours';
                    const type = c.typeCoursEnum || '';

                    let bgColor = '#5b7fc7'; // Default
                    if(type.toLowerCase() === 'cm') bgColor = '#007bff';
                    else if(type.toLowerCase() === 'td') bgColor = '#28a745';
                    else if(type.toLowerCase() === 'tp') bgColor = '#fd7e14';

                    const formatT = (d) => { const x = parseDate(d); return `${x.getHours()}:${x.getMinutes()<10?'0':''}${x.getMinutes()}`; };

                    // Fusion ROWSPAN et application des contraintes de taille + wrapping de texte
                    html += `<td rowspan="${cell.rowspan}" style="background-color: ${bgColor}; color: white; border: 1px solid #fff; border-left: 4px solid rgba(0,0,0,0.3); vertical-align: top; padding: 4px; border-radius: 4px; white-space: normal !important; word-wrap: break-word; overflow-wrap: break-word;">
                        <div style="width: 100%; white-space: normal !important;">
                            <strong>${nom} - ${type}</strong><br>
                            <span style="font-size: 9px;">${formatT(c.heureDebutCours)} - ${formatT(c.heureFinCours)}</span>
                        </div>
                    </td>`;
                }
                // Si 'covered', la cellule est absorbée par le rowspan, on ne dessine rien.
            }
        }
        html += `</tr>`;
    }
    html += `</tbody></table><br><br>`;
    return html;
}

window.exportToExcel = function() {
    const weeks = getToutesLesSemaines();
    let html = `<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40">
    <head>
        <meta charset="utf-8">
        <style> td { mso-number-format: "\@"; } </style>
    </head><body>
    <h1 style="text-align: center; font-family: Arial;">Emploi du temps - Promo S${numSemestre}</h1>`;

    weeks.forEach(weekStart => {
        const weekData = getWeekMatrixData(weekStart);
        html += `<h2 style="font-family: Arial;">${weekData.title}</h2>`;
        html += generateHTMLTable(weekData);
    });

    html += '</body></html>';

    const blob = new Blob([html], { type: 'application/vnd.ms-excel' });
    const link = document.createElement('a');
    link.href = URL.createObjectURL(blob);
    link.download = `EDT_Semestre_${numSemestre}.xls`;
    link.click();
    closeExportModal();
};

window.exportToPDF = function() {
    closeExportModal();
    const weeks = getToutesLesSemaines();
    const printWindow = window.open('', '', 'height=800,width=1200');

    printWindow.document.write('<html><head><title>Emploi du temps Semestre</title>');
    printWindow.document.write('<style>');
    printWindow.document.write('body { font-family: Arial, sans-serif; margin: 10px; }');
    printWindow.document.write('h1 { text-align: center; color: #333; }');
    printWindow.document.write('h2 { color: #5b7fc7; margin-top: 20px; page-break-before: always; }');
    printWindow.document.write('h2:first-of-type { page-break-before: auto; }');
    printWindow.document.write('@media print { @page { margin: 1cm; size: landscape; } }');
    printWindow.document.write('</style></head><body>');
    printWindow.document.write(`<h1>Emploi du temps - S${numSemestre}</h1>`);

    weeks.forEach(weekStart => {
        const weekData = getWeekMatrixData(weekStart);
        printWindow.document.write(`<h2>${weekData.title}</h2>`);
        printWindow.document.write(generateHTMLTable(weekData));
    });

    printWindow.document.write('</body></html>');
    printWindow.document.close();

    setTimeout(() => {
        printWindow.print();
        printWindow.close();
    }, 500);
};

window.exportToCSV = function() {
    const weeks = getToutesLesSemaines();
    let csvContent = '\uFEFF';
    csvContent += `Emploi du temps - Promo S${numSemestre}\n\n`;

    weeks.forEach(weekStart => {
        const weekData = getWeekMatrixData(weekStart);
        csvContent += `"${weekData.title}"\n`;

        const headers = ['Heure'];
        weekData.days.forEach(day => {
            const dateStr = day.date.toLocaleDateString('fr-FR', { weekday: 'long', day: '2-digit', month: '2-digit' });
            for(let i = 0; i < day.tracks.length; i++) headers.push(dateStr + (day.tracks.length > 1 ? ` (Piste ${i+1})` : ''));
        });
        csvContent += headers.map(h => `"${h}"`).join(';') + '\n';

        for (let s = 0; s < weekData.TOTAL_SLOTS; s++) {
            const currentMins = weekData.START_MINS + s * 15;
            const hour = Math.floor(currentMins / 60);
            const min = currentMins % 60;
            const rowText = [`${hour}:${min === 0 ? '00' : (min < 10 ? '0' + min : min)}`];

            for (let dayIdx = 0; dayIdx < 5; dayIdx++) {
                for (let trackIdx = 0; trackIdx < weekData.days[dayIdx].tracks.length; trackIdx++) {
                    const cell = weekData.cellMap[s][dayIdx][trackIdx];
                    if (cell && cell.type === 'start') {
                        const c = cell.course;
                        const nom = c.composanteDto ? c.composanteDto.nomComposante : 'Cours';
                        rowText.push(`${nom} - ${c.typeCoursEnum || ''}`);
                    } else {
                        rowText.push(''); // Laisse vide pour les cellules couvertes (CSV ne gère pas le rowspan)
                    }
                }
            }
            csvContent += rowText.map(t => `"${t}"`).join(';') + '\n';
        }
        csvContent += '\n\n';
    });

    const blob = new Blob([csvContent], { type: 'text/csv;charset=utf-8;' });
    const link = document.createElement('a');
    link.href = URL.createObjectURL(blob);
    link.download = `EDT_Semestre_${numSemestre}.csv`;
    link.click();
    closeExportModal();
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