package com.example.vaccineManagementSystem.Service;

import com.example.vaccineManagementSystem.Exceptions.VaccinationAddressNotFoundException;
import com.example.vaccineManagementSystem.Models.VaccinationCenter;
import com.example.vaccineManagementSystem.Repository.VaccinationCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VaccinationCenterService {
    @Autowired
    private VaccinationCenterRepository vaccinationCenterRepository;

    public String addCenter(VaccinationCenter vaccinationCenter) throws VaccinationAddressNotFoundException{
        if(vaccinationCenter.getAddress() == null){
            throw new VaccinationAddressNotFoundException("Address Not found");
        }
        vaccinationCenterRepository.save(vaccinationCenter);
        return "Center Added Successfully";
    }


}
