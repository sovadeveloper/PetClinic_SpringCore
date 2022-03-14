package com.sovadeveloper.services.Impl;

import com.sovadeveloper.dto.ClientDTO;
import com.sovadeveloper.dto.PetDTO;
import com.sovadeveloper.entities.ClientEntity;
import com.sovadeveloper.entities.PetEntity;
import com.sovadeveloper.repositories.ClientRepo;
import com.sovadeveloper.repositories.PetRepo;
import com.sovadeveloper.repositories.PetTypeRepo;
import com.sovadeveloper.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PetServiceImpl implements PetService {
    private final PetRepo petRepo;
    private final ClientRepo clientRepo;
    private final PetTypeRepo petTypeRepo;

    @Autowired
    public PetServiceImpl(PetRepo petRepo, ClientRepo clientRepo, PetTypeRepo petTypeRepo) {
        this.petRepo = petRepo;
        this.clientRepo = clientRepo;
        this.petTypeRepo = petTypeRepo;
    }

    @Transactional
    @Override
    public PetDTO create(PetEntity petEntity) throws Exception {
        clientRepo.findById(petEntity.getClient().getId())
                .orElseThrow(() -> new Exception("Такого клиента не существует"));
        petTypeRepo.findById(petEntity.getPetType().getId())
                .orElseThrow(() -> new Exception("Такого типа животного не существует"));
        return PetDTO.toModel(petRepo.save(petEntity));
    }

    @Transactional
    @Override
    public PetDTO getById(Long id) throws Exception {
        return PetDTO.toModel(petRepo.findById(id)
                .orElseThrow(() -> new Exception("Такого питомца не существует")));
    }

    @Transactional
    @Override
    public PetDTO edit(Long id, PetEntity petEntityUpdated) throws Exception {
        PetEntity petEntity = petRepo.findById(id)
                .orElseThrow(() -> new Exception("Такого питомца не существует"));
        clientRepo.findById(petEntityUpdated.getClient().getId())
                .orElseThrow(() -> new Exception("Такого клиента не существует"));
        petTypeRepo.findById(petEntity.getPetType().getId())
                .orElseThrow(() -> new Exception("Такого типа животного не существует"));
        petEntity.setName(petEntityUpdated.getName());
        return PetDTO.toModel(petRepo.save(petEntity));
    }

    @Transactional
    @Override
    public Long delete(Long id) throws Exception {
        PetEntity petEntity = petRepo.findById(id)
                .orElseThrow(() -> new Exception("Такого питомца не существует"));
        petRepo.delete(petEntity);
        return id;
    }

    @Transactional
    @Override
    public List<PetDTO> getAll() throws Exception {
        List<PetEntity> petEntities = petRepo.findAll();
        List<PetDTO> petDTOS = new ArrayList<>();
        for(PetEntity petEntity: petEntities){
            petDTOS.add(PetDTO.toModel(petEntity));
        }
        return petDTOS;
    }

    @Override
    public List<PetDTO> getAllByClient(ClientDTO clientDTO) throws Exception {
        List<PetEntity> petEntities = petRepo.findAllByClient(clientRepo.getById(clientDTO.getId()));
        List<PetDTO> petDTOS = new ArrayList<>();
        for(PetEntity petEntity: petEntities){
            petDTOS.add(PetDTO.toModel(petEntity));
        }
        return petDTOS;
    }
}
