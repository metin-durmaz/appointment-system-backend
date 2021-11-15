package com.turksat.appointmentsystem.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Appointment Data Transfer Object")

public class AppointmentDto {

    @ApiModelProperty(value = "Appointment ID")
    private Long id;

    @ApiModelProperty(value = "Appointment Start Date")
    private Date startDate;

    @ApiModelProperty(value = "Appointment Finish Date")
    private Date finishDate;

    @ApiModelProperty(value = "Appointment Duration")
    private Integer duration;

    @ApiModelProperty(value = "Appointment Status")
    private Integer appStatus;

    private UserDto owner;

    private RoomDto room;

    private Integer capacity;

}