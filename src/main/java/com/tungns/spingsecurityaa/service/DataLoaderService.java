package com.tungns.spingsecurityaa.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tungns.spingsecurityaa.entity.AuthorityEntity;
import com.tungns.spingsecurityaa.entity.RoleEntity;
import com.tungns.spingsecurityaa.entity.UserEntity;
import com.tungns.spingsecurityaa.repository.AuthorityRepository;
import com.tungns.spingsecurityaa.repository.RoleRepository;
import com.tungns.spingsecurityaa.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.Collections;

@Component
@AllArgsConstructor
public class DataLoaderService {

    private final AuthorityRepository authorityRepository;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    RequestMappingHandlerMapping requestMappingHandlerMapping;
    ObjectMapper objectMapper;


    @PostConstruct
    @Transactional
    public void createData() throws JsonProcessingException {
//        var handlerMethods = requestMappingHandlerMapping.getHandlerMethods();
//        System.out.println(objectMapper.writeValueAsString(handlerMethods.values()));
        var authority = new AuthorityEntity().setCode("SUPER").setName("Read SMS");
        authorityRepository.save(authority);
        var role = new RoleEntity().setName("Admin").setAuthorities(Collections.singletonList(authority));
        roleRepository.save(role);
        var user = new UserEntity().setUsername("TungNS").setPassword("123456").setRole(role);
        userRepository.save(user);

        var authority2 = new AuthorityEntity().setCode("TEST").setName("Test");
        authorityRepository.save(authority2);
        var role2 = new RoleEntity().setName("Admin-Test").setAuthorities(Collections.singletonList(authority2));
        roleRepository.save(role2);
        var user2 = new UserEntity().setUsername("TungNS2").setPassword("123456").setRole(role2);
        userRepository.save(user2);
    }
}

