package com.turksat.appointmentsystem.controller;

import com.turksat.appointmentsystem.model.dto.PeriodDto;
import com.turksat.appointmentsystem.model.dto.RoomDto;
import com.turksat.appointmentsystem.service.implementations.RoomServiceImpl;
import com.turksat.appointmentsystem.util.ApiPaths;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@CrossOrigin(origins = ApiPaths.LOCAL_CLIENT_BASE_PATH, maxAge = 3600)
@RequestMapping("/api/rooms")
@Api(value = "Room API")
public class RoomController {
    private final RoomServiceImpl roomServiceImpl;

    public RoomController(RoomServiceImpl roomServiceImpl) {
        this.roomServiceImpl = roomServiceImpl;
    }

    // CREATE
    @PostMapping
    @ApiOperation(value = "Create Operation", response = RoomDto.class)
    public ResponseEntity<RoomDto> save(@RequestBody RoomDto roomDto){
        return ResponseEntity.ok(this.roomServiceImpl.save(roomDto));
    }

    // READ
    @GetMapping("/{id}")
    @ApiOperation(value = "Read Operation", response = RoomDto.class)
    public ResponseEntity<RoomDto> getById(@PathVariable Long id){
        return ResponseEntity.ok(this.roomServiceImpl.getById(id));
    }

    // UPDATE
    @PutMapping("/{id}")
    @ApiOperation(value = "Update Operation", response = RoomDto.class)
    public ResponseEntity<RoomDto> update(@PathVariable Long id, @RequestBody RoomDto roomDto){
        return ResponseEntity.ok(this.roomServiceImpl.update(id,roomDto));
    }

    // DELETE
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete Operation", response = RoomDto.class)
    public ResponseEntity<Boolean> delete(@PathVariable Long id){
        return ResponseEntity.ok(this.roomServiceImpl.delete(id));
    }
}
