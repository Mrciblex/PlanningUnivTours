package com.univtime.informatique.dto.coursDto;

import com.univtime.informatique.constants.TypeCours;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

public class CoursDto {
    private Integer idCours;
    private LocalDateTime heureDebutCours;
    private LocalDateTime heureFinCours;
    private TypeCours typeCoursEnum;
    private ComposanteCoursDto composanteDto;
    private ProfesseurCoursDto professeurDto;
    private SalleCoursDto salleDto;
    private Set<ParticipeACoursDto> participeADto;

    public CoursDto() {
    }

    public CoursDto(
            Integer idCours,
            LocalDateTime heureDebutCours,
            LocalDateTime heureFinCours,
            TypeCours typeCoursEnum,
            ComposanteCoursDto composanteDto,
            ProfesseurCoursDto professeurDto,
            SalleCoursDto salleDto,
            Set<ParticipeACoursDto> participeADto) {
        this.idCours = idCours;
        this.heureDebutCours = heureDebutCours;
        this.heureFinCours = heureFinCours;
        this.typeCoursEnum = typeCoursEnum;
        this.composanteDto = composanteDto;
        this.professeurDto = professeurDto;
        this.salleDto = salleDto;
        this.participeADto = participeADto;
    }

    public CoursDto(
            TypeCours typeCoursEnum,
            ComposanteCoursDto composanteDto,
            ProfesseurCoursDto professeurDto,
            SalleCoursDto salleDto,
            Set<ParticipeACoursDto> participeADto) {
        this.typeCoursEnum = typeCoursEnum;
        this.composanteDto = composanteDto;
        this.professeurDto = professeurDto;
        this.salleDto = salleDto;
        this.participeADto = participeADto;
    }

    // Getters et Setters
    public Integer getIdCours() {
        return idCours;
    }

    public void setIdCours(Integer idCours) {
        this.idCours = idCours;
    }

    public LocalDateTime getHeureDebutCours() {
        return heureDebutCours;
    }

    public void setHeureDebutCours(LocalDateTime heureDebutCours) {
        this.heureDebutCours = heureDebutCours;
    }

    public LocalDateTime getHeureFinCours() {
        return heureFinCours;
    }

    public void setHeureFinCours(LocalDateTime heureFinCours) {
        this.heureFinCours = heureFinCours;
    }

    public TypeCours getTypeCoursEnum() {
        return typeCoursEnum;
    }

    public void setTypeCoursEnum(TypeCours typeCoursEnum) {
        this.typeCoursEnum = typeCoursEnum;
    }

    public ComposanteCoursDto getComposanteDto() {
        return composanteDto;
    }

    public void setComposanteDto(ComposanteCoursDto composanteCoursDto) {
        this.composanteDto = composanteCoursDto;
    }

    public ProfesseurCoursDto getProfesseurDto() {
        return professeurDto;
    }

    public void setProfesseurDto(ProfesseurCoursDto professeurCoursDto) {
        this.professeurDto = professeurCoursDto;
    }

    public SalleCoursDto getSalleDto() {
        return salleDto;
    }

    public void setSalleDto(SalleCoursDto salleCoursDto) {
        this.salleDto = salleCoursDto;
    }

    public Set<ParticipeACoursDto> getParticipeADto() {
        return participeADto;
    }

    public void setParticipeADto(Set<ParticipeACoursDto> participeADto) {
        this.participeADto = participeADto;
    }

    @Override
    public boolean equals(Object obj) {
        // Si c'est exactement la même instance en mémoire, c'est le même objet (cas des DTO temporaires qu'on manipule dans l'algo)
        if (this == obj) return true;

        // Si c'est null ou d'une autre classe, c'est faux
        if (obj == null || getClass() != obj.getClass()) return false;

        CoursDto coursDto = (CoursDto) obj;

        // Si l'un des deux (ou les deux) DTO n'a pas d'ID (null), ils sont forcément différents.
        // (S'ils étaient la même instance, le point 1 aurait déjà renvoyé true)
        if (this.idCours == null || coursDto.idCours == null) {
            return false;
        }

        // Si les deux ont un ID, on les compare normalement
        return Objects.equals(idCours, coursDto.idCours);
    }

    @Override
    public int hashCode() {
        // Si l'ID est null, on utilise l'adresse mémoire (super.hashCode())
        return idCours != null ? Objects.hashCode(idCours) : super.hashCode();
    }
}
