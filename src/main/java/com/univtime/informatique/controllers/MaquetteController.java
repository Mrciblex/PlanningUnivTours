package com.univtime.informatique.controllers;

import com.univtime.informatique.dto.cmDto.*;
import com.univtime.informatique.dto.composanteDto.CMComposanteDto;
import com.univtime.informatique.dto.composanteDto.ComposanteDto;
import com.univtime.informatique.dto.composanteDto.TDComposanteDto;
import com.univtime.informatique.dto.composanteDto.TPComposanteDto;
import com.univtime.informatique.dto.groupeDto.GroupeDto;
import com.univtime.informatique.dto.maquetteDto.MaquetteAssignationDto;
import com.univtime.informatique.dto.maquetteDto.MaquetteLigneDto;
import com.univtime.informatique.dto.maquetteDto.MaquetteSaveRequestDto;
import com.univtime.informatique.dto.moduleDto.ModuleDto;
import com.univtime.informatique.dto.professeurDto.ProfesseurDto;
import com.univtime.informatique.dto.promoDto.GroupePromoDto;
import com.univtime.informatique.dto.promoDto.PromoDto;
import com.univtime.informatique.dto.repartitionSemaineDto.RepartitionSemaineDto;
import com.univtime.informatique.dto.sousGroupeDto.SousGroupeDto;
import com.univtime.informatique.dto.tdDto.*;
import com.univtime.informatique.dto.tpDto.*;
import com.univtime.informatique.entities.ComposanteEntity;
import com.univtime.informatique.entities.ProfesseurEntity;
import com.univtime.informatique.entities.PromoEntity;
import com.univtime.informatique.entities.RepartitionSemaineEntity;
import com.univtime.informatique.services.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/gestionnaire-edt/maquette")
public class MaquetteController {
    /**
     * URL : /gestionnaire-edt/maquette
     */
    private final ModuleService moduleService;
    private final ComposanteService composanteService;
    private final ProfesseurService professeurService;
    private final GroupeService groupeService;
    private final SousGroupeService sousGroupeService;
    private final CMService cmService;
    private final TDService tdService;
    private final TPService tpService;
    private final PromoService promoService;
    private final RepartitionSemaineService repartitionSemaineService;

    public MaquetteController(
            ModuleService moduleService,
            ComposanteService composanteService,
            ProfesseurService professeurService,
            GroupeService groupeService,
            SousGroupeService sousGroupeService,
            CMService cmService,
            TDService tdService,
            TPService tpService,
            PromoService promoService,
            RepartitionSemaineService repartitionSemaineService) {
        this.moduleService = moduleService;
        this.composanteService = composanteService;
        this.professeurService = professeurService;
        this.groupeService = groupeService;
        this.sousGroupeService = sousGroupeService;
        this.cmService = cmService;
        this.tdService = tdService;
        this.tpService = tpService;
        this.promoService = promoService;
        this.repartitionSemaineService = repartitionSemaineService;
    }

    @GetMapping("/{promoId}/{semestre}")
    public String afficherMaquette(@PathVariable Integer promoId,
                                   @PathVariable Integer semestre,
                                   Model model) {

        // Configuration de base de la maquette (Ex: 15 semaines de 0 à 14 ou 1 à 15)

        PromoDto promo = promoService.findPromoDtoById(promoId);
        model.addAttribute("promo", promo);
        model.addAttribute("numSemestre", semestre);

        int nbSemaines = semestre == 1 ?
                (int) ChronoUnit.WEEKS.between(promo.getDebutS1Promo(), promo.getFinS1Promo())
                : (int) ChronoUnit.WEEKS.between(promo.getDebutS2Promo(), promo.getFinS2Promo());

        model.addAttribute("nbsem", nbSemaines);

        // Données globales disponibles pour l'importation dans la vue JS
        model.addAttribute("modulesDispos", moduleService.findAllModules());
        model.addAttribute("composantesDispos", composanteService.findAllComposantes());
        model.addAttribute("professeursDispos", professeurService.findAllProfesseurs());
        model.addAttribute("groupesDispos", groupeService.findGroupeDtoByIdPromo(promoId));

        List<CMDto> existingCms = cmService.findCMDtoByIdPromo(promoId);

        List<TDDto> existingTds = tdService.findTDDtoByIdPromo(promoId);

        List<TPDto> existingTps = tpService.findTPDtoByIdPromo(promoId);

        model.addAttribute("existingCms", existingCms);
        model.addAttribute("existingTds", existingTds);
        model.addAttribute("existingTps", existingTps);

        return "gestionnaire_edt/maquette";
    }

