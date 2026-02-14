
// Index
function ancienMenu() {
    const dropdown = document.getElementById('ancienMenu');
    dropdown.classList.toggle('active');
}

// Gestionnaire de promo
document.getElementById('promoModal')
    .addEventListener(
        'click',
        function(e) {
            if (e.target === this) {
                closeModal();
            }
        }
    );

function openAddModal() {
    document.getElementById('modalTitle').textContent = 'Ajouter une promotion';
    document.getElementById('promoId').value = '';
    document.getElementById('nom').value = '';
    document.getElementById('annee').value = '';
    document.getElementById('dateDebutS1').value = '';
    document.getElementById('dateFinS1').value = '';
    document.getElementById('dateDebutS2').value = '';
    document.getElementById('dateFinS2').value = '';
    document.getElementById('promoModal').classList.add('active');
    console.log('Modal classes:', this.className);
}

function editPromo(id, nom, annee, dateDebutS1, dateFinS1, dateDebutS2, dateFinS2) {
    document.getElementById('modalTitle').textContent = 'Modifier la promotion';
    document.getElementById('promoId').value = id;
    document.getElementById('nom').value = nom;
    document.getElementById('annee').value = annee;
    document.getElementById('dateDebutS1').value = dateDebutS1 || '';
    document.getElementById('dateFinS1').value = dateFinS1 || '';
    document.getElementById('dateDebutS2').value = dateDebutS2 || '';
    document.getElementById('dateFinS2').value = dateFinS2 || '';
    document.getElementById('promoModal').classList.add('active');
}

function closeModal() {
    document.getElementById('promoModal').classList.remove('active');
    console.log('Modal close:', this.className);
}
