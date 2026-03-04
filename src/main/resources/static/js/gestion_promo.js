// --- UTILITAIRES ---
function closePopUpAdd() { document.getElementById('promoAdd').classList.remove('active'); }
function closePopUpUpdate() { document.getElementById('promoUpdate').classList.remove('active'); }
function closePopUpDelete() { document.getElementById('promoDelete').classList.remove('active'); }

function openPromoPopUp() {
    document.getElementById('formAdd').reset();
    document.getElementById('promoAdd').classList.add('active');
}

// --- AJOUT ---
document.getElementById('formAdd').addEventListener('submit', function(e) {
    e.preventDefault();
    const data = {
        nomPromo: document.getElementById('nomA').value,
        anneePromo: parseInt(document.getElementById('anneeA').value),
        nbEtuPromo: parseInt(document.getElementById('nbetuA').value),
        debutS1Promo: document.getElementById('dateDebutS1A').value || null,
        finS1Promo: document.getElementById('dateFinS1A').value || null,
        debutS2Promo: document.getElementById('dateDebutS2A').value || null,
        finS2Promo: document.getElementById('dateFinS2A').value || null
    };

    fetch(`${baseUrl}gestion-promos/new`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(data)
    }).then(res => res.ok ? window.location.reload() : alert("Erreur lors de l'ajout"));
});

// --- MODIFICATION ---
function editPromoPopUp(btn) {
    document.getElementById("promoIdU").value = btn.dataset.id;
    document.getElementById("nomU").value = btn.dataset.nom;
    document.getElementById("anneeU").value = btn.dataset.annee;
    document.getElementById("nbetuU").value = btn.dataset.nbetu;
    document.getElementById("dateDebutS1U").value = btn.dataset.debutS1 || '';
    document.getElementById("dateFinS1U").value = btn.dataset.finS1 || '';
    document.getElementById("dateDebutS2U").value = btn.dataset.debutS2 || '';
    document.getElementById("dateFinS2U").value = btn.dataset.finS2 || '';
    document.getElementById("promoUpdate").classList.add('active');
}

document.getElementById('formUpdate').addEventListener('submit', function(e) {
    e.preventDefault();
    const id = document.getElementById('promoIdU').value;
    const data = {
        idPromo: parseInt(id),
        nomPromo: document.getElementById('nomU').value,
        anneePromo: parseInt(document.getElementById('anneeU').value),
        nbEtuPromo: parseInt(document.getElementById('nbetuU').value),
        debutS1Promo: document.getElementById('dateDebutS1U').value || null,
        finS1Promo: document.getElementById('dateFinS1U').value || null,
        debutS2Promo: document.getElementById('dateDebutS2U').value || null,
        finS2Promo: document.getElementById('dateFinS2U').value || null
    };

    fetch(`${baseUrl}gestion-promos/${id}/edit`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(data)
    }).then(res => res.ok ? window.location.reload() : alert("Erreur lors de la modification"));
});

// --- SUPPRESSION ---
function deletePromoPopUp(btn) {
    document.getElementById("idPromo").textContent = btn.dataset.id;
    document.getElementById("nomD").textContent = btn.dataset.nom;
    document.getElementById('anneeD').textContent = btn.dataset.annee;
    document.getElementById('nbetuD').textContent = btn.dataset.nbetu;
    document.getElementById('dateDebutS1D').textContent = (btn.dataset.dateDebutS1 + ' / ' + btn.dataset.dateFinS1) || '-';
    document.getElementById('dateDebutS2D').textContent = (btn.dataset.dateDebutS2 + ' / ' + btn.dataset.dateFinS2) || '-';
    document.getElementById("promoDelete").classList.add('active');
}

function deletePromo() {
    const id = document.getElementById("idPromo").textContent;
    fetch(`${baseUrl}gestion-promos/${id}/delete`, { method: 'POST' })
        .then(res => res.ok ? window.location.reload() : alert("Erreur suppression"));
}