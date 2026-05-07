package com.works.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;

    private String name;
    private String surname;

    @Column(unique = true)
    private String mail;
    private String phone;


    @ManyToMany
    private List<Address> addressList; //3. bir tablo olusturur

    @Embedded
    private IdentityInfo identityInfo;

}
