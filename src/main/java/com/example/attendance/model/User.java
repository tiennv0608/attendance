package com.example.attendance.model;

import lombok.*;

import javax.persistence.*;

import com.example.attendance.constants.Constant;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String username;

    private String password;

    private String firstname;

    private String lastname;

    private int age;

    private String address;

    private Constant.RoleName role;
}
