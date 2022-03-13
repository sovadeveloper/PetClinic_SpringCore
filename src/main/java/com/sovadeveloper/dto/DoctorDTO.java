package com.sovadeveloper.dto;

import com.sovadeveloper.entities.DoctorEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DoctorDTO {
    private Long id;
    private String name;

    public static DoctorDTO toModel(DoctorEntity doctorEntity){
        DoctorDTO doctorDTO = new DoctorDTO();
        doctorDTO.setId(doctorEntity.getId());
        doctorDTO.setName(doctorEntity.getName());
        return doctorDTO;
    }
}
