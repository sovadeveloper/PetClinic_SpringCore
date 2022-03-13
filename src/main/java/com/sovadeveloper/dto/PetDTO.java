package com.sovadeveloper.dto;

import com.sovadeveloper.entities.PetEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PetDTO {
    private Long id;
    private String name;
    private ClientDTO client;
    private PetTypeDTO petType;

    public static PetDTO toModel(PetEntity petEntity){
        PetDTO petDTO = new PetDTO();
        petDTO.setId(petEntity.getId());
        petDTO.setName(petEntity.getName());
        petDTO.setClient(ClientDTO.toModel(petEntity.getClient()));
        petDTO.setPetType(PetTypeDTO.toModel(petEntity.getPetType()));
        return petDTO;
    }
}
