package com.ltech.shortUrl.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ltech.shortUrl.models.SiteModel;
import com.ltech.shortUrl.services.SiteService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
@RequestMapping("/")
public class SiteController {

    private final SiteService siteService;
    
    public SiteController(SiteService siteService) {
        this.siteService = siteService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<SiteModel>> getAll() {
        return ResponseEntity.ok(siteService.getAll());
    }

    @PostMapping("/create")
    public ResponseEntity<SiteModel> createUrl(@RequestBody String urlOriginal) {
        SiteModel createdSite = siteService.createUrl(urlOriginal);
        return ResponseEntity.ok(createdSite);
    }
    
    @GetMapping("/getOriginalUrl")
    public ResponseEntity<String> getUrlOriginal(@RequestParam String shortUrl) {
        String originalUrl = siteService.getUrlOriginal(shortUrl);
        if (originalUrl != null) {
            return ResponseEntity.ok(originalUrl);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
}