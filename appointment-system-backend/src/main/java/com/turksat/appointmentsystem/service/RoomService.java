package com.turksat.appointmentsystem.service;

import com.turksat.appointmentsystem.model.dto.RoomDto;

public interface RoomService {
    RoomDto save(RoomDto roomDto);
    RoomDto getById(Long id);
    Boolean delete(Long id);
    RoomDto update(Long id , RoomDto roomDto);
}
