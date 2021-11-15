package com.turksat.appointmentsystem.controller;

import com.turksat.appointmentsystem.model.dto.AppointmentDto;
import com.turksat.appointmentsystem.service.implementations.AppointmentServiceImpl;
import com.turksat.appointmentsystem.util.ApiPaths;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

@RestController
@Slf4j
@CrossOrigin(origins = ApiPaths.LOCAL_CLIENT_BASE_PATH,maxAge = 3600)
@RequestMapping("/api/appointments")
@Api(value = "Appointment API")
public class AppointmentController {

    private final AppointmentServiceImpl appointmentServiceImpl;

    public AppointmentController(AppointmentServiceImpl appointmentServiceImpl) {
        this.appointmentServiceImpl = appointmentServiceImpl;
    }

    // CREATE
    @PostMapping
    @ApiOperation(value = "Create Operation", response = AppointmentDto.class)
    public ResponseEntity<AppointmentDto> save(@RequestBody AppointmentDto appointmentDto) throws MessagingException, UnsupportedEncodingException {
        return ResponseEntity.ok(this.appointmentServiceImpl.save(appointmentDto));
    }

    // READ
    @GetMapping("/{id}")
    @ApiOperation(value = "Read Operation", response = AppointmentDto.class)
    public ResponseEntity<AppointmentDto> getById(@PathVariable Long id){
        return ResponseEntity.ok(this.appointmentServiceImpl.getById(id));
    }

    // UPDATE
    @PutMapping("/{id}")
    @ApiOperation(value = "Update Operation", response = AppointmentDto.class)
    public ResponseEntity<AppointmentDto> update(@PathVariable Long id, @RequestBody AppointmentDto appointmentDto){
        return ResponseEntity.ok(this.appointmentServiceImpl.update(id,appointmentDto));
    }

    // DELETE
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete Operation", response = Boolean.class)
    public ResponseEntity<Boolean> delete(@PathVariable Long id){
        return ResponseEntity.ok(this.appointmentServiceImpl.delete(id));
    }
}
