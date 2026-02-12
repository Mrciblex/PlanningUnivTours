package com.univtime.informatique.algorithme;

import com.univtime.informatique.constants.TypeCours;
import com.univtime.informatique.dto.cmDto.CMDto;
import com.univtime.informatique.dto.composanteDto.ComposanteDto;
import com.univtime.informatique.dto.coursDto.ComposanteCoursDto;
import com.univtime.informatique.dto.coursDto.CoursDto;
import com.univtime.informatique.dto.coursDto.ParticipeACoursDto;
import com.univtime.informatique.dto.coursDto.ProfesseurCoursDto;
import com.univtime.informatique.dto.jourDto.DisponibiliteJourDto;
import com.univtime.informatique.dto.jourDto.JourDto;
import com.univtime.informatique.dto.professeurDto.ProfesseurDto;
import com.univtime.informatique.dto.promoDto.PromoDto;
import com.univtime.informatique.dto.sousGroupeDto.SousGroupeDto;
import com.univtime.informatique.dto.tdDto.TDDto;
import com.univtime.informatique.dto.tpDto.TPDto;
import com.univtime.informatique.helpers.MomentBanalise;
import com.univtime.informatique.helpers.PlanningPeriodMinutes;
import com.univtime.informatique.helpers.Semestre;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class GenerationAlgorithme {
    private static int slotStep = 15;
    private static List<DayOfWeek> excludedDays = new ArrayList<>();
    private static List<PlanningPeriodMinutes> planningPossiblePeriod = new ArrayList<>();

    public static List<PlanningPeriodMinutes> getPlanningPossiblePeriod() {
        return planningPossiblePeriod;
    }

    public static void setPlanningPossiblePeriod(List<PlanningPeriodMinutes> planningPossiblePeriod) {
        GenerationAlgorithme.planningPossiblePeriod = planningPossiblePeriod;
    }

    public static List<DayOfWeek> getExcludedDays() {
        return excludedDays;
    }

    public static void setExcludedDays(List<DayOfWeek> excludedDays) {
        GenerationAlgorithme.excludedDays = excludedDays;
    }

    public static int getSlotStep() {
        return slotStep;
    }

    public static void setSlotStep(int slotStep) {
        GenerationAlgorithme.slotStep = slotStep;
    }

    private static boolean isExcludeDay(LocalDate actualDate, List<DayOfWeek> excludeDays) {
        return excludeDays.contains(actualDate.getDayOfWeek());
    }

    private static int getChargeTotaleMinutes(int weekOffset,
                                              ProfesseurDto p,
                                              List<CMDto> cms,
                                              List<TDDto> tds,
                                              List<TPDto> tps) {

        // Somme du nombre total de cours à placer pour un prof * la durée d'un bloc (en minutes), pour la semaine donnée
        // Possible de le faire via requête DB
        int chargeCM = cms.stream()
                .filter(c -> c.getProfesseurDto().getIdProf().equals(p.getIdProf()))
                .filter(c -> c.getRepartitionSemaineDto().getNumSemaine().equals(weekOffset))
                .mapToInt(c -> c.getRepartitionSemaineDto().getQteTypeCours() * c.getComposanteDto().getBlocHoraireCM())
                .sum();

        int chargeTD = tds.stream()
                .filter(c -> c.getProfesseurDto().getIdProf().equals(p.getIdProf()))
                .filter(c -> c.getRepartitionSemaineDto().getNumSemaine().equals(weekOffset))
                .mapToInt(c -> c.getRepartitionSemaineDto().getQteTypeCours() * c.getComposanteDto().getBlocHoraireTD())
                .sum();

        int chargeTP = tps.stream()
                .filter(c -> c.getProfesseurDto().getIdProf().equals(p.getIdProf()))
                .filter(c -> c.getRepartitionSemaineDto().getNumSemaine().equals(weekOffset))
                .mapToInt(c -> c.getRepartitionSemaineDto().getQteTypeCours() * c.getComposanteDto().getBlocHoraireTP())
                .sum();

        return chargeCM + chargeTD + chargeTP;
    }

    private static int getTotalDisponibiliteMinutes(ProfesseurDto p) {
        Integer idProf = p.getIdProf();

        /*
            SELECT * FROM Jours WHERE idProf = 1;
         */
        // Permet d'avoir les disponibilités par jour du prof
        List<JourDto> jours = new ArrayList<JourDto>();

        return jours.stream()
                .flatMap(jourDto -> jourDto.getDisponibiliteDto().stream())
                .mapToInt(dispo -> dispo.getHeureFinDispo() - dispo.getHeureDebutDispo())
                .sum();
    }

    private static double getProfTension(int weekOffset, ProfesseurDto p, List<CMDto> cms, List<TDDto> tds, List<TPDto> tps){
        int charge = getChargeTotaleMinutes(weekOffset, p, cms, tds, tps);
        int dispo = getTotalDisponibiliteMinutes(p);

        if (dispo == 0) return Double.MAX_VALUE; // Si aucune disponibilité, priorité maximale (juste pour éviter les bugs, ça n'a pas d'impact)
        return (double) charge / dispo;
    }

    private static List<PlanningPeriodMinutes> generatePlanningPossiblePeriod(){
        List<PlanningPeriodMinutes> planningPossiblePeriod = new ArrayList<PlanningPeriodMinutes>();
        planningPossiblePeriod.add(new PlanningPeriodMinutes(8 * 60, 10 * 60)); // 8h00 à 10h00
        planningPossiblePeriod.add(new PlanningPeriodMinutes(10 * 60 + 15, 12 * 60 + 15)); // 10h15 à 12h15
        planningPossiblePeriod.add(new PlanningPeriodMinutes(13 * 60 + 30, 15 * 60 + 30)); // 13h30 à 15h30
        planningPossiblePeriod.add(new PlanningPeriodMinutes(15 * 60 + 45, 17 * 60 + 45)); // 15h45 à 17h45
        planningPossiblePeriod.add(new PlanningPeriodMinutes(18 * 60, 20 * 60)); // 18h00 à 20h00

        return planningPossiblePeriod;
    }

    private static List<DayOfWeek> generateExcludedDays(){
        List<DayOfWeek> excludedDays = new ArrayList<DayOfWeek>();
        excludedDays.add(DayOfWeek.SATURDAY); // Samedi
        excludedDays.add(DayOfWeek.SUNDAY); // Dimanche

        return excludedDays;
    }

    private static boolean isSlotAvailable(LocalDateTime start, LocalDateTime end, List<MomentBanalise> momentBanalises){
        boolean isSlotAvailable = true;
        for (MomentBanalise momentBanalise : momentBanalises) {
            if (isSlotAvailable && start.isBefore(momentBanalise.fin()) && end.isAfter(momentBanalise.debut())) {
                // Un chevauchement a été trouvé, donc le slot n'est pas disponible
                isSlotAvailable = false;
            }
        }
        return isSlotAvailable;
    }

    private record DateCours(
            LocalDateTime heureDebutCours,
            LocalDateTime heureFinCours
    ){}

    /**
        Calcul les dates réelles de début et de fin pour un cours donné
        en utilisant les index numSemaine et numJour par rapport à la structure du planning
     */
    private static DateCours getRealDateCours(){
        return null;
    }

    private static boolean isProfDisponible(Slot slot, Set<DisponibiliteJourDto> dispos) {
        for (DisponibiliteJourDto dispo : dispos) {
            if (slot.getDebut() >= dispo.getHeureDebutDispo() &&
                    slot.getFin() <= dispo.getHeureFinDispo()) {
                return true;
            }
        }
        return false;
    }

    private static boolean isSameParticipants(Slot slot, CoursDto cours){
        for (ParticipeACoursDto participantCandidat : cours.getParticipeADto()) {
            for(CoursDto cour : slot.getUsedBy()){
                for(ParticipeACoursDto participantActuel : cour.getParticipeADto()){
                    if (participantCandidat.equals(participantActuel)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /*
        ATTENTION A NE PAS FAIRE LA CONFUSION ENTRE "Jours" DE LA BASE DE DONNEES ET "Jour" L'OBJECT METIER (my bad...)
     */
    private static Jour getRandomBestPlacement(PromoDto promo, Semaine currentSemaine, CoursDto cours){
        int dureeSemaine = currentSemaine.getJours().size();
        Integer blocNecessaire = cours.getComposanteDto().getBlocHoraire(cours.getTypeCoursEnum());
        int nbSlotNecessaire = (int) Math.ceil((double) blocNecessaire / slotStep);
        List<Jour> bestPlacements = new ArrayList<Jour>();
        Set<ParticipeACoursDto> participants = cours.getParticipeADto();

        Integer idProf = cours.getProfesseurDto().getIdProf();

        /*
            SELECT * FROM Jours WHERE idProf = 1;
         */
        // Permet d'avoir les disponibilités par jour du prof
        List<JourDto> jours = new ArrayList<JourDto>();

        for (int i = 0; i < dureeSemaine; i++){
            int currentDay = i;
            Jour jour = currentSemaine.getJours().get(i);
            Set<DisponibiliteJourDto> disponibiliteProf = jours.stream()
                    .filter(j -> j.getJourSemaine() == currentDay + 1)
                    .map(JourDto::getDisponibiliteDto)
                    .flatMap(Set::stream)
                    .collect(Collectors.toSet());

            // Ca ne sert à rien de boucler sur les dernières heures si le bloc est plus gros que la place qu'il nous reste
            outerloop:
            for (int j = 0; j < jour.getSlots().size() - nbSlotNecessaire; j++){
                List<Slot> slots = new ArrayList<>(); // Bloc du cours

                for (int k = 0; k < nbSlotNecessaire; k++){
                    int indexActuel = j + k;
                    Slot slot = jour.getSlots().get(indexActuel);

                    // On s'assure que les participants n'ont pas déjà cours dans ce slot
                    if (isSameParticipants(slot, cours)){
                        j = indexActuel; // Au reset de la boucle, j prends + 1 donc pas besoin de faire + 1 ici
                        continue outerloop;
                    }

                    // On s'assure que le professeurs est dispo pour tout les créneau qu'on utilise pour placer le bloc de cours
                    if(!isProfDisponible(slot, disponibiliteProf)){
                        j = indexActuel; // Au reset de la boucle, j prends + 1 donc pas besoin de faire + 1 ici
                        continue outerloop;
                    }

                    // On s'assure que les slots du bloc sont continu (pas de coupure au milieu du cours, en dehors des pauses de 15 min)
                    // Si slots non continu (diff > 15 minutes), par exemple pause de midi, alors on reprends au premier slot dispo après
                    if (k > 0){
                        Slot slotPrecedent = jour.getSlots().get(indexActuel - 1);
                        if (slot.getDebut() - slotPrecedent.getFin() > 15){
                            j = indexActuel - 1; // Au reset de la boucle, j prends + 1 donc comme on veut l'index Actuel alors on fait -1 ici
                            continue outerloop;
                        }
                    }
                    slots.add(slot);
                }

                // Si on arrive ici, c'est que le inner loop (k) est allé au bout sans break/continue
                // Donc le bloc est valide !
                bestPlacements.add(new Jour(
                        j + 1,
                        slots
                ));
            }
        }



        return null;
    }

    public static List<CoursDto> generatePlanning(Integer idPromo, Integer numSemestre, List<MomentBanalise> momentBanalises) {
            // INSERT TEST DATA
            /**
             * Paramètres globaux
             */
            // Hard coded (15 min)
            int slotStep = GenerationAlgorithme.getSlotStep();

            // Dynamique (non lié à la BD -> saisie lors de la génération (ne pas oublier jours fériés proposés))
            List<MomentBanalise> pauses = momentBanalises;

            // Hard coded
            List<DayOfWeek> excludedDays = GenerationAlgorithme.generateExcludedDays();

            // Hard coded
            List<PlanningPeriodMinutes> planningPossiblePeriod = GenerationAlgorithme.generatePlanningPossiblePeriod();

            GenerationAlgorithme.setExcludedDays(excludedDays);
            GenerationAlgorithme.setPlanningPossiblePeriod(planningPossiblePeriod);

            /**
             * Paramètres dynamiques de la base de données
             */

            /*
                SELECT * FROM Promos WHERE idPromo = 1;
             */
        PromoDto promoComplete = new PromoDto();

        // Dynamique lié à la BD
        Semestre semestre;
        if (numSemestre == 1){
            semestre = new Semestre(promoComplete.getDebutS1Promo(), promoComplete.getFinS1Promo());
        }else{
            semestre = new Semestre(promoComplete.getDebutS2Promo(), promoComplete.getFinS1Promo());
        }

            // Tout les sous groupes de la promo actuelle selectionner (paramètre idPromo)

            /*
                SELECT s FROM sousGroupes s
                INNER JOIN Groupes g USING (idGroupe)
                INNER JOIN Promos p USING (idPromo)
                WHERE p.idPromo = 1;
             */
            List<SousGroupeDto> promo = new ArrayList<>();

            // Toutes les composantes concerné par cette promo
            /*
                SELECT c FROM Composantes c
                INNER JOIN Modules m USING (idModule)
                INNER JOIN PromoEstComposee pc USING (idModule)
                INNER JOIN Promos p USING (idPromo)
                WHERE p.idPromo = 1;
             */
            List<ComposanteDto> composantes = new ArrayList<>();

            // Tout les professeurs avec leurs jours et leurs disponibilités concerné par cette promo
            /*
                SELECT p FROM Professeurs p
                INNER JOIN CM cm USING (idProf)
                INNER JOIN TD td USING (idProf)
                INNER JOIN TP tp USING (idProf)
                INNER JOIN Promos pr USING (idPromo)
                WHERE pr.idPromo = 1;

             */
            List<ProfesseurDto> professeurs = new ArrayList<>();

            /*
                SELECT * FROM CM WHERE idPromo = 1;
             */
            List<CMDto> cms = new ArrayList<>();

            /*
                SELECT td FROM TD td
                INNER JOIN Groupes g USING (idGroupe)
                INNER JOIN Promos p USING (idPromo)
                WHERE p.idPromo = 1;
             */
            List<TDDto> tds = new ArrayList<>();

            /*
                SELECT tp FROM TP tp
                INNER JOIN SousGroupes sg USING (idSousGroupe)
                INNER JOIN Groupes grp USING (idGroupe)
                INNER JOIN Promos p USING (idPromo)
                WHERE p.idPromo = 1;
             */
            List<TPDto> tps = new ArrayList<>();

            //System.out.println(cm);
            //System.out.println(td);
            //System.out.println(tp);

            /**
             * Boucle sur tout les jours du semestre
             */
            long nbSemaine = semestre.nbSemaines();
            List<Semaine> semaines = new ArrayList<>();

            for (int weekOffset = 0; weekOffset < nbSemaine; weekOffset++) {
                // Début et fin de la semaine du calendrier
                LocalDate weekStart = semestre.firstWeek().plusWeeks(weekOffset);
                LocalDate weekEnd = weekStart.plusDays(6);

                // Ajuster pour la première semaine partielle
                if (weekStart.isBefore(semestre.debut())) {
                    weekStart = semestre.debut();
                }

                // Ajuster pour la dernière semaine partielle
                if (weekEnd.isAfter(semestre.fin())) {
                    weekEnd = semestre.fin();
                }

                int currentWeek = weekOffset + 1;

                List<Jour> jours = new ArrayList<Jour>();
                for (int dayOffset = weekStart.getDayOfWeek().getValue() - 1; dayOffset < weekEnd.getDayOfWeek().getValue(); dayOffset++) {
                    LocalDate actualDay = semestre.debut().plusWeeks(weekOffset).plusDays(dayOffset);
                    if (GenerationAlgorithme.isExcludeDay(actualDay, excludedDays)) {
                        continue;
                    }

                    /**
                     * Création des slots de la semaine
                     */
                    List<Slot> slots = new ArrayList<>();
                    for (PlanningPeriodMinutes period : planningPossiblePeriod) {
                        for (int start = period.debut(); start < period.fin(); start = start + slotStep) {
                            // Vérifier si ce créneau est présent dans des MomentBanalisés (jours fériés et autre)
                            if (
                                    !GenerationAlgorithme.isSlotAvailable(
                                        LocalDateTime.of(actualDay, LocalTime.of(0, 0, 0)),
                                        LocalDateTime.of(actualDay, LocalTime.of(23, 59, 59)),
                                        pauses
                                    )
                                ||
                                    !GenerationAlgorithme.isSlotAvailable(
                                            LocalDateTime.of(actualDay, LocalTime.of(start / 60, start % 60)),
                                            LocalDateTime.of(actualDay, LocalTime.of(start / 60, start % 60)),
                                            pauses
                                    )
                            ) {
                                continue;
                            }

                            Slot slot = new Slot(start, start + slotStep);
                            slots.add(slot);
                        }
                    }
                    jours.add(new Jour(dayOffset + 1, slots));

                }
                Semaine currentSemaine = new Semaine(currentWeek, jours);
                semaines.add(currentSemaine);



                // ------------ Placement des cours dans les slots ------------
                // On a tous les slots de la semaine actuel qui viennent d'être créés
                // Il faut boucler avec les profs (ordered) puis les cours à placer (ordered)
                // Boucler sur les slots de la semaine qui sont possible (verif prof et groupe)
                // Vérifier si le slot est pas dans un moment banalisé aussi, puis rank tous les placements de cours possible
                // Choisir aléatoire parmi x pourcent des meilleurs un placement
                // Une fois les slots de la semaine remplis, on améliore l'organisation
                // Il faut pour chaque slots essayer de replacer ses cours autre part dans la semaine et voir si le score de la semaine augmente
                // (Si un slot ou le score augmente est déjà pris, alors on essaie de déplacer celui là aussi mais on dit que le slot est désormais figé
                // C'est à dire que la prochaine itération d'amélioration de week, ne pourra pas changer ce slot
                // L'amélioration devrait être ordered avec les Slots allant de score le moins bon au meilleur (pour éviter de bouger les bons scores)

                // PB : Dans ce cas de relocalisation, si on remplace un slot occupé :
                // Est ce que c'est possible de re-placer le second slot (disponibilités) ? Oui, Est ce que c'est un meilleur score de semaine ? Oui
                // Alors on remplace le slot (ou les slots si plusieurs ont dû bouger) et on le(s) fige
                // Timeout si impossible ou score de semaine moins bien, ce après quoi : re-tentative d'up le score grâce au mouvement du slot (autre part parmi les meilleurs choix) x fois
                // Si aucune amélioration possible pour ce slot (score week inférieur ou impossibles disponibilités) ou score de nouveau placement inférieur,
                // Alors on ne fige que le slot qu'on peut pas améliorer, le rest des lots testés ne doivent pas être figé si impossible ou score week inférieur.

                // Dans le cas ou le slot nouveau meilleur slot est libre, alors on déplace et on fige le nouveau slot

                // Le calcul du Weight doit être optimisé car il sera souvent appelé pour tester l'amélioration du planning !
                // Les poids se calcul via la semaine

                /**
                 * Professeurs ordered pour affiner les résultats.
                 * Ratio volumeHoraire pour la semaine / disponibilité
                 * Si le ratio est > 1 alors trop de cours pour pas assez de dispo (Que faire en cas d'impossibilité -> warning des placements impossible + différence d'heures voulu à heures réel par composante + ne pas placer le cours)
                 * Si le ratio est <= 1 alors placement possible mais plus c'est proche de 1, plus il y a une tension pour les placements
                 */
                // Calcul de la tension (ratio volume/dispo) pour ordonner les professeurs

                professeurs.sort((p1, p2) -> {
                    double ratio1 = GenerationAlgorithme.getProfTension(currentWeek, p1, cms, tds, tps);
                    double ratio2 = GenerationAlgorithme.getProfTension(currentWeek, p2, cms, tds, tps);
                    return Double.compare(ratio2, ratio1); // Plus le ratio est élevé, plus il est prioritaire
                });

                System.out.println("COURS QUI DOIVENT ÊTRE PLACER ------------");
                System.out.println("SEMAINE " + currentWeek + " : ");
                for (ProfesseurDto prof : professeurs) {
                    cms.stream()
                            .filter(cours -> cours.getProfesseurDto().getIdProf().equals(prof.getIdProf()))
                            .filter(cours -> cours.getRepartitionSemaineDto().getNumSemaine().equals(currentWeek) && cours.getRepartitionSemaineDto().getQteTypeCours() > 0)
                            .forEach(cour -> {

                                // ----------------------- DEBUG -----------------------
                                StringBuilder cmGroupes = new StringBuilder();
                                for (int sg = 0; sg < promo.size(); sg++) {
                                    if (sg == promo.size() - 1) {
                                        cmGroupes.append(promo.get(sg).getNomSousGroupe());
                                    } else {
                                        cmGroupes.append(promo.get(sg).getNomSousGroupe()).append(",");
                                    }
                                }
                                System.out.println(
                                        "CM " + cmGroupes + " | " +
                                                cour.getComposanteDto().getNomComposante() + " x" + cour.getRepartitionSemaineDto().getQteTypeCours() + " | " +
                                                cour.getProfesseurDto().getPrenomProf() + " " + cour.getProfesseurDto().getNomProf());
                                // ----------------------- FIN DEBUG -----------------------

                                Set<ParticipeACoursDto> participeACoursDto = promo.stream().map(sg -> {
                                    return new ParticipeACoursDto(
                                            sg.getIdSousGroupe(),
                                            sg.getNomSousGroupe(),
                                            sg.getNbEtuSousGroupe(),
                                            sg.getGroupeDto().getIdGroupe()
                                    );
                                }).collect(Collectors.toSet());

                                ComposanteCoursDto composanteCoursDto = new ComposanteCoursDto(
                                        cour.getComposanteDto().getIdComposante(),
                                        cour.getComposanteDto().getBlocHoraireCM(),
                                        cour.getComposanteDto().getBlocHoraireTD(),
                                        cour.getComposanteDto().getBlocHoraireTP()
                                );

                                ProfesseurCoursDto professeurCoursDto = new ProfesseurCoursDto(
                                        cour.getProfesseurDto().getIdProf(),
                                        cour.getProfesseurDto().getJourIds()
                                );

                                CoursDto courCreated = new CoursDto(
                                        TypeCours.CM,
                                        composanteCoursDto,
                                        professeurCoursDto,
                                        null,
                                        participeACoursDto
                                );

                                // Créer une fonction qui prend en paramètre : la list actuelle du planning de la semaine (slots)
                                // Le cours à ajouter,
                                // Optimisation à faire : on ne re-calcul réellement que le score du jour qui change à chaque fois ! Et non de la semaine entière tout le temps
                                // Après on re-fait juste la moyenne

                                getRandomBestPlacement(promoComplete, currentSemaine, courCreated);
                            });

                    tds.stream()
                            .filter(cours -> cours.getProfesseurDto().getIdProf().equals(prof.getIdProf()))
                            .filter(cours ->  cours.getRepartitionSemaineDto().getNumSemaine().equals(currentWeek) && cours.getRepartitionSemaineDto().getQteTypeCours() > 0)
                            .forEach(cours -> {
                                List<SousGroupeDto> sousGroupesDto = promo.stream()
                                        .filter(sg -> sg.getGroupeDto().getIdGroupe().equals(cours.getGroupeDto().getIdGroupe())).toList();

                                // ----------------------- DEBUG -----------------------
                                StringBuilder tdGroupes = new StringBuilder();
                                for (int sg = 0; sg < sousGroupesDto.size(); sg++) {
                                    if (sg == sousGroupesDto.size() - 1) {
                                        tdGroupes.append(sousGroupesDto.get(sg).getNomSousGroupe());
                                    } else {
                                        tdGroupes.append(sousGroupesDto.get(sg).getNomSousGroupe()).append(",");
                                    }
                                }
                                System.out.println(
                                        "CM " + tdGroupes + " | " +
                                                cours.getComposanteDto().getNomComposante() + " x" + cours.getRepartitionSemaineDto().getQteTypeCours() + " | " +
                                                cours.getProfesseurDto().getPrenomProf() + " " + cours.getProfesseurDto().getNomProf());
                                // ----------------------- FIN DEBUG -----------------------

                            });

                    tps.stream()
                            .filter(cours -> cours.getProfesseurDto().getIdProf().equals(prof.getIdProf()))
                            .filter(cours -> cours.getRepartitionSemaineDto().getNumSemaine().equals(currentWeek) && cours.getRepartitionSemaineDto().getQteTypeCours() > 0)
                            .forEach(cours -> {

                                // ----------------------- DEBUG -----------------------
                                System.out.println(
                                        "CM " + cours.getSousGroupeDto().getNomSousGroupe() + " | " +
                                                cours.getComposanteDto().getNomComposante() + " x" + cours.getRepartitionSemaineDto().getQteTypeCours() + " | " +
                                                cours.getProfesseurDto().getPrenomProf() + " " + cours.getProfesseurDto().getNomProf());
                                // ----------------------- FIN DEBUG -----------------------

                            });
                }

            }
        return null;
    }



    /**
     * Fonction pour générer les données issues de la base données de test
     */
    /*
    private static List<ProfesseurDto> testDataProfesseurs(){
        List<ProfesseurDto> profs = new ArrayList<>();
        // DISPO QUE LE MATIN (8h-12h15)
        profs.add(new ProfesseurDto(
                        "Brouard",
                        "Thierry",
                        false,
                        List.of(
                                new JourDto(1, List.of(new DisponibilitePojo(8 * 60, 12 * 60 + 15))),
                                new JourPojo(2, List.of(new DisponibilitePojo(8 * 60, 12 * 60 + 15))),
                                new JourPojo(3, List.of(new DisponibilitePojo(8 * 60, 12 * 60 + 15))),
                                new JourPojo(4, List.of(new DisponibilitePojo(8 * 60, 12 * 60 + 15))),
                                new JourPojo(5, List.of(new DisponibilitePojo(8 * 60, 12 * 60 + 15)))
                        )
                )
        );
        // DISPO TOUT LE TEMPS
        profs.add(new ProfesseurPojo(
                        "Brouard",
                        "Helene",
                        false,
                        List.of(
                                new JourPojo(1, List.of(new DisponibilitePojo(8 * 60, 20 * 60))),
                                new JourPojo(2, List.of(new DisponibilitePojo(8 * 60, 20 * 60))),
                                new JourPojo(3, List.of(new DisponibilitePojo(8 * 60, 20 * 60))),
                                new JourPojo(4, List.of(new DisponibilitePojo(8 * 60, 20 * 60))),
                                new JourPojo(5, List.of(new DisponibilitePojo(8 * 60, 20 * 60)))
                        )
                )
        );
        // DISPO QUE LE MATIN (10h30-12h15) DONC MOINS DE 2H
        profs.add(new ProfesseurPojo(
                        "Desport",
                        "Pierre",
                        false,
                        List.of(
                                new JourPojo(1, List.of(new DisponibilitePojo(10 * 60 + 30, 12 * 60 + 15))),
                                new JourPojo(2, List.of(new DisponibilitePojo(10 * 60 + 30, 12 * 60 + 15))),
                                new JourPojo(3, List.of(new DisponibilitePojo(10 * 60 + 30, 12 * 60 + 15))),
                                new JourPojo(4, List.of(new DisponibilitePojo(10 * 60 + 30, 12 * 60 + 15))),
                                new JourPojo(5, List.of(new DisponibilitePojo(10 * 60 + 30, 12 * 60 + 15)))
                        )
                )
        );
        // DISPO DECOUPER
        profs.add(new ProfesseurPojo(
                        "Cabet",
                        "Aurore",
                        false,
                        List.of(
                                new JourPojo(1, List.of(new DisponibilitePojo(8 * 60, 10 * 60 + 15), new DisponibilitePojo(15 * 60 + 45, 17 * 60 + 45))),
                                new JourPojo(2, List.of(new DisponibilitePojo(8 * 60, 10 * 60 + 15), new DisponibilitePojo(15 * 60 + 45, 17 * 60 + 45))),
                                new JourPojo(3, List.of(new DisponibilitePojo(8 * 60, 10 * 60 + 15), new DisponibilitePojo(15 * 60 + 45, 17 * 60 + 45))),
                                new JourPojo(4, List.of(new DisponibilitePojo(8 * 60, 10 * 60 + 15), new DisponibilitePojo(15 * 60 + 45, 17 * 60 + 45))),
                                new JourPojo(5, List.of(new DisponibilitePojo(8 * 60, 10 * 60 + 15), new DisponibilitePojo(15 * 60 + 45, 17 * 60 + 45)))
                        )
                )
        );
        return profs;
    }

    public static Semestre testDataSemestre(){
        return new Semestre(
                LocalDate.of(2025, 9, 1),
                LocalDate.of(2025, 9, 7)
        );
    }

    private static List<ComposantePojo> testDataComposantes(){
        List<ComposantePojo> composantes = new ArrayList<>();
        composantes.add(new ComposantePojo("Maths", 2 * 60, 2 * 60, 2 * 60));
        composantes.add(new ComposantePojo("Admin BD", 2 * 60, 2 * 60, 2 * 60));
        composantes.add(new ComposantePojo("Securite Logicielle", 2 * 60, 2 * 60, 2 * 60));

        return composantes;
    }

    private static List<SousGroupePojo> testDataPromo(){
        List<SousGroupePojo> promo = new ArrayList<>();
        promo.add(new SousGroupePojo("G1A", 10));
        promo.add(new SousGroupePojo("G1B", 10));
        promo.add(new SousGroupePojo("G2A", 10));
        promo.add(new SousGroupePojo("G2B", 10));

        return promo;
    }
    */
}