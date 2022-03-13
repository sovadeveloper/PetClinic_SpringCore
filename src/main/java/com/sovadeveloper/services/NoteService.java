package com.sovadeveloper.services;

import com.sovadeveloper.dto.NoteDTO;
import com.sovadeveloper.entities.NoteEntity;

import java.util.List;

public interface NoteService {
    NoteDTO create(NoteEntity noteEntity) throws Exception;
    NoteDTO getById(Long id) throws Exception;
    NoteDTO edit(Long id, NoteEntity noteEntityUpdated) throws Exception;
    Long delete(Long id) throws Exception;
    List<NoteDTO> getAll() throws Exception;
}
