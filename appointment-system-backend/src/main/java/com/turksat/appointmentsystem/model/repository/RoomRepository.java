package com.turksat.appointmentsystem.model.repository;

import com.turksat.appointmentsystem.model.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room,Long> {
}
