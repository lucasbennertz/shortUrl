package com.ltech.shortUrl.Dto.request;

import com.ltech.shortUrl.models.SiteModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SiteRequestDTO {
    private String urlOriginal;


    public SiteRequestDTO() {
    }

    public SiteRequestDTO(String urlOriginal) {
        this.urlOriginal = urlOriginal;
    }

    public SiteRequestDTO siteModelToDto(SiteModel siteModel){
        SiteRequestDTO siteRequestDTO = new SiteRequestDTO();
        siteRequestDTO.setUrlOriginal(siteModel.getUrlOriginal());
        return siteRequestDTO;
    }
}