    @PostMapping("/save")
    @Transactional
    public ResponseEntity<?> saveMaquette(@RequestBody MaquetteSaveRequestDto request) {
        try {
            PromoDto promo = promoService.findPromoDtoById(Math.toIntExact(request.getIdPromo()));
            if (promo == null) {
                return ResponseEntity.badRequest().body("Promotion introuvable.");
            }

            // Nettoyage de la base pour cette promo (CM, TD, TP)
            List <CMDto> cms = cmService.findCMDtoByIdPromo(promo.getIdPromo());
            cms.forEach(cm -> cmService.deleteCMById(cm.getCMId()));

            List <TDDto> tds = tdService.findTDDtoByIdPromo(promo.getIdPromo());
            tds.forEach(td -> tdService.deleteTDById(td.getTDId()));

            List <TPDto> tps = tpService.findTPDtoByIdPromo(promo.getIdPromo());
            tps.forEach(tp -> tpService.deleteTPById(tp.getTPId()));

            // Injection des nouvelles données
            for (MaquetteLigneDto ligne : request.getLignes()) {
                ComposanteDto composante = composanteService.findComposanteDtoById(Math.toIntExact(ligne.getIdComposante()));
                if (composante == null) continue;

                for (MaquetteAssignationDto assign : ligne.getAssignations()) {
                    ProfesseurDto prof = professeurService.findProfesseurDtoById(Math.toIntExact(assign.getIdProf()));
                    if (prof == null) continue;

                    for (Map.Entry<Integer, Double> entry : assign.getVolumes().entrySet()) {
                        Integer semaine = entry.getKey();
                        Double volume = entry.getValue();

                        if (volume == null || volume <= 0) continue;

                        // Création d'une nouvelle Répartition unique
                        RepartitionSemaineDto repSem = new RepartitionSemaineDto();
                        repSem.setNumSemaine(semaine);
                        repSem.setQteTypeCours(volume.intValue());
                        repSem = repartitionSemaineService.createRepartitionSemaine(repSem);

                        // Injection selon le type
                        if ("CM".equals(assign.getType())) {
                            CMDto cm = new CMDto();
                            cm.setPromoDto(new PromoCMDto(promo.getIdPromo()));
                            cm.setComposanteDto(new ComposanteCMDto(composante.getIdComposante()));
                            cm.setProfesseurDto(new ProfesseurCMDto(prof.getIdProf()));
                            cm.setRepartitionSemaineDto(new RepartitionSemaineCMDto(repSem.getIdRepartitionSemaine()));
                            cmService.createCM(cm);

                        } else if ("TD".equals(assign.getType())) {
                            // cibleId = "GRP_12"
                            int idGroupe = Integer.parseInt(assign.getCibleId().split("_")[1]);
                            GroupeDto groupe = groupeService.findGroupeDtoById(idGroupe);

                            TDDto td = new TDDto();
                            td.setGroupeDto(new GroupeTDDto(groupe.getIdGroupe()));
                            td.setComposanteDto(new ComposanteTDDto(composante.getIdComposante()));
                            td.setProfesseurDto(new ProfesseurTDDto(prof.getIdProf()));
                            td.setRepartitionSemaineDto(new RepartitionSemaineTDDto(repSem.getIdRepartitionSemaine()));
                            tdService.createTD(td);

                        } else if ("TP".equals(assign.getType())) {
                            // cibleId = "SG_45"
                            Integer idSousGroupe = Integer.parseInt(assign.getCibleId().split("_")[1]);
                            SousGroupeDto sg = sousGroupeService.findSousGroupeDtoById(idSousGroupe);

                            TPDto tp = new TPDto();
                            tp.setSousGroupeDto(new SousGroupeTPDto(sg.getIdSousGroupe()));
                            tp.setComposanteDto(new ComposanteTPDto(composante.getIdComposante()));
                            tp.setProfesseurDto(new ProfesseurTPDto(prof.getIdProf()));
                            tp.setRepartitionSemaineDto(new RepartitionSemaineTPDto(repSem.getIdRepartitionSemaine()));
                            tpService.createTP(tp);
                        }
                    }
                }
            }

            return ResponseEntity.ok("Sauvegarde réussie.");
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResponseEntity.internalServerError().body("Erreur serveur : " + e.getMessage());
        }
    }

