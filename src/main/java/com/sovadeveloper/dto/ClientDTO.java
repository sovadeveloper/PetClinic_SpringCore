package com.sovadeveloper.dto;

import com.sovadeveloper.entities.ClientEntity;
import com.sovadeveloper.entities.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class ClientDTO {
    private Long id;
    private String name;
    private String phone;
    private Set<Role> roles;

    public static ClientDTO toModel(ClientEntity clientEntity){
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setId(clientEntity.getId());
        clientDTO.setName(clientEntity.getUsername());
        clientDTO.setPhone(clientEntity.getPhone());
        clientDTO.setRoles(clientEntity.getRoles());
        return clientDTO;
    }
}
