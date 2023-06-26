package com.example.vaccineManagementSystem.Service;

import com.example.vaccineManagementSystem.Dtos.RequestDtos.UpdateEmailDto;
import com.example.vaccineManagementSystem.Models.Dose;
import com.example.vaccineManagementSystem.Models.User;
import com.example.vaccineManagementSystem.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User addUser(User user){
        return userRepository.save(user);
    }

    public Date getVaccinationDate(Integer userId){
        User user = userRepository.findById(userId).get();

        Dose dose = user.getDose();
        return dose.getVaccinationDate();
    }

    public String updateEmail(UpdateEmailDto updateEmailDto){

        int userId = updateEmailDto.getUserId();

        User user = userRepository.findById(userId).get();

        //Modify the entity with new parameters

        user.setEmail(updateEmailDto.getEmail());

        userRepository.save(user);

        return "Email has been modified";
    }

    public User getUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return user;
    }
}
