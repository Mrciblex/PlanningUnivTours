const cleanBaseUrl = baseUrl.endsWith('/') ? baseUrl : baseUrl + '/';

function closeModal(id) {
    document.getElementById(id).classList.remove('active');
}

function openGroupeModal(mode, btn = null) {
    const form = document.getElementById('groupeForm');
    const title = document.getElementById('groupeModalTitle');
    const methodInput = document.getElementById('groupeMethod');

    if (mode === 'add') {
        title.textContent = 'Ajouter un groupe';
        form.action = cleanBaseUrl + 'gestionnaire-edt/groupes/new';
        methodInput.value = 'POST';
        document.getElementById('groupeIdInput').value = '';
        document.getElementById('nomGroupeInput').value = '';
        document.getElementById('nbEtuGroupeInput').value = '';
    } else {
        title.textContent = 'Modifier un groupe';
        form.action = cleanBaseUrl + 'gestionnaire-edt/groupes/edit';
        methodInput.value = 'PUT';
        document.getElementById('groupeIdInput').value = btn.dataset.id;
        document.getElementById('nomGroupeInput').value = btn.dataset.nom;
        document.getElementById('nbEtuGroupeInput').value = btn.dataset.nbetu;
    }
    document.getElementById('groupeModal').classList.add('active');
}

function openGroupeDeleteModal(btn) {
    document.getElementById('groupeIdD').textContent = btn.dataset.id;
    document.getElementById('groupeNomD').textContent = btn.dataset.nom;
    document.getElementById('idGroupeDeleteInput').value = btn.dataset.id;
    document.getElementById('groupeDelete').classList.add('active');
}

function openSgModal(mode, btn = null) {
    const form = document.getElementById('sgForm');
    const title = document.getElementById('sgModalTitle');
    const methodInput = document.getElementById('sgMethod');

    if (mode === 'add') {
        title.textContent = 'Ajouter un sous-groupe';
        form.action = cleanBaseUrl + 'gestionnaire-edt/groupes/sg/new';
        methodInput.value = 'POST';
        document.getElementById('sgIdInput').value = '';
        document.getElementById('nomSgInput').value = '';
        document.getElementById('nbEtuSgInput').value = '';
        if(document.getElementById('idGroupeSelect').options.length > 0) {
            document.getElementById('idGroupeSelect').selectedIndex = 0;
        }
    } else {
        title.textContent = 'Modifier un sous-groupe';
        form.action = cleanBaseUrl + 'gestionnaire-edt/groupes/sg/edit';
        methodInput.value = 'PUT';
        document.getElementById('sgIdInput').value = btn.dataset.id;
        document.getElementById('nomSgInput').value = btn.dataset.nom;
        document.getElementById('nbEtuSgInput').value = btn.dataset.nbetu;
        document.getElementById('idGroupeSelect').value = btn.dataset.idgroupe;
    }
    document.getElementById('sgModal').classList.add('active');
}

function openSgDeleteModal(btn) {
    document.getElementById('sgNomD').textContent = btn.dataset.nom;
    document.getElementById('sgNbEtuD').textContent = btn.dataset.nbetu;
    document.getElementById('idSgDeleteInput').value = btn.dataset.id;
    document.getElementById('sgDelete').classList.add('active');
}