// Index

// Make functions global
window.toggleDropdown = function() {
    console.log('Toggle dropdown called');
    const dropdown = document.getElementById('selection_promo_recente');
    console.log('Dropdown element:', dropdown);
    console.log('Current classes:', dropdown.className);
    dropdown.classList.toggle('active');
    console.log('New classes:', dropdown.className);
};

window.createNewEDT = function() {
    window.location.href = '/edt/create';
};

window.accessPromo = function(promoId) {
    window.location.href = '/edt/promo/' + promoId;
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

/*
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
*/
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
