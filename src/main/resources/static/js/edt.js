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

// Extracteur pour le front et les exports
function extractCoursInfo(c) {
    const nom = c.composanteDto ? c.composanteDto.nomComposante : 'Cours';
    const type = c.typeCoursEnum || '';
    const prof = c.professeurDto ? `${c.professeurDto.nomProf || ''} ${c.professeurDto.prenomProf || ''}`.trim() : '';
    let salle = c.salleDto && c.salleDto.idSalle ? `Salle de ${c.salleDto.nbPlace} places `: '';
    salle += c.salleDto && c.salleDto.salleMachine ? `${c.salleDto.salleMachine ? '| Machine ' : ''}`: '';
    salle += c.salleDto && c.salleDto.nbPC ? `| ${c.salleDto.nbPC} PC`: '';
    const groupes = (c.participeADto && Array.isArray(c.participeADto))
        ? c.participeADto.map(g => g.nomSousGroupe).join(', ')
        : '';
    return { nom, type, prof, salle, groupes };
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
    loadCourses(); // Recharge instantanément sans rafraichir
}

function nextWeek() {
    const targetDate = new Date(currentWeekStart);
    targetDate.setDate(targetDate.getDate() + 7);
    if (targetDate.getTime() > getWeekStart(finSemestre).getTime()) return;
    currentWeekStart = targetDate;
    updateWeekInfo();
    loadCourses(); // Recharge instantanément sans rafraichir
}

