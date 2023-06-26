package com.example.vaccineManagementSystem.Service;

import com.example.vaccineManagementSystem.Models.Dose;
import com.example.vaccineManagementSystem.Models.User;
import com.example.vaccineManagementSystem.Repository.DoseRepository;
import com.example.vaccineManagementSystem.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoseService {
    @Autowired
    private DoseRepository doseRepository;
    @Autowired
    private UserRepository userRepository;

    public String giveDose(String doseId, Integer userId){
        User user = userRepository.findById(userId).get();

        //An entity of that model has been created
        //This entity will be saved in the database
        Dose dose = new Dose();

        //setting its attributes
        dose.setDoseId(doseId);
        dose.setUser(user);

        //This is for unidirectional mapping
        //save the entity
        //doseRepository.save(dose);


        //This is for bidirectional mapping
        //Setting the child reference object in parent
        user.setDose(dose);

        userRepository.save(user);               //child will automatically get set by cascading effect


        return "Dose given to user Successfully";
    }
}