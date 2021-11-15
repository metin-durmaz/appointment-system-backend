package com.turksat.appointmentsystem.controller;

import com.turksat.appointmentsystem.model.dto.AppointmentDto;
import com.turksat.appointmentsystem.model.dto.PeriodDto;
import com.turksat.appointmentsystem.service.implementations.PeriodServiceImpl;
import com.turksat.appointmentsystem.util.ApiPaths;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@CrossOrigin(origins = ApiPaths.LOCAL_CLIENT_BASE_PATH, maxAge = 3600)
@RequestMapping("/api/periods")
@Api(value = "Period API")
public class PeriodController {

    private final PeriodServiceImpl periodServiceImpl;

    public PeriodController(PeriodServiceImpl periodServiceImpl) {
        this.periodServiceImpl = periodServiceImpl;
    }

    // CREATE
    @PostMapping
    @ApiOperation(value = "Create Operation", response = PeriodDto.class)
    public ResponseEntity<PeriodDto> save(@RequestBody PeriodDto periodDto){
        return ResponseEntity.ok(this.periodServiceImpl.save(periodDto));
    }

    // READ
    @GetMapping("/{id}")
    @ApiOperation(value = "Read Operation", response = PeriodDto.class)
    public ResponseEntity<PeriodDto> getById(@PathVariable Long id){
        return ResponseEntity.ok(this.periodServiceImpl.getById(id));
    }

    // UPDATE
    @PutMapping("/{id}")
    @ApiOperation(value = "Update Operation", response = PeriodDto.class)
    public ResponseEntity<PeriodDto> update(@PathVariable Long id, @RequestBody PeriodDto periodDto){
        return ResponseEntity.ok(this.periodServiceImpl.update(id,periodDto));
    }

    // DELETE
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete Operation", response = Boolean.class)
    public ResponseEntity<Boolean> delete(@PathVariable Long id){
        return ResponseEntity.ok(this.periodServiceImpl.delete(id));
    }
}
