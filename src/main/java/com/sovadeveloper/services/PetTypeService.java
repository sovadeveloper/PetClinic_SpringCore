package com.sovadeveloper.services;

import com.sovadeveloper.dto.PetTypeDTO;
import com.sovadeveloper.entities.PetTypeEntity;

import java.util.List;

public interface PetTypeService {
    PetTypeDTO create(PetTypeEntity petTypeEntity) throws Exception;
    PetTypeDTO getById(Long id) throws Exception;
    PetTypeDTO edit(Long id, PetTypeEntity petTypeEntityUpdated) throws Exception;
    Long delete(Long id) throws Exception;
    List<PetTypeDTO> getAll() throws Exception;
}
