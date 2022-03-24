package com.sovadeveloper.services.Impl;

import com.sovadeveloper.dto.PetTypeDTO;
import com.sovadeveloper.entities.PetTypeEntity;
import com.sovadeveloper.repositories.PetTypeRepo;
import com.sovadeveloper.services.PetTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PetTypeServiceImpl implements PetTypeService {
    private final PetTypeRepo petTypeRepo;

    @Autowired
    public PetTypeServiceImpl(PetTypeRepo petTypeRepo) {
        this.petTypeRepo = petTypeRepo;
    }

    @Transactional
    @Override
    public PetTypeDTO create(PetTypeEntity petTypeEntity) throws Exception {
        return PetTypeDTO.toModel(petTypeRepo.save(petTypeEntity));
    }

    @Transactional
    @Override
    public PetTypeDTO getById(Long id) throws Exception {
        return PetTypeDTO.toModel(petTypeRepo.findById(id)
                .orElseThrow(() -> new Exception("Такого типа животного не существует")));
    }

    @Transactional
    @Override
    public PetTypeDTO edit(Long id, PetTypeEntity petTypeEntityUpdated) throws Exception {
        PetTypeEntity petTypeEntity = petTypeRepo.findById(id)
                .orElseThrow(() -> new Exception("Такого типа животного не существует"));
        petTypeEntity.setName(petTypeEntityUpdated.getName());
        return PetTypeDTO.toModel(petTypeRepo.save(petTypeEntity));
    }

    @Transactional
    @Override
    public Long delete(Long id) throws Exception {
        PetTypeEntity petTypeEntity = petTypeRepo.findById(id)
                .orElseThrow(() -> new Exception("Такого типа животного не существует"));
        petTypeRepo.delete(petTypeEntity);
        return id;
    }

    @Transactional
    @Override
    public List<PetTypeDTO> getAll() throws Exception {
        List<PetTypeEntity> petTypeEntities = petTypeRepo.findAll();
        List<PetTypeDTO> petTypeDTOS = new ArrayList<>();
        petTypeEntities.forEach(pt -> petTypeDTOS.add(PetTypeDTO.toModel(pt)));
        return petTypeDTOS;
    }
}
