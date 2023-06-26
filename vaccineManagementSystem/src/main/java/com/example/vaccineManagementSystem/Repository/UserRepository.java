package com.example.vaccineManagementSystem.Repository;

import com.example.vaccineManagementSystem.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

    //Just define the functions
    //prebuilt functions + defined
    User findByEmail(String email);
}
