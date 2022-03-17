package com.sovadeveloper.repositories;

import com.sovadeveloper.entities.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepo extends JpaRepository<ClientEntity, Long> {
    ClientEntity findByPhone(String phone);
    ClientEntity findClientEntityByUsername(String name);
}
