package com.turksat.appointmentsystem.model.repository;

import com.turksat.appointmentsystem.model.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

}
