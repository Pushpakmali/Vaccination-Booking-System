package com.example.vaccineManagementSystem.Repository;

import com.example.vaccineManagementSystem.Models.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
}
