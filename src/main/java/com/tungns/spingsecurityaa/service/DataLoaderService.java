package com.tungns.spingsecurityaa.service;

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

import java.util.Collections;

@Component
@AllArgsConstructor
public class DataLoaderService {

    private final AuthorityRepository authorityRepository;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    @PostConstruct
    @Transactional
    public void createData() {
        var authority = new AuthorityEntity().setCode("READ_SMS").setName("Read SMS");
        authorityRepository.save(authority);
        var role = new RoleEntity().setName("Admin").setAuthorities(Collections.singletonList(authority));
        roleRepository.save(role);
        var user = new UserEntity().setUsername("TungNS").setPassword("123456").setRole(role);
        userRepository.save(user);
    }
}

