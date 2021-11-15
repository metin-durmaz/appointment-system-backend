package com.turksat.appointmentsystem.service;

import com.turksat.appointmentsystem.model.dto.PeriodDto;

public interface PeriodService {
    PeriodDto save(PeriodDto periodDto);
    PeriodDto getById(Long id);
    Boolean delete(Long id);
    PeriodDto update(Long id , PeriodDto periodDto);
}
