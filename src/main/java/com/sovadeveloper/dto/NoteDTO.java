package com.sovadeveloper.dto;

import com.sovadeveloper.entities.NoteEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NoteDTO {
    private Long id;
    private String title;
    private PetDTO pet;
    private DoctorDTO doctor;

    public static NoteDTO toModel(NoteEntity noteEntity){
        NoteDTO noteDTO = new NoteDTO();
        noteDTO.setId(noteEntity.getId());
        noteDTO.setTitle(noteEntity.getTitle());
        noteDTO.setPet(PetDTO.toModel(noteEntity.getPet()));
        noteDTO.setDoctor(DoctorDTO.toModel(noteEntity.getDoctor()));
        return noteDTO;
    }
}
