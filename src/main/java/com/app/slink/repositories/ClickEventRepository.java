package com.app.slink.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.slink.entities.ClickEvent;
import com.app.slink.entities.UrlMapping;


public interface ClickEventRepository extends  JpaRepository<ClickEvent, Long>{
    List<ClickEvent> findByUrlMappingAndClickDateBetween(UrlMapping urlMapping, LocalDateTime startDate, LocalDateTime endDate);
    List<ClickEvent> findByUrlMappingInAndClickDateBetween(List<UrlMapping> urlMapping, LocalDateTime startDate, LocalDateTime endDate);

}
