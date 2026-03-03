// =====================
//  MODAL AJOUTER/MODIFIER
// =====================

function openAddModal() {
    document.getElementById('modalTitle').textContent = 'Ajouter un professeur';
    document.getElementById('profId').value = '';
    document.getElementById('nomProf').value = '';
    document.getElementById('prenomProf').value = '';
    document.getElementById('intervenantNon').checked = true;

    const idPromo = document.getElementById('formIdPromo').value;
    const form = document.getElementById('profForm');
    form.action = idPromo
        ? `/gestionnaire-edt/professeurs/${idPromo}/new`
        : `/gestionnaire-edt/professeurs/new`;

    document.getElementById('profModal').classList.remove('hidden');
}

function editProfesseur(id, nom, prenom, intervenantExterieur, idPromo) {
    document.getElementById('modalTitle').textContent = 'Modifier un professeur';
    document.getElementById('profId').value = id;
    document.getElementById('nomProf').value = nom;
    document.getElementById('prenomProf').value = prenom;

    if (intervenantExterieur === 'true' || intervenantExterieur === true) {
        document.getElementById('intervenantOui').checked = true;
    } else {
        document.getElementById('intervenantNon').checked = true;
    }

    const form = document.getElementById('profForm');
    form.action = idPromo
        ? `/gestionnaire-edt/professeurs/${idPromo}/${id}/edit`
        : `/gestionnaire-edt/professeurs/${id}/edit`;

    document.getElementById('profModal').classList.remove('hidden');
}

function closeModal() {
    document.getElementById('profModal').classList.add('hidden');
}

// =====================
//  MODAL SUPPRESSION
// =====================

function deleteProfesseur(id, nom, idPromo) {
    document.getElementById('deleteNom').textContent = nom;

    const form = document.getElementById('deleteForm');
    form.action = idPromo
        ? `/gestionnaire-edt/professeurs/${idPromo}/${id}/delete`
        : `/gestionnaire-edt/professeurs/${id}/delete`;

    document.getElementById('deleteModal').classList.remove('hidden');
}

function closeDeleteModal() {
    document.getElementById('deleteModal').classList.add('hidden');
}

// Fermer en cliquant à l'extérieur
window.addEventListener('click', function (e) {
    if (e.target === document.getElementById('profModal')) closeModal();
    if (e.target === document.getElementById('deleteModal')) closeDeleteModal();
});