    // ----------- CREATE -----------

    @PostMapping("/{idPromo}/modules")
    public String createModule(@PathVariable Integer idPromo,
                               @ModelAttribute ModuleDto moduleDto) {
        moduleService.createModule(moduleDto);
        return "redirect:/gestionnaire-edt/maquette/" + idPromo;
    }

    @PostMapping("/{idPromo}/composantes")
    public String createComposante(@PathVariable Integer idPromo,
                                   @ModelAttribute ComposanteDto composanteDto) {
        composanteService.createComposante(composanteDto);
        return "redirect:/gestionnaire-edt/maquette/" + idPromo;
    }

    @PostMapping("/{idPromo}/professeurs")
    public String createProfesseur(@PathVariable Integer idPromo,
                                   @ModelAttribute ProfesseurDto professeurDto) {
        professeurService.createProfesseur(professeurDto);
        return "redirect:/gestionnaire-edt/maquette/" + idPromo;
    }

    @PostMapping("/{idPromo}/groupes")
    public String createGroupe(@PathVariable Integer idPromo,
                               @ModelAttribute GroupeDto groupeDto) {
        groupeService.createGroupe(groupeDto);
        return "redirect:/gestionnaire-edt/maquette/" + idPromo;
    }

    @PostMapping("/{idPromo}/sous-groupes")
    public String createSousGroupe(@PathVariable Integer idPromo,
                                   @ModelAttribute SousGroupeDto sousGroupeDto) {
        sousGroupeService.createSousGroupe(sousGroupeDto);
        return "redirect:/gestionnaire-edt/maquette/" + idPromo;
    }

    // ----------- DELETE -----------

    @PostMapping("/{idPromo}/modules/{id}/delete")
    public String deleteModule(@PathVariable Integer idPromo,
                               @PathVariable Integer id) {
        moduleService.deleteModuleById(id);
        return "redirect:/gestionnaire-edt/maquette/" + idPromo;
    }

    @PostMapping("/{idPromo}/composantes/{id}/delete")
    public String deleteComposante(@PathVariable Integer idPromo,
                                   @PathVariable Integer id) {
        composanteService.deleteComposanteById(id);
        return "redirect:/gestionnaire-edt/maquette/" + idPromo;
    }

    @PostMapping("/{idPromo}/groupes/{id}/delete")
    public String deleteGroupe(@PathVariable Integer idPromo,
                               @PathVariable Integer id) {
        groupeService.deleteGroupeById(id);
        return "redirect:/gestionnaire-edt/maquette/" + idPromo;
    }

    @PostMapping("/{idPromo}/sous-groupes/{id}/delete")
    public String deleteSousGroupe(@PathVariable Integer idPromo,
                                   @PathVariable Integer id) {
        sousGroupeService.deleteSousGroupeById(id);
        return "redirect:/gestionnaire-edt/maquette/" + idPromo;
    }

    private <T> Map<Integer, Integer> nbParSem(
            Set<T> items,
            Function<T, Integer> semExtracteur) {

        Map<Integer, Integer> map = new HashMap<>();

        // Pour 12 semaines
        for (int i = 1; i <= 12; i++) {
            map.put(i, 0);
        }

        if (items == null) return map;

        for (T item : items) {
            Integer sem = semExtracteur.apply(item);
            if (sem != null) {
                map.put(sem, map.getOrDefault(sem, 0) + 1);
            }
        }

        return map;
    }
}
