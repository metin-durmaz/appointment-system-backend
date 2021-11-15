package com.turksat.appointmentsystem.model.repository;

import com.turksat.appointmentsystem.model.entity.Period;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeriodRepository extends JpaRepository<Period, Long> {
}
