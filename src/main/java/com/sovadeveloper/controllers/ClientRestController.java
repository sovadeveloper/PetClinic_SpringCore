package com.sovadeveloper.controllers;

import com.sovadeveloper.entities.ClientEntity;
import com.sovadeveloper.entities.Role;
import com.sovadeveloper.services.ClientService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/client")
public class ClientRestController {
    private final ClientService clientService;

    @Autowired
    public ClientRestController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'CLIENT')")
    @GetMapping
    public ResponseEntity getAll(){
        try {
            return ResponseEntity.ok(clientService.getAll());
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e);
        }
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'CLIENT')")
    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Long id){
        try{
            return ResponseEntity.ok(clientService.getById(id));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e);
        }
    }

    @PostMapping
    public ResponseEntity create(@RequestBody ClientEntity clientEntity){
        try {
            return ResponseEntity.ok(clientService.create(clientEntity));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e);
        }
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'CLIENT')")
    @PutMapping("/{id}")
    public ResponseEntity edit(@PathVariable Long id, @RequestBody ClientEntity clientEntity){
        try {
            return ResponseEntity.ok(clientService.edit(id, clientEntity));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e);
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        try {
            return ResponseEntity.ok(clientService.delete(id));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e);
        }
    }
}
