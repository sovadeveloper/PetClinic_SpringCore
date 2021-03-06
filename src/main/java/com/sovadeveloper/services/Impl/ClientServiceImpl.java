package com.sovadeveloper.services.Impl;

import com.sovadeveloper.dto.ClientDTO;
import com.sovadeveloper.entities.ClientEntity;
import com.sovadeveloper.entities.Role;
import com.sovadeveloper.repositories.ClientRepo;
import com.sovadeveloper.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ClientServiceImpl implements ClientService, UserDetailsService {
    private final ClientRepo clientRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ClientServiceImpl(ClientRepo clientRepo, PasswordEncoder passwordEncoder) {
        this.clientRepo = clientRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override
    public ClientDTO create(ClientEntity clientEntity) throws Exception {
        if(clientEntity.getPassword().length() < 1){
            throw new Exception("Пароль не может быть пустым");
        }
        if(clientEntity.getPhone().length() != 11){
            throw new Exception("Номер введен некорректно");
        }
        ClientEntity clientEntityCheckPhone = clientRepo.findByPhone(clientEntity.getPhone());
        if(clientEntityCheckPhone != null && clientEntityCheckPhone.getPhone().equals(clientEntity.getPhone())){
            throw new Exception("Пользователь с данным номером уже существует");
        }
        Set<Role> roles = new HashSet<>();
        roles.add(Role.CLIENT);
        clientEntity.setRoles(roles);
        clientEntity.setPassword(passwordEncoder.encode(clientEntity.getPassword()));
        return ClientDTO.toModel(clientRepo.save(clientEntity));
    }

    @Transactional
    @Override
    public ClientDTO getById(Long id) throws Exception {
        return ClientDTO.toModel(clientRepo.findById(id)
                .orElseThrow(() -> new Exception("Такого пользователя не существует")));
    }

    @Transactional
    @Override
    public ClientDTO edit(Long id, ClientEntity clientEntityUpdated) throws Exception {
        if(clientEntityUpdated.getPhone().length() != 11){
            throw new Exception("Номер введен некорректно");
        }
        ClientEntity clientEntity = clientRepo.findById(id)
                .orElseThrow(() -> new Exception("Такого пользователя не существует"));
        ClientEntity clientEntityCheckPhone = clientRepo.findByPhone(clientEntityUpdated.getPhone());
        if(clientEntityCheckPhone != null && clientEntityCheckPhone.getPhone().equals(clientEntity.getPhone())
                && !clientEntityCheckPhone.getId().equals(clientEntity.getId())){
            throw new Exception("Пользователь с данным номером уже существует");
        }
        clientEntity.setUsername(clientEntityUpdated.getUsername());
        clientEntity.setPhone(clientEntityUpdated.getPhone());
        return ClientDTO.toModel(clientRepo.save(clientEntity));
    }

    @Transactional
    @Override
    public Long delete(Long id) throws Exception {
        ClientEntity clientEntity = clientRepo.findById(id)
                .orElseThrow(() -> new Exception("Такого пользователя не существует"));
        clientRepo.delete(clientEntity);
        return id;
    }

    @Transactional
    @Override
    public List<ClientDTO> getAll() throws Exception {
        List<ClientEntity> clientEntities = clientRepo.findAll();
        List<ClientDTO> clientDTOS = new ArrayList<>();
        clientEntities.forEach(cl -> clientDTOS.add(ClientDTO.toModel(cl)));
        return clientDTOS;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return clientRepo.findClientEntityByUsername(username);
    }
}
