package com.tungns.spingsecurityaa.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Entity
@Table(name = "TEST_AUTHORITY")
@Setter
@Getter
@Accessors(chain = true)
public class AuthorityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;
    private String code;
    private String name;
    @ManyToMany(mappedBy = "authorities")
    @Setter(AccessLevel.NONE)
    private List<RoleEntity> roles;
}
