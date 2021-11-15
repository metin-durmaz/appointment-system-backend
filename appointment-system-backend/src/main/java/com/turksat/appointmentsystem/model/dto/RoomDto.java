package com.turksat.appointmentsystem.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Room Data Transfer Object")

public class RoomDto {

    @ApiModelProperty(value = "Room ID")
    private Long id;

    @ApiModelProperty(value = "Room Status")
    private Boolean roomStatus;

    @ApiModelProperty(value = "Room Free Days")
    private String freeDays;

    @ApiModelProperty(value = "Room Periods")
    private Collection<PeriodDto> periods;

    @ApiModelProperty(value = "Room Appointments")
    private Collection<AppointmentDto> appointments;

}