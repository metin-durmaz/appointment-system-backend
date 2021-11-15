package com.turksat.appointmentsystem.service.implementations;

import com.turksat.appointmentsystem.model.dto.AppointmentDto;
import com.turksat.appointmentsystem.model.dto.PeriodDto;
import com.turksat.appointmentsystem.model.dto.RoomDto;
import com.turksat.appointmentsystem.model.entity.Appointment;
import com.turksat.appointmentsystem.model.entity.Period;
import com.turksat.appointmentsystem.model.entity.Room;
import com.turksat.appointmentsystem.model.repository.RoomRepository;
import com.turksat.appointmentsystem.service.RoomService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final ModelMapper modelMapper;

    public RoomServiceImpl(RoomRepository roomRepository, ModelMapper modelMapper) {
        this.roomRepository = roomRepository;
        this.modelMapper = modelMapper;
    }

    /**
     * It saves user to database.
     *
     * @param roomDto
     * @return
     */
    // CREATE
    @Override
    public RoomDto save(RoomDto roomDto) {
        Room room = this.roomRepository.save(modelMapper.map(roomDto,Room.class));
        return modelMapper.map(room,RoomDto.class);
    }

    /**
     * It retrieves a room from database with the room ID.
     *
     * @param id
     * @return
     */
    // READ
    @Override
    public RoomDto getById(Long id) {
        RoomDto roomDto=modelMapper.map(this.roomRepository.getById(id),RoomDto.class);

        if(roomDto==null)
            throw new IllegalArgumentException("There is no room with this id !");

        return roomDto;
    }

    /**
     * It updates a room in the database with the room ID.
     *
     * @param id
     * @param roomDto
     * @return
     */
    // UPDATE
    @Override
    public RoomDto update(Long id, RoomDto roomDto) {
        Room roomDb=roomRepository.getById(id);
        if(!roomRepository.existsById(id))
            throw new IllegalArgumentException("There is no room with this id");

        roomDb.setRoomStatus(roomDto.getRoomStatus());
        roomDb.setFreeDays(roomDto.getFreeDays());

        roomRepository.save(roomDb);

        return modelMapper.map(roomDb, RoomDto.class);
    }

    /**
     * It deletes a room from the database with the room ID.
     *
     * @param id
     * @return
     */
    // DELETE
    @Override
    public Boolean delete(Long id) {

        if(!this.roomRepository.existsById(id))
            throw new IllegalArgumentException("There is no room with this id !");

        this.roomRepository.delete(this.roomRepository.getById(id));

        return Boolean.TRUE;
    }
}
