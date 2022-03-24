package com.sovadeveloper.controllers;

import com.sovadeveloper.entities.PetEntity;
import com.sovadeveloper.services.PetService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pet")
public class PetRestController {
    private final PetService petService;

    @Autowired
    public PetRestController(PetService petService) {
        this.petService = petService;
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'CLIENT')")
    @GetMapping
    public ResponseEntity getAll(){
        try {
            return ResponseEntity.ok(petService.getAll());
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e);
        }
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'CLIENT')")
    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Long id){
        try {
            return ResponseEntity.ok(petService.getById(id));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e);
        }
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'CLIENT')")
    @PostMapping
    public ResponseEntity create(@RequestBody PetEntity petEntity){
        try {
            return ResponseEntity.ok(petService.create(petEntity));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e);
        }
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'CLIENT')")
    @PutMapping("/{id}")
    public ResponseEntity edit(@PathVariable Long id, @RequestBody PetEntity petEntity){
        try {
            return ResponseEntity.ok(petService.edit(id, petEntity));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e);
        }
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'CLIENT')")
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        try {
            return ResponseEntity.ok(petService.delete(id));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e);
        }
    }
}