function loadCourses() {
    // Nettoyer l'affichage actuel
    document.querySelectorAll('.courses-container').forEach(el => el.remove());

    if (typeof coursData === 'undefined' || !Array.isArray(coursData)) return;

    // Définir les limites de la semaine affichée
    const weekStart = new Date(currentWeekStart);
    weekStart.setHours(0, 0, 0, 0);

    // AFFICHER DATE A COTE DES TH JOUR
    // RENDRE EXCEL ET CSV BIEN
    // PDF DEJA BIEN
    document.querySelector(".th-day-1").innerHTML = "Lundi " + weekStart.getDate();
    document.querySelector(".th-day-2").innerHTML = "Mardi " + (weekStart.getDate() + 1);
    document.querySelector(".th-day-3").innerHTML = "Mercredi " + (weekStart.getDate() + 2);
    document.querySelector(".th-day-4").innerHTML = "Jeudi " + (weekStart.getDate() + 3);
    document.querySelector(".th-day-5").innerHTML = "Vendredi " + (weekStart.getDate() + 4);

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

        // Formatage pour correspondre à l'attribut data-time ("8:00" ou "8:15")
        const timeString = `${slotHour}:${slotMin === 0 ? '00' : (slotMin < 10 ? '0' + slotMin : slotMin)}`;

        const targetCell = document.querySelector(`td[data-day="${dayOfWeek}"][data-time="${timeString}"]`);

        if (targetCell) {
            // Calculer les dimensions (15px = 15 minutes)
            const offsetMins = totalStartMins - slotMins;
            const heightPx = (durationMins * 15) / 15 - 1;
            const marginTopPx = (offsetMins * 15) / 15;

            const info = extractCoursInfo(c);
            const typeClass = info.type ? `type-${info.type.toLowerCase()}` : '';

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
            titleDiv.textContent = `${info.nom} - ${info.type}`;

            const detailsDiv = document.createElement('div');
            detailsDiv.className = 'course-details';
            const formatTime = (d) => d.toLocaleTimeString('fr-FR', {hour: '2-digit', minute:'2-digit'});
            let detailsHtml = `<span>${formatTime(debut)} - ${formatTime(fin)}</span>`;
            detailsHtml += `<br><span style="font-size: 11px; opacity: 0.9; font-weight:bold;">${info.prof}</span>`;
            detailsHtml += `<br><span style="font-size: 11px; opacity: 0.85;">${info.groupes}</span>`;
            if (info.salle) detailsHtml += `<br><span style="font-size: 11px;">${info.salle}</span>`;
            detailsDiv.innerHTML = detailsHtml;

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

// --- GÉNÉRATEUR ---
window.openSettings = function() {
    document.getElementById('generatorPopUp').style.display = 'flex';
};

window.closeGeneratorModal = function() {
    document.getElementById('generatorPopUp').style.display = 'none';
};

let responsePopUp = document.querySelector(".generator-response");

function runGenerator(target) {
    if(!confirm("Attention : Cela va supprimer tous les cours actuels de ce semestre pour cette promo. Continuer ?")) return;

    const requestData = {
        weights: {
            repetition: parseFloat(document.getElementById('w_repetition').value),
            tot: parseFloat(document.getElementById('w_tot').value),
            matin: parseFloat(document.getElementById('w_matin').value),
            tard: parseFloat(document.getElementById('w_tard').value),
            trous: parseFloat(document.getElementById('w_trous').value),
            regu: parseFloat(document.getElementById('w_regu').value)
        },
        // On envoie la liste des IDs pour que le serveur sache quoi supprimer précisément
        existingCourseIds: coursData.map(c => c.idCours).filter(id => id != null)
    };

    // On affiche un loader si tu en as un, sinon le bouton change
    const btn = target;
    const originalText = btn.innerText;
    btn.innerText = "Génération en cours...";
    btn.disabled = true;
    btn.style.cursor = "not-allowed";
    let cancelBtns = document.querySelectorAll(".generator-cancel-btn");
    const weightInputs = document.querySelectorAll(".generator-weight-input");

    weightInputs.forEach(input => {
        input.disabled = true;
        input.style.cursor = "not-allowed";
        input.style.opacity = "0.4";
    });

    cancelBtns.forEach(cancelBtn => {
        cancelBtn.disabled = true;
        cancelBtn.style.cursor = "not-allowed";
        cancelBtn.style.opacity = "0.4";
    });

    fetch(`${baseUrl}gestionnaire-edt/edt/generate/${promoData.idPromo}/${numSemestre}`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(requestData)
    })
        .then(response => response.json())
        .then(data => {
            // Success
            btn.disabled = false;
            btn.style.cursor = "pointer";
            btn.innerText = "Terminer";
            btn.onclick = endGeneration;
            cancelBtns.forEach(cancelBtn => cancelBtn.style.display = "none");
            document.querySelector(".generator-form").style.display = "none";

            // Gestion des cours non placés
            let totalUnimplemented = 0;
            if (Object.keys(data.unImplementedCours).length > 0) {
                let msg = `Génération terminée en ${data.executionTime}.<br> Certains cours n'ont pas pu être placés :<br>`;
                for (const week in data.unImplementedCours) {
                    msg += `<br>- Semaine ${week}: ${data.unImplementedCours[week].length} cours restant(s)`;
                    totalUnimplemented += data.unImplementedCours[week].length;
                }
                const responsePopUpText = document.createElement("p");
                msg += `<br><br>TOTAL : ${totalUnimplemented} cours restant(s) | Voir <button style="cursor: pointer;" onclick="getRecapitulatif()"><b><u>récapitulatif</u></b></button> pour plus de détails`;
                responsePopUpText.innerHTML = `${msg}`;
                responsePopUp.appendChild(responsePopUpText);
                //alert(msg);
            } else {
                //alert(`Succès ! Tous les cours ont été placés (${data.executionTime}).`);
                const responsePopUpText = document.createElement("p");
                responsePopUpText.textContent = `Tous les cours ont été placés (${data.executionTime}).`
                responsePopUp.appendChild(responsePopUpText);
            }

            responsePopUp.style.display = "grid";

        })
        .catch(error => {
            console.log(error);
            alert("Erreur lors de la génération.");
            btn.innerText = originalText;
            btn.disabled = false;
        });
}

function endGeneration(){
    location.reload();
}

function getRecapitulatif(){
    location.reload();
}

// --- POP-UP ET EXPORTS ---
window.exportEDT = function() { document.getElementById('exportPopUp').style.display = 'flex'; };
window.closeExportModal = function() { document.getElementById('exportPopUp').style.display = 'none'; };

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

function getWeekMatrixData(weekStart) {
    const weekEnd = new Date(weekStart);
    weekEnd.setDate(weekEnd.getDate() + 4);

    const weekCourses = coursData.filter(c => {
        const debut = parseDate(c.heureDebutCours);
        const debutStr = new Date(debut.getFullYear(), debut.getMonth(), debut.getDate());
        return debutStr >= weekStart && debutStr <= weekEnd;
    });

    const days = [];
    for (let d = 1; d <= 5; d++) {
        const dayDate = new Date(weekStart);
        dayDate.setDate(dayDate.getDate() + (d - 1));
        const dayCourses = weekCourses.filter(c => parseDate(c.heureDebutCours).getDay() === d);

        dayCourses.sort((a, b) => parseDate(a.heureDebutCours).getTime() - parseDate(b.heureDebutCours).getTime());

        const tracks = [];
        dayCourses.forEach(c => {
            const startMins = parseDate(c.heureDebutCours).getHours() * 60 + parseDate(c.heureDebutCours).getMinutes();
            const endMins = parseDate(c.heureFinCours).getHours() * 60 + parseDate(c.heureFinCours).getMinutes();

            let placed = false;
            for (let t = 0; t < tracks.length; t++) {
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
            if (!placed) tracks.push([c]);
        });

        if (tracks.length === 0) tracks.push([]);
        days.push({ date: dayDate, tracks: tracks });
    }

    const START_MINS = 7 * 60;
    const TOTAL_SLOTS = 53;

    const cellMap = Array(TOTAL_SLOTS).fill(null).map(() =>
        Array(5).fill(null).map((_, dayIdx) =>
            Array(days[dayIdx].tracks.length).fill(null)
        )
    );

    // 1. Placement des cours et Rowspan
    days.forEach((day, dayIdx) => {
        day.tracks.forEach((track, trackIdx) => {
            track.forEach(course => {
                const startMins = parseDate(course.heureDebutCours).getHours() * 60 + parseDate(course.heureDebutCours).getMinutes();
                const endMins = parseDate(course.heureFinCours).getHours() * 60 + parseDate(course.heureFinCours).getMinutes();

                const startSlot = Math.max(0, Math.floor((startMins - START_MINS) / 15));
                const endSlot = Math.min(TOTAL_SLOTS, Math.floor((endMins - START_MINS) / 15));
                const rowspan = endSlot - startSlot;

                if (rowspan > 0 && startSlot < TOTAL_SLOTS) {
                    cellMap[startSlot][dayIdx][trackIdx] = { type: 'start', rowspan, colspan: 1, course };
                    for (let s = startSlot + 1; s < endSlot; s++) {
                        if (s < TOTAL_SLOTS) cellMap[s][dayIdx][trackIdx] = { type: 'covered' };
                    }
                }
            });
        });
    });

    // 2. Calcul du Colspan dynamique
    for (let dayIdx = 0; dayIdx < 5; dayIdx++) {
        const tracksCount = days[dayIdx].tracks.length;
        for (let s = 0; s < TOTAL_SLOTS; s++) {
            for (let t = 0; t < tracksCount; t++) {
                const cell = cellMap[s][dayIdx][t];
                if (cell && cell.type === 'start') {
                    let colspan = 1;
                    for (let nextT = t + 1; nextT < tracksCount; nextT++) {
                        let canExpand = true;
                        for (let r = s; r < s + cell.rowspan; r++) {
                            if (r < TOTAL_SLOTS && cellMap[r][dayIdx][nextT] !== null) {
                                canExpand = false;
                                break;
                            }
                        }
                        if (canExpand) {
                            colspan++;
                            for (let r = s; r < s + cell.rowspan; r++) {
                                if (r < TOTAL_SLOTS) cellMap[r][dayIdx][nextT] = { type: 'covered_col', isStartRow: (r === s), course: cell.course };
                            }
                        } else {
                            break;
                        }
                    }
                    cell.colspan = colspan;
                }
            }
        }
    }

    const title = `Semaine du ${weekStart.toLocaleDateString('fr-FR')} au ${weekEnd.toLocaleDateString('fr-FR')}`;
    return { title, days, cellMap, TOTAL_SLOTS, START_MINS };
}

function generateHTMLTable(weekData) {
    let html = `<table border="1" style="border-collapse: collapse; table-layout: fixed; width: 100%; font-family: Arial, sans-serif; font-size: 11px; margin-bottom: 30px;">`;

    // ASTUCE COLGROUP
    html += `<colgroup><col style="width: 14%;">`;
    weekData.days.forEach(day => {
        const trackWidth = 86 / day.tracks.length; // Répartition de l'espace restant
        for(let i=0; i<day.tracks.length; i++) {
            html += `<col style="width: ${trackWidth}%;">`;
        }
    });
    html += `</colgroup>`;

    html += `<thead><tr><th style="background-color: #2F3E47; color: white; text-align: center; vertical-align: middle; height: 30px;">Heure</th>`;

    weekData.days.forEach(day => {
        const colspan = day.tracks.length;
        const dateStr = day.date.toLocaleDateString('fr-FR', { weekday: 'long', day: '2-digit', month: '2-digit' });
        html += `<th colspan="${colspan}" style="background-color: #00A99B; color: white; border: 1px solid #1F635E; text-align: center; vertical-align: middle;">${dateStr}</th>`;
    });
    html += `</tr></thead><tbody>`;

    for (let s = 0; s < weekData.TOTAL_SLOTS; s++) {
        html += `<tr>`;
        const currentMins = weekData.START_MINS + s * 15;
        const hour = Math.floor(currentMins / 60);
        const min = currentMins % 60;
        const timeStr = `${hour}:${min === 0 ? '00' : (min < 10 ? '0' + min : min)}`;

        // Hauteur de ligne fixée pour garantir le quadrillage
        html += `<th style="background-color: #2c3e50; color: white; border: 1px solid #1a252f; height: 25px; text-align: center; vertical-align: middle;">${timeStr}</th>`;

        const rowBgColor = s % 2 === 0 ? '#f9fafb' : '#ffffff';

        for (let dayIdx = 0; dayIdx < 5; dayIdx++) {
            const tracksCount = weekData.days[dayIdx].tracks.length;
            for (let trackIdx = 0; trackIdx < tracksCount; trackIdx++) {
                const cell = weekData.cellMap[s][dayIdx][trackIdx];

                if (cell === null) {
                    html += `<td style="border: 1px solid #e5e7eb; background-color: ${rowBgColor};"></td>`;
                } else if (cell.type === 'start') {
                    const c = cell.course;
                    const info = extractCoursInfo(c);

                    let bgColor = '#5b7fc7';
                    if(info.type.toLowerCase() === 'cm') bgColor = '#007bff';
                    else if(info.type.toLowerCase() === 'td') bgColor = '#28a745';
                    else if(info.type.toLowerCase() === 'tp') bgColor = '#fd7e14';

                    const formatT = (d) => {
                        const x = parseDate(d);
                        return `${x.getHours()}:${x.getMinutes()<10?'0':''}${x.getMinutes()}`;
                    };

                    /*
                    html += `<td rowspan="${cell.rowspan}" colspan="${cell.colspan}" style="background-color: ${bgColor}; color: white; border: 1px solid #ffffff; border-left: 4px solid #1a252f; vertical-align: middle; text-align: center; padding: 4px; border-radius: 4px; box-shadow: 0 1px 3px rgba(0,0,0,0.1); white-space: normal !important; word-wrap: break-word; mso-style-text-wrap: yes;">
                        ${contentHtml}
                    </td>`;
                     */
                    html += `<td rowspan="${cell.rowspan}" colspan="${cell.colspan}" style="background-color: ${bgColor}; color: white; border: 1px solid #ffffff; border-left: 4px solid #1a252f; vertical-align: middle; text-align: center; padding: 4px; border-radius: 4px; box-shadow: 0 1px 3px rgba(0,0,0,0.1); white-space: normal !important; word-wrap: break-word; mso-style-text-wrap: yes;">
                        <div style="width: 100%; height: 100%; display: flex; flex-direction: column; justify-content: center; align-items: center; overflow-wrap: break-word; line-height: 1.2;">
                            <strong style="font-size: 13px; margin-bottom: 2px; display: block;">${info.nom} - ${info.type}</strong>
                            <span style="font-size:11px; opacity: 0.9; display: block;">${formatT(c.heureDebutCours)} - ${formatT(c.heureFinCours)}</span>
                            <span style="font-size: 11px; opacity: 0.9; font-weight:bold; display: block;">${info.prof}</span>
                            <span style="font-size: 11px; opacity: 0.9; display: block;">${info.groupes}</span>
                            <span style="font-size: 11px; opacity: 0.9; display: block;">${info.salle}</span>
                        </div>
                    </td>`;
                }
            }
        }
        html += `</tr>`;
    }
    html += `</tbody></table>`;
    return html;
}

window.exportToExcel = function() {
    const weeks = getToutesLesSemaines();
    let html = `<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40">
    <head>
        <meta http-equiv="content-type" content="application/vnd.ms-excel; charset=UTF-8">
        <style> 
            /* Forces Excel to wrap text in merged cells */
            td { mso-style-text-wrap: yes; vertical-align: middle; text-align: center; } 
            br { mso-data-placement: same-cell; }
        </style>
    </head><body style="font-family: Arial, sans-serif;">
    <h1 style="text-align: center;">Emploi du temps - ${promoData.nomPromo} S${numSemestre} ${promoData.anneePromo}</h1>`;

    weeks.forEach(weekStart => {
        const weekData = getWeekMatrixData(weekStart);
        html += `<h2 style="text-align: center; color: #333;">${weekData.title}</h2>`;
        html += generateHTMLTable(weekData);
        html += `<br><br>`; // Espace entre les semaines
    });

    html += '</body></html>';

    // Encodage utf-8
    const blob = new Blob(['\uFEFF' + html], { type: 'application/vnd.ms-excel;charset=utf-8;' });
    const link = document.createElement('a');
    link.href = URL.createObjectURL(blob);
    link.download = `EDT_Semestre_${numSemestre}.xls`;
    link.click();
    closeExportModal();
};

window.exportToPDF = function() {
    closeExportModal();
    const weeks = getToutesLesSemaines();
    const printWindow = window.open('', '', 'height=1080,width=1920');

    printWindow.document.write('<html><head><title>Emploi du temps Semestre</title>');
    printWindow.document.write('<style>');
    printWindow.document.write('body { font-family: "Segoe UI", Arial, sans-serif; margin: 20px; color: #333; }');
    printWindow.document.write('h1 { text-align: center; color: #1f2937; margin-bottom: 30px; }');
    printWindow.document.write('h2 { color: #00A99B; margin-top: 30px; margin-bottom: 10px; font-size: 18px; page-break-before: always; }');
    printWindow.document.write('h2:first-of-type { page-break-before: auto; }');
    printWindow.document.write('@media print { @page { margin: 10mm; size: landscape; } body { -webkit-print-color-adjust: exact; print-color-adjust: exact; } }');
    printWindow.document.write('</style></head><body>');
    printWindow.document.write(`<h1>Emploi du temps - ${promoData.nomPromo} S${numSemestre} ${promoData.anneePromo}</h1>`);

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
    let csvContent = '\uFEFF'; // Pour l'encodage UTF-8 sous Excel

    // Entêtes BDD
    const headers = ['ID Cours', 'Date', 'Heure Début', 'Heure Fin', 'Type', 'Composante', 'Professeur', 'Salle', 'Groupes'];
    csvContent += headers.map(h => `"${h}"`).join(';') + '\n';

    if (typeof coursData !== 'undefined' && Array.isArray(coursData)) {
        const sortedCourses = [...coursData].sort((a,b) => parseDate(a.heureDebutCours).getTime() - parseDate(b.heureDebutCours).getTime());

        sortedCourses.forEach(c => {
            const debut = parseDate(c.heureDebutCours);
            const fin = parseDate(c.heureFinCours);

            const dateStr = debut.toLocaleDateString('fr-FR');
            const hdStr = debut.toLocaleTimeString('fr-FR', {hour: '2-digit', minute:'2-digit'});
            const hfStr = fin.toLocaleTimeString('fr-FR', {hour: '2-digit', minute:'2-digit'});

            const info = extractCoursInfo(c);

            // Formatage
            const escapeCSV = (str) => `"${String(str).replace(/"/g, '""')}"`;

            const row = [
                c.idCours || '',
                dateStr,
                hdStr,
                hfStr,
                info.type,
                info.nom,
                info.prof,
                info.salle,
                info.groupes
            ].map(escapeCSV);

            csvContent += row.join(';') + '\n';
        });
    }

    const blob = new Blob([csvContent], { type: 'text/csv;charset=utf-8;' });
    const link = document.createElement('a');
    link.href = URL.createObjectURL(blob);
    link.download = `Data_Cours_Semestre_${numSemestre}.csv`;
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