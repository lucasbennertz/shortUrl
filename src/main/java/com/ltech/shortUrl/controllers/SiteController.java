package com.ltech.shortUrl.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ltech.shortUrl.models.SiteModel;
import com.ltech.shortUrl.services.SiteService;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
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
    public ResponseEntity<SiteModel> createUrl(@RequestBody SiteModel urlOriginal) {
        SiteModel createdSite = siteService.createUrl(urlOriginal.getUrlOriginal());
        return ResponseEntity.ok(createdSite);
    }
    @GetMapping("/getDados")
    public ResponseEntity<SiteModel> getDados(@RequestParam String shortUrl) {
        String originalUrl = siteService.getUrlOriginal(shortUrl);
        if (originalUrl != null) {
            SiteModel site = new SiteModel(originalUrl);
            site.setUrlEncurtada(shortUrl);
            return ResponseEntity.ok(site);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/getOriginalUrl")
    public void getUrlOriginal(@RequestParam String shortUrl, HttpServletResponse response) throws IOException {
        String originalUrl = siteService.getUrlOriginal(shortUrl);
        if (originalUrl != null) {
            response.sendRedirect(originalUrl); // redireciona de verdade
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "URL encurtada não encontrada");
        }
    }
    
}