package com.univtime.informatique.dtos.cmDto;

import com.univtime.informatique.entities.ComposanteEntity;
import com.univtime.informatique.entities.ProfesseurEntity;
import com.univtime.informatique.entities.PromoEntity;
import com.univtime.informatique.entities.RepartitionSemaineEntity;
import com.univtime.informatique.entities.ids.CMId;

public class CMDto {
    private CMId idCM;
    private ProfesseurEntity professeur;
    private PromoEntity promo;
    private ComposanteEntity composante;
    private RepartitionSemaineEntity repartitionSemaine;

    public CMEntity() {

    }

    public CMId getIdCM() {
        return idCM;
    }

    public void setIdCM(CMId idCM) {
        this.idCM = idCM;
    }

    public ProfesseurEntity getProfesseur() {
        return professeur;
    }

    public void setProfesseur(ProfesseurEntity professeur) {
        this.professeur = professeur;
    }

    public PromoEntity getPromo() {
        return promo;
    }

    public void setPromo(PromoEntity promo) {
        this.promo = promo;
    }

    public ComposanteEntity getComposante() {
        return composante;
    }

    public void setComposante(ComposanteEntity composante) {
        this.composante = composante;
    }

    public RepartitionSemaineEntity getRepartitionSemaine() {
        return repartitionSemaine;
    }

    public void setRepartitionSemaine(RepartitionSemaineEntity repartitionSemaine) {
        this.repartitionSemaine = repartitionSemaine;
    }
}
