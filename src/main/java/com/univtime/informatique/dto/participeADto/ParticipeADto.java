package com.univtime.informatique.dto.participeADto;

import com.univtime.informatique.entities.ids.ParticipeAId;

import java.util.Objects;

public class ParticipeADto {
    private ParticipeAId idParticipeA;

    public ParticipeADto() {

    }

    public ParticipeADto(
            ParticipeAId idParticipeA) {
        this.idParticipeA = idParticipeA;
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
        ParticipeADto that = (ParticipeADto) obj;
        return Objects.equals(idParticipeA, that.idParticipeA);
    }
}
