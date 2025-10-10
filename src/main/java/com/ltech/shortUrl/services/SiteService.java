package com.ltech.shortUrl.services;


import java.util.List;

import org.springframework.stereotype.Service;

import com.ltech.shortUrl.models.SiteModel;
import com.ltech.shortUrl.repositories.SiteRepositorie;

@Service
public class SiteService {
    
    private final SiteRepositorie repositorie;

    public SiteService(SiteRepositorie repositorie){
        this.repositorie = repositorie;
    }

    public List<SiteModel> getAll(){
        return this.repositorie.findAll();
    }
    public String getUrlOriginal(String shortUrl){
        return repositorie.findByUrlEncurtada(shortUrl)
        .map(SiteModel:: getUrlOriginal)
        .orElse(null);
    }
    public SiteModel createUrl(String urlOriginal){
        SiteModel site = new SiteModel(urlOriginal);
        return repositorie.save(site);
    }
    public void deleteById(Long id){
        repositorie.deleteById(id);
    }
}
