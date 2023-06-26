package com.example.vaccineManagementSystem.Models;

import com.example.vaccineManagementSystem.Enums.Gender;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)       //To generate unique id automatically
    private int userId;

    @Column(name = "user_name")                               //To give column custom name
    private String name;

    private int age;

    @Column(unique = true)                                 //This will make sure that only unique values are going into database
    private String email;                                  //If we pass duplicate value then it will throw error

    private String mobileNo;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    //Bidirectional Mapping Syntax
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Dose dose;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Appointment> appointmentList = new ArrayList<>();


}