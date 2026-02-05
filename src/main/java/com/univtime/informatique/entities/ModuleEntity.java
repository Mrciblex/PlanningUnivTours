package com.univtime.informatique.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "modules")
public class ModuleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idModule", nullable = false)
    private Integer idModule;

    @Column(name = "nomModule", nullable = false, length = 150)
    private String nomModule;

    @OneToMany(mappedBy = "module", fetch = FetchType.LAZY)
    private Set<PromoEstComposeeEntity> promoEstComposeeEntities = new HashSet<>();

    @OneToMany(mappedBy = "module", fetch = FetchType.LAZY)
    private Set<ComposanteEntity> composanteEntities = new HashSet<>();

    public Integer getIdModule() {
        return idModule;
    }

    public void setIdModule(Integer idModule) {
        this.idModule = idModule;
    }

    public String getNomModule() {
        return nomModule;
    }

    public void setNomModule(String nomModule) {
        this.nomModule = nomModule;
    }

    public Set<PromoEstComposeeEntity> getPromoEstComposeeEntities() {
        return promoEstComposeeEntities;
    }

    public void setPromoEstComposeeEntities(Set<PromoEstComposeeEntity> promoEstComposeeEntities) {
        this.promoEstComposeeEntities = promoEstComposeeEntities;
    }

    public Set<ComposanteEntity> getComposanteEntities() {
        return composanteEntities;
    }

    public void setComposanteEntities(Set<ComposanteEntity> composanteEntities) {
        this.composanteEntities = composanteEntities;
    }
}
