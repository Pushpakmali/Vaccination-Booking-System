package com.example.vaccineManagementSystem.Service;

import com.example.vaccineManagementSystem.Dtos.RequestDtos.AppointmentRequestDTO;
import com.example.vaccineManagementSystem.Exceptions.DoctorNotFoundException;
import com.example.vaccineManagementSystem.Exceptions.UserNotFoundException;
import com.example.vaccineManagementSystem.Models.Appointment;
import com.example.vaccineManagementSystem.Models.Doctor;
import com.example.vaccineManagementSystem.Models.User;
import com.example.vaccineManagementSystem.Repository.AppointmentRepository;
import com.example.vaccineManagementSystem.Repository.DoctorRepository;
import com.example.vaccineManagementSystem.Repository.UserRepository;
import com.sun.source.doctree.DocCommentTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private UserRepository userRepository;

    public String bookAppointment(AppointmentRequestDTO appointmentRequestDTO) throws DoctorNotFoundException, UserNotFoundException {
        Optional<Doctor> doctorOptional = doctorRepository.findById(appointmentRequestDTO.getDocId());
        Optional<User> userOptional = userRepository.findById(appointmentRequestDTO.getUserId());

        if(doctorOptional.isEmpty()){
            throw new DoctorNotFoundException("Doctor Not Found");
        }

        if(userOptional.isEmpty()){
            throw new UserNotFoundException("User Not found");
        }

        Doctor doctor = doctorOptional.get();
        User user = userOptional.get();

        Appointment appointment = new Appointment();

        //Creating object and setting its attribute
        appointment.setUser(user);
        appointment.setDoctor(doctor);
        appointment.setAppointmentDate(appointmentRequestDTO.getAppointmentData());
        appointment.setAppointmentTime(appointmentRequestDTO.getAppointmentTime());

        //Saving before so that i can get the primary key of the appointment table...
        appointment = appointmentRepository.save(appointment);

        doctor.getAppointmentList().add(appointment);
        user.getAppointmentList().add(appointment);

        doctorRepository.save(doctor);
        userRepository.save(user);

        return "Appointment Booked Successfully";
    }
}
