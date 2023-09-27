package com.pragma.powerup.infrastructure.out.jpa.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,length = 60)
    private String name;
    @Column(nullable = false,length = 60)
    private String lastName;
    @Column(nullable = false,length = 30, unique = true)
    private String documentNumber;
    @Column(nullable = false,length = 13, unique = true)
    private String phone;
    private LocalDate birthdate;
    @Column(nullable = false,length = 80,unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_role")
    private RoleEntity idRole;
}
