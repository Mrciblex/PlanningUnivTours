package com.univtime.informatique.dto.sousGroupeDto;

import com.univtime.informatique.dto.participeADto.CoursParticipeADto;

import java.util.Objects;

public class ParticipeASousGroupeDto {
    private CoursParticipeADto coursDto;

    public ParticipeASousGroupeDto() {

    }

    public ParticipeASousGroupeDto(CoursParticipeADto coursDto) {
        this.coursDto = coursDto;
    }

    public CoursParticipeADto getCoursDto() {
        return coursDto;
    }

    public void setCoursDto(CoursParticipeADto coursDto) {
        this.coursDto = coursDto;
    }

    @Override
    public int hashCode() {
        return Objects.hash(coursDto);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) return false;
        ParticipeASousGroupeDto that = (ParticipeASousGroupeDto) obj;
        return Objects.equals(coursDto, that.coursDto);
    }
}
