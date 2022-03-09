package com.sovadeveloper.dao;

import com.sovadeveloper.entities.PetTypeEntity;

import java.util.List;

public interface PetTypeDao {
    PetTypeEntity save(PetTypeEntity petTypeEntity);
    void update(PetTypeEntity petTypeEntity);
    void delete(Long id);
    PetTypeEntity getById(Long id);
    List<PetTypeEntity> getAll();
}
