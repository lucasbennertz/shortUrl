package com.ltech.shortUrl.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SiteRepositorie extends JpaRepository<String, Long>{
    
}
