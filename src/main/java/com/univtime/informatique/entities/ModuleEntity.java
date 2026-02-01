package com.univtime.informatique.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

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
    private List<PromoEstComposeeEntity> promoEstComposeeEntities = new ArrayList<>();

    @OneToMany(mappedBy = "module", fetch = FetchType.LAZY)
    private List<ComposanteEntity> composanteEntities = new ArrayList<>();

    public ModuleEntity() {

    }

    public ModuleEntity(Integer idModule, String nomModule) {
        this.idModule = idModule;
        this.nomModule = nomModule;
    }

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
}
