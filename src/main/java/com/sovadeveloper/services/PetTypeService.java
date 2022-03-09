package com.sovadeveloper.services;

import com.sovadeveloper.dao.PetTypeDao;
import com.sovadeveloper.entities.PetTypeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("petTypeService")
public class PetTypeService {
    private final PetTypeDao petTypeDao;

    @Autowired
    public PetTypeService(@Qualifier("petTypeDaoImpl") PetTypeDao petTypeDao) {
        this.petTypeDao = petTypeDao;
    }

    public void add(PetTypeEntity petTypeEntity){
        petTypeDao.save(petTypeEntity);
    }
}
