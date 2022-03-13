package com.sovadeveloper.repositories;

import com.sovadeveloper.entities.NoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepo extends JpaRepository<NoteEntity, Long> {
}
