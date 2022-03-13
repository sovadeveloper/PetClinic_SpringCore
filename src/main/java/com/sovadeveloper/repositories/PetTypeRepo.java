package com.sovadeveloper.repositories;

import com.sovadeveloper.entities.PetTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PetTypeRepo extends JpaRepository<PetTypeEntity, Long> {
}
