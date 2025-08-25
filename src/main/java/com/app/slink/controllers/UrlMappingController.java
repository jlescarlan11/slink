package com.app.slink.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.slink.dtos.UrlMappingDTO;
import com.app.slink.entities.User;
import com.app.slink.service.UrlMappingService;
import com.app.slink.service.UserService;

import lombok.AllArgsConstructor;


@RestController
@RequestMapping("/api/urls")
@AllArgsConstructor
public class UrlMappingController {

    private final UrlMappingService urlMappingService;
    private final UserService userService;

    @PostMapping("/shorten")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<UrlMappingDTO> createShortUrl(@RequestBody Map<String, String> request, Principal principal     ) {
        
       String originalUrl = request.get("originalUrl");
       User user = userService.findByUsername(principal.getName());

       UrlMappingDTO urlMappingDTO = urlMappingService.createShortUrl(originalUrl, user);

       return ResponseEntity.ok(urlMappingDTO);

    }

    @GetMapping("/myurls")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<UrlMappingDTO>> getUserUrls(Principal principal) {
        
       User user = userService.findByUsername(principal.getName());
       List<UrlMappingDTO> urls = urlMappingService.getUrlsByUser(user);
       return ResponseEntity.ok(urls);

    }
    
}
