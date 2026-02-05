package com.univtime.informatique.dto.coursDto;

import com.univtime.informatique.dto.participeADto.SousGroupeParticipeADto;

import java.util.Objects;

public class ParticipeACoursDto {
    private SousGroupeParticipeADto sousGroupeDto;

    public ParticipeACoursDto() {

    }

    public ParticipeACoursDto(SousGroupeParticipeADto sousGroupeDto) {
        this.sousGroupeDto = sousGroupeDto;
    }

    public SousGroupeParticipeADto getSousGroupeDto() {
        return sousGroupeDto;
    }

    public void setSousGroupeDto(SousGroupeParticipeADto sousGroupeDto) {
        this.sousGroupeDto = sousGroupeDto;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sousGroupeDto);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) return false;
        ParticipeACoursDto that = (ParticipeACoursDto) obj;
        return Objects.equals(sousGroupeDto, that.sousGroupeDto);
    }
}
