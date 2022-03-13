package com.sovadeveloper.repositories;

import com.sovadeveloper.entities.DoctorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepo extends JpaRepository<DoctorEntity, Long> {
}
