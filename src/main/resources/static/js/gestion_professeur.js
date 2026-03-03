// baseUrl et idPromo sont injectés par Thymeleaf dans la page HTML

// =====================
//  POP UP AJOUTER
// =====================
function openAddPopUp() {
    document.getElementById('addNom').value    = '';
    document.getElementById('addPrenom').value = '';
    // reset radio à NON
    document.querySelectorAll('#formAdd input[name="intervenantExterieur"]')
        .forEach(r => { r.checked = r.value === 'false'; });

    // L'action est déjà définie par th:action dans le HTML (idPromo injecté par Thymeleaf)
    document.getElementById('profAdd').classList.add('active');
}

// =====================
//  POP UP MODIFIER
// =====================
function openEditPopUp(btn) {
    const id          = btn.dataset.id;
    const nom         = btn.dataset.nom;
    const prenom      = btn.dataset.prenom;
    const intervenant = btn.dataset.intervenant;

    document.getElementById('updateId').value     = id;
    document.getElementById('updateNom').value    = nom;
    document.getElementById('updatePrenom').value = prenom;

    if (intervenant === 'true') {
        document.getElementById('updateIntervenantOui').checked = true;
    } else {
        document.getElementById('updateIntervenantNon').checked = true;
    }

    // POST /gestionnaire-edt/professeurs/{idPromo}/{id}/edit
    document.getElementById('formUpdate').action =
        `${baseUrl}gestionnaire-edt/professeurs/${idPromo}/${id}/edit`;

    document.getElementById('profUpdate').classList.add('active');
}

// =====================
//  POP UP SUPPRIMER
// =====================
function openDeletePopUp(btn) {
    const id  = btn.dataset.id;
    const nom = btn.dataset.nom;

    document.getElementById('deleteNom').textContent = nom;

    // POST /gestionnaire-edt/professeurs/{idPromo}/{id}/delete
    document.getElementById('formDelete').action =
        `${baseUrl}gestionnaire-edt/professeurs/${idPromo}/${id}/delete`;

    document.getElementById('profDelete').classList.add('active');
}

// =====================
//  FERMER TOUS LES POP UP
// =====================
function closePopUp() {
    document.getElementById('profAdd').classList.remove('active');
    document.getElementById('profUpdate').classList.remove('active');
    document.getElementById('profDelete').classList.remove('active');
}

// Fermer en cliquant à l'extérieur
window.addEventListener('click', function (e) {
    ['profAdd', 'profUpdate', 'profDelete'].forEach(id => {
        if (e.target === document.getElementById(id)) closePopUp();
    });
});