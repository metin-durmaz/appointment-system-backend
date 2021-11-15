package com.turksat.appointmentsystem.service.implementations;

import com.turksat.appointmentsystem.model.dto.AppointmentDto;
import com.turksat.appointmentsystem.model.entity.Appointment;
import com.turksat.appointmentsystem.model.entity.Room;
import com.turksat.appointmentsystem.model.entity.User;
import com.turksat.appointmentsystem.model.repository.AppointmentRepository;
import com.turksat.appointmentsystem.service.AppointmentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final ModelMapper modelMapper;
    private final MailServiceImpl mailServiceImpl;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, ModelMapper modelMapper, MailServiceImpl mailServiceImpl) {
        this.appointmentRepository = appointmentRepository;
        this.modelMapper = modelMapper;
        this.mailServiceImpl = mailServiceImpl;
    }

    /**
     * It saves user to database.
     *
     * @param appointmentDto
     * @return
     * @throws MessagingException
     * @throws UnsupportedEncodingException
     */
    // CREATE
    @Override
    public AppointmentDto save(AppointmentDto appointmentDto) throws MessagingException, UnsupportedEncodingException {
        Appointment appointment=this.appointmentRepository.save(modelMapper.map(appointmentDto, Appointment.class));
        mailServiceImpl.infoMail("metindurmaz98@gmail.com","www.google.com");
        return modelMapper.map(appointment,AppointmentDto.class);
    }

    /**
     * It retrieves a appointment from database with the appointment ID.
     *
     * @param id
     * @return
     */
    // READ
    @Override
    public AppointmentDto getById(Long id) {
        AppointmentDto appointmentDto=modelMapper.map(this.appointmentRepository.getById(id),AppointmentDto.class);

        if(appointmentDto==null)
            throw new IllegalArgumentException("There is no appointment with this id !");

        return appointmentDto;
    }

    /**
     * It updates a appointment in the database with the appointment ID.
     *
     * @param id
     * @param appointmentDto
     * @return
     */
    // UPDATE
    @Override
    public AppointmentDto update(Long id, AppointmentDto appointmentDto) {
        Appointment appointmentDb=appointmentRepository.getById(id);
        if(!appointmentRepository.existsById(id))
            throw new IllegalArgumentException("There is no appointment with this id!");

        appointmentDb.setStartDate(appointmentDto.getStartDate());
        appointmentDb.setFinishDate(appointmentDto.getFinishDate());
        appointmentDb.setDuration(appointmentDto.getDuration());
        appointmentDb.setAppStatus(appointmentDto.getAppStatus());
        appointmentDb.setOwner(modelMapper.map(appointmentDto.getOwner(), User.class));
        appointmentDb.setRoom(modelMapper.map(appointmentDto.getRoom(), Room.class));
        appointmentDb.setCapacity(appointmentDto.getCapacity());

        appointmentRepository.save(appointmentDb);

        return modelMapper.map(appointmentDb,AppointmentDto.class);
    }

    /**
     * It deletes a appointment from the database with the appointment ID.
     *
     * @param id
     * @return
     */
    // DELETE
    @Override
    public Boolean delete(Long id) {

        if(!this.appointmentRepository.existsById(id))
            throw new IllegalArgumentException("There is no appointment with this id !");

        this.appointmentRepository.delete(this.appointmentRepository.getById(id));

        return Boolean.TRUE;
    }
}
