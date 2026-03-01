let debutSemaine = Date.now();
//const cours= {};

function previousWeek() {
    console.log("previous")
    /*
    currentWeekStart.setDate(currentWeekStart.getDate() - 7);
    updateWeekInfo();
    loadCourses();
     */
}

function nextWeek() {
    console.log("next")
    /*
    currentWeekStart.setDate(currentWeekStart.getDate() + 7);
    updateWeekInfo();
    loadCourses();
     */
}

function drawEdt(){

}


// A faire (corriger avec fab) : mini calendrier
// ICI








// A faire

window.addCourse = function() {
    alert('Ajouter un cours - Modal à implémenter');
};

window.addCourseAt = function(cell) {
    const day = cell.dataset.day;
    const time = cell.dataset.time;

    console.log('Add course at day ' + day + ' at ' + time);
    // TODO: Open modal with pre-filled day and time
    alert('Ajouter un cours le jour ' + day + ' à ' + time);
};