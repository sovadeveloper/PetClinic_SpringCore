package com.sovadeveloper.dto;

import com.sovadeveloper.entities.ClientEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ClientDTO {
    private Long id;
    private String name;
    private String phone;

    public static ClientDTO toModel(ClientEntity clientEntity){
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setId(clientEntity.getId());
        clientDTO.setName(clientEntity.getName());
        clientDTO.setPhone(clientEntity.getPhone());
        return clientDTO;
    }
}
