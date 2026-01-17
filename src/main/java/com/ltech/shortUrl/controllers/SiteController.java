package com.ltech.shortUrl.controllers;

import org.springframework.web.bind.annotation.*;

import com.ltech.shortUrl.Dto.SiteDTO;
import com.ltech.shortUrl.models.SiteModel;
import com.ltech.shortUrl.services.SiteService;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;


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
    public ResponseEntity<SiteModel> createUrl(@RequestBody SiteDTO siteDto) {
        SiteModel createdSite = siteService.createUrl(siteDto.getUrlOriginal());
        return ResponseEntity.ok(createdSite).;
    }
    @GetMapping("/getDados/{shortUrl}")
    public ResponseEntity<SiteModel> getDados(@PathVariable String shortUrl) {
        String originalUrl = siteService.getUrlOriginal(shortUrl);
        if (originalUrl != null) {
            SiteModel site = new SiteModel(originalUrl);
            site.setUrlEncurtada(shortUrl);
            return ResponseEntity.ok(site);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/{shortUrl}")
    public void getUrlOriginal(@PathVariable String shortUrl, HttpServletResponse response) throws IOException {
        String originalUrl = siteService.getUrlOriginal(shortUrl);
        if (originalUrl != null) {
            response.sendRedirect(originalUrl); // redireciona de verdade
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "URL encurtada não encontrada");
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        siteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
}