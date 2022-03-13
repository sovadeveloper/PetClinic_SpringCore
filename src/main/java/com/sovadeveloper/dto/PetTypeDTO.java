package com.sovadeveloper.dto;

import com.sovadeveloper.entities.PetTypeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PetTypeDTO {
    private Long id;
    private String name;

    public static PetTypeDTO toModel(PetTypeEntity petTypeEntity){
        PetTypeDTO petTypeDTO = new PetTypeDTO();
        petTypeDTO.setId(petTypeEntity.getId());
        petTypeDTO.setName(petTypeEntity.getName());
        return petTypeDTO;
    }
}
