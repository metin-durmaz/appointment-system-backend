package com.turksat.appointmentsystem.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Period Data Transfer Object")

public class PeriodDto {

    @ApiModelProperty(value = "Period ID")
    private Long id;

    @ApiModelProperty(value = "Period Status")
    private Boolean status;

    @ApiModelProperty(value = "Period Slice")
    private String slice;

    @ApiModelProperty(value = "Period Room")
    private RoomDto room;
}
