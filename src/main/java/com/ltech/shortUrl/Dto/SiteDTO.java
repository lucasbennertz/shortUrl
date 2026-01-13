package com.ltech.shortUrl.Dto;

import com.ltech.shortUrl.models.SiteModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SiteDTO {
    private String urlOriginal;
    private String urlEncurtada;
    
    public SiteDTO() {
    }

    public SiteDTO(String urlOriginal) {
        this.urlOriginal = urlOriginal;
    }
    
    public SiteDTO siteModelToDto(SiteModel siteModel){
        SiteDTO siteDTO = new SiteDTO();
        siteDTO.setUrlOriginal(siteModel.getUrlOriginal());
        siteDTO.setUrlEncurtada(siteModel.getUrlEncurtada());
        return siteDTO;
    }
}
