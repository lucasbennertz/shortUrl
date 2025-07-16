package com.ltech.shortUrl.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ltech.shortUrl.models.SiteModel;

@Repository
public interface SiteRepositorie extends JpaRepository<SiteModel, Long>{
    Optional<SiteModel> findByUrlOriginal(String urlEncurtada);
}
