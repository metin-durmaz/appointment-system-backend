package com.turksat.appointmentsystem.service;

import com.turksat.appointmentsystem.model.dto.AppointmentDto;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public interface AppointmentService {
    AppointmentDto save(AppointmentDto appointmentDto) throws MessagingException, UnsupportedEncodingException;
    AppointmentDto getById(Long id);
    Boolean delete(Long id);
    AppointmentDto update(Long id , AppointmentDto appointmentDto);

}
