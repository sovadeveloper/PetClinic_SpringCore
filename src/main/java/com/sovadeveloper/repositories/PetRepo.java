package com.sovadeveloper.repositories;

import com.sovadeveloper.entities.PetEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepo extends JpaRepository<PetEntity, Long> {
}
