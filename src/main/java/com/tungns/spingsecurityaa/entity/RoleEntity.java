package com.tungns.spingsecurityaa.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Entity
@Table(name = "TEST_ROLE")
@Setter
@Getter
@Accessors(chain = true)
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "role")
    List<UserEntity> users;

    @ManyToMany
    @JoinTable(name = "TEST_ROLE_TEST_AUTHORITY", joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "authority_code", referencedColumnName = "code"))
    List<AuthorityEntity> authorities;
}
