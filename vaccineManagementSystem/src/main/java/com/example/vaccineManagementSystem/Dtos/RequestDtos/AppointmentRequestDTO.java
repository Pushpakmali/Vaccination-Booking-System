package com.example.vaccineManagementSystem.Dtos.RequestDtos;

import lombok.Data;

import java.time.LocalTime;
import java.util.Date;

@Data
public class AppointmentRequestDTO {

    private int docId;
    private int userId;
    private Date appointmentData;
    private LocalTime appointmentTime;
}
