package com.sovadeveloper.services.Impl;

import com.sovadeveloper.dto.DoctorDTO;
import com.sovadeveloper.entities.DoctorEntity;
import com.sovadeveloper.repositories.DoctorRepo;
import com.sovadeveloper.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepo doctorRepo;

    @Autowired
    public DoctorServiceImpl(DoctorRepo doctorRepo) {
        this.doctorRepo = doctorRepo;
    }

    @Transactional
    @Override
    public DoctorDTO create(DoctorEntity doctorEntity) throws Exception {
        return DoctorDTO.toModel(doctorRepo.save(doctorEntity));
    }

    @Transactional
    @Override
    public DoctorDTO getById(Long id) throws Exception {
        return DoctorDTO.toModel(doctorRepo.findById(id)
                .orElseThrow(() -> new Exception("Такого доктора не существует")));
    }

    @Transactional
    @Override
    public DoctorDTO edit(Long id, DoctorEntity doctorEntityUpdated) throws Exception {
        DoctorEntity doctorEntity = doctorRepo.findById(id)
                .orElseThrow(() -> new Exception("Такого доктора не существует"));
        doctorEntity.setName(doctorEntityUpdated.getName());
        return DoctorDTO.toModel(doctorRepo.save(doctorEntity));
    }

    @Transactional
    @Override
    public Long delete(Long id) throws Exception {
        DoctorEntity doctorEntity = doctorRepo.findById(id)
                .orElseThrow(() -> new Exception("Такого доктора не существует"));
        doctorRepo.delete(doctorEntity);
        return id;
    }

    @Transactional
    @Override
    public List<DoctorDTO> getAll() throws Exception {
        List<DoctorEntity> doctorEntities = doctorRepo.findAll();
        List<DoctorDTO> doctorDTOS = new ArrayList<>();
        for(DoctorEntity doctorEntity: doctorEntities){
            doctorDTOS.add(DoctorDTO.toModel(doctorEntity));
        }
        return doctorDTOS;
    }
}
