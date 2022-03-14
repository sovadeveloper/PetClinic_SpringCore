package com.sovadeveloper.repositories;

import com.sovadeveloper.entities.DoctorEntity;
import com.sovadeveloper.entities.NoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepo extends JpaRepository<NoteEntity, Long> {
    List<NoteEntity> findAllByDoctor(DoctorEntity doctorEntity);
}
