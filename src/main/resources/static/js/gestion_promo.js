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
    const nbetu = btn.dataset.nbetu;
    const debutS1 = btn.dataset.debutS1;
    const finS1 = btn.dataset.finS1;
    const debutS2 = btn.dataset.debutS2;
    const finS2 = btn.dataset.finS2;

    document.getElementById("promoIdU").value = id;
    document.getElementById("nomU").value = nom;
    document.getElementById("anneeU").value = annee;
    document.getElementById("nbetuU").value = nbetu;
    document.getElementById("dateDebutS1U").value = debutS1;
    document.getElementById("dateFinS1U").value = finS1;
    document.getElementById("dateDebutS2U").value = debutS2;
    document.getElementById("dateFinS2U").value = finS2;

    document.getElementById("promoUpdate").classList.add('active');
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

    document.getElementById("promoDelete").classList.add('active');
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

// Fermer les pop up
function closePopUpAdd() {
    document.getElementById('promoAdd').classList.remove('active');
}

function closePopUpUpdate() {
    document.getElementById('promoUpdate').classList.remove('active');
}

function closePopUpDelete() {
    document.getElementById('promoDelete').classList.remove('active');
}
