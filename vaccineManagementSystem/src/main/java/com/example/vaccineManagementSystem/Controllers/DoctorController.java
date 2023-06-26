package com.example.vaccineManagementSystem.Controllers;

import com.example.vaccineManagementSystem.Dtos.RequestDtos.AssosiateDoctorDTO;
import com.example.vaccineManagementSystem.Models.Doctor;
import com.example.vaccineManagementSystem.Service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/doctor")
public class DoctorController {
    @Autowired
    DoctorService doctorService;

    @PostMapping("/addDoctor")
    public String addDoctor(@RequestBody Doctor doctor){
        try {
            return doctorService.addDoctor(doctor);
        }
        catch(Exception ex){
            return ex.getMessage();
        }
    }

    @PostMapping("/associateWithCenter")
    public ResponseEntity<String> associateWithCenter(@RequestBody AssosiateDoctorDTO assosiateDoctorDTO){

        try{
            String result = doctorService.associateWithCenter(assosiateDoctorDTO);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        catch(Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
