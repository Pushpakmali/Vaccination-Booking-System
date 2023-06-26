package com.example.vaccineManagementSystem.Service;

import com.example.vaccineManagementSystem.Dtos.RequestDtos.AssosiateDoctorDTO;
import com.example.vaccineManagementSystem.Exceptions.CenterNotFound;
import com.example.vaccineManagementSystem.Exceptions.DoctorNotFoundException;
import com.example.vaccineManagementSystem.Exceptions.EmailEmptyException;
import com.example.vaccineManagementSystem.Exceptions.DoctorAlreadyExisitsException;
import com.example.vaccineManagementSystem.Models.Doctor;
import com.example.vaccineManagementSystem.Models.VaccinationCenter;
import com.example.vaccineManagementSystem.Repository.DoctorRepository;
import com.example.vaccineManagementSystem.Repository.VaccinationCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private VaccinationCenterRepository vaccinationCenterRepository;

    public String addDoctor(Doctor doctor) throws DoctorAlreadyExisitsException, EmailEmptyException {

        if(doctor.getEmail().equals(null)){
            throw new EmailEmptyException("Email is Mandatory");
        }

        if(doctorRepository.findByEmail(doctor.getEmail()) != null){
            throw new DoctorAlreadyExisitsException("Doctor already Exists");
        }

        doctorRepository.save(doctor);
        return "Doctor added successfully";
    }

    public String associateWithCenter(AssosiateDoctorDTO assosiateDoctorDTO) throws DoctorNotFoundException, CenterNotFound {
        Integer docId = assosiateDoctorDTO.getDocId();
        Optional<Doctor> docOp = doctorRepository.findById(docId);

        if(docOp.isEmpty()){
            throw new DoctorNotFoundException("Doctor Not Found");
        }

        Integer centerId = assosiateDoctorDTO.getCenterId();
        Optional<VaccinationCenter> vaccinationCenterOptional = vaccinationCenterRepository.findById(centerId);

        if(vaccinationCenterOptional.isEmpty()){
            throw new CenterNotFound("Center Not Found");
        }

        Doctor doctor = docOp.get();
        VaccinationCenter center = vaccinationCenterOptional.get();

        doctor.setVaccinationCenter(center);   //Setting the foreign key

        //Set the bidirectional map
        //Adding the doctor to the list
        List<Doctor> doctorList = center.getDoctorList();
        doctorList.add(doctor);

        vaccinationCenterRepository.save(center);

        return "Doctor Has Been Associated To The Center";
    }
}
