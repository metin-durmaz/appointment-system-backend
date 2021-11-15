package com.turksat.appointmentsystem.model.repository;

import com.turksat.appointmentsystem.model.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment,Long> {

}
