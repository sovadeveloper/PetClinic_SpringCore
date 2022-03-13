package com.sovadeveloper.services;

import com.sovadeveloper.dto.PetDTO;
import com.sovadeveloper.entities.PetEntity;

import java.util.List;

public interface PetService {
    PetDTO create(PetEntity petEntity) throws Exception;
    PetDTO getById(Long id) throws Exception;
    PetDTO edit(Long id, PetEntity petEntityUpdated) throws Exception;
    Long delete(Long id) throws Exception;
    List<PetDTO> getAll() throws Exception;
}
