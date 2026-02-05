package com.univtime.informatique.dto.participeADto;

import java.util.Objects;

public class participeADto {
    private SousGroupeParticipeADto sousGroupeDto;
    private CoursParticipeADto coursDto;

    public participeADto() {

    }

    public participeADto(SousGroupeParticipeADto sousGroupeDto, CoursParticipeADto coursDto) {
        this.sousGroupeDto = sousGroupeDto;
        this.coursDto = coursDto;
    }

    public SousGroupeParticipeADto getSousGroupeDto() {
        return sousGroupeDto;
    }

    public void setSousGroupeDto(SousGroupeParticipeADto sousGroupeDto) {
        this.sousGroupeDto = sousGroupeDto;
    }

    public CoursParticipeADto getCoursDto() {
        return coursDto;
    }

    public void setCoursDto(CoursParticipeADto coursDto) {
        this.coursDto = coursDto;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sousGroupeDto, coursDto);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) return false;
        participeADto that = (participeADto) obj;
        return Objects.equals(sousGroupeDto, that.sousGroupeDto) && Objects.equals(coursDto, that.coursDto);
    }
}
