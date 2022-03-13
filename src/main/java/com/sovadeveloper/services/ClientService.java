package com.sovadeveloper.services;

import com.sovadeveloper.dto.ClientDTO;
import com.sovadeveloper.entities.ClientEntity;

import java.util.List;

public interface ClientService {
    ClientDTO create(ClientEntity clientEntity) throws Exception;
    ClientDTO getById(Long id) throws Exception;
    ClientDTO edit(Long id, ClientEntity clientEntityUpdated) throws Exception;
    Long delete(Long id) throws Exception;
    List<ClientDTO> getAll() throws Exception;
}
