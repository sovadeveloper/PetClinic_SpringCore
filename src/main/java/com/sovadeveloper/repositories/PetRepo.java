package com.sovadeveloper.repositories;

import com.sovadeveloper.entities.ClientEntity;
import com.sovadeveloper.entities.PetEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PetRepo extends JpaRepository<PetEntity, Long> {
    List<PetEntity> findAllByClient(ClientEntity clientEntity);
}
