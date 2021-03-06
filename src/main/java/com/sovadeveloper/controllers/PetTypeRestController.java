package com.sovadeveloper.controllers;

import com.sovadeveloper.dto.PetTypeDTO;
import com.sovadeveloper.entities.PetTypeEntity;
import com.sovadeveloper.services.PetTypeService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/petType")
public class PetTypeRestController {
    private final PetTypeService petTypeService;

    @Autowired
    public PetTypeRestController(PetTypeService petTypeService) {
        this.petTypeService = petTypeService;
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'CLIENT')")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAll(){
        try {
            return ResponseEntity.ok(petTypeService.getAll());
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e);
        }
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'CLIENT')")
    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Long id){
        try {
            return ResponseEntity.ok(petTypeService.getById(id));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e);
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity create(@RequestBody PetTypeEntity petTypeEntity){
        try {
            return ResponseEntity.ok(petTypeService.create(petTypeEntity));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e);
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity edit(@PathVariable Long id, @RequestBody PetTypeEntity petTypeEntity){
        try {
            return ResponseEntity.ok(petTypeService.edit(id, petTypeEntity));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e);
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        try {
            return ResponseEntity.ok(petTypeService.delete(id));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e);
        }
    }
}
