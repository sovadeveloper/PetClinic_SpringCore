package com.sovadeveloper.services.Impl;

import com.sovadeveloper.dto.NoteDTO;
import com.sovadeveloper.entities.NoteEntity;
import com.sovadeveloper.repositories.DoctorRepo;
import com.sovadeveloper.repositories.NoteRepo;
import com.sovadeveloper.repositories.PetRepo;
import com.sovadeveloper.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {
    private final NoteRepo noteRepo;
    private final PetRepo petRepo;
    private final DoctorRepo doctorRepo;

    @Autowired
    public NoteServiceImpl(NoteRepo noteRepo, PetRepo petRepo, DoctorRepo doctorRepo) {
        this.noteRepo = noteRepo;
        this.petRepo = petRepo;
        this.doctorRepo = doctorRepo;
    }

    @Transactional
    @Override
    public NoteDTO create(NoteEntity noteEntity) throws Exception {
        petRepo.findById(noteEntity.getPet().getId())
                .orElseThrow(() -> new Exception("Такого питомца не существует"));
        doctorRepo.findById(noteEntity.getDoctor().getId())
                .orElseThrow(() -> new Exception("Такого доктора не существует"));
        return NoteDTO.toModel(noteRepo.save(noteEntity));
    }

    @Transactional
    @Override
    public NoteDTO getById(Long id) throws Exception {
        return NoteDTO.toModel(noteRepo.findById(id)
                .orElseThrow(() -> new Exception("Такой записи не существует")));
    }

    @Transactional
    @Override
    public NoteDTO edit(Long id, NoteEntity noteEntityUpdated) throws Exception {
        NoteEntity noteEntity = noteRepo.findById(id)
                .orElseThrow(() -> new Exception("Такой записи не существует"));
        petRepo.findById(noteEntityUpdated.getPet().getId())
                .orElseThrow(() -> new Exception("Такого питомца не существует"));
        doctorRepo.findById(noteEntityUpdated.getDoctor().getId())
                .orElseThrow(() -> new Exception("Такого доктора не существует"));
        noteEntity.setTitle(noteEntityUpdated.getTitle());
        return NoteDTO.toModel(noteRepo.save(noteEntity));
    }

    @Transactional
    @Override
    public Long delete(Long id) throws Exception {
        NoteEntity noteEntity = noteRepo.findById(id)
                .orElseThrow(() -> new Exception("Такой записи не существует"));
        noteRepo.delete(noteEntity);
        return id;
    }

    @Transactional
    @Override
    public List<NoteDTO> getAll() throws Exception {
        List<NoteEntity> noteEntities = noteRepo.findAll();
        List<NoteDTO> noteDTOS = new ArrayList<>();
        for(NoteEntity noteEntity: noteEntities){
            noteDTOS.add(NoteDTO.toModel(noteEntity));
        }
        return noteDTOS;
    }
}
