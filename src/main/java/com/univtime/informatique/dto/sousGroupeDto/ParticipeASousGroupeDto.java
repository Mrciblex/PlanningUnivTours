package com.univtime.informatique.dto.sousGroupeDto;

import com.univtime.informatique.entities.ids.ParticipeAId;

import java.util.Objects;

public class ParticipeASousGroupeDto {
    private ParticipeAId idParticipeA;

    public ParticipeASousGroupeDto() {

    }

    public ParticipeASousGroupeDto(
            ParticipeAId participeAId) {
        this.idParticipeA = participeAId;
    }

    public ParticipeAId getIdParticipeA() {
        return idParticipeA;
    }

    public void setIdParticipeA(ParticipeAId idParticipeA) {
        this.idParticipeA = idParticipeA;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idParticipeA);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ParticipeASousGroupeDto that = (ParticipeASousGroupeDto) obj;
        return Objects.equals(idParticipeA, that.idParticipeA);
    }
}
