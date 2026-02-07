package com.univtime.informatique.dto.composanteDto;

import com.univtime.informatique.dto.ids.CMIdDto;

import java.util.Objects;

public class CMComposanteDto {
    private CMIdDto cmId;

    public CMComposanteDto() {

    }

    public CMComposanteDto(CMIdDto cmId) {
        this.cmId = cmId;
    }

    public CMIdDto getCmId() {
        return cmId;
    }

    public void setCmId(CMIdDto cmId) {
        this.cmId = cmId;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cmId);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CMComposanteDto that = (CMComposanteDto) o;
        return Objects.equals(cmId, that.cmId);
    }
}
