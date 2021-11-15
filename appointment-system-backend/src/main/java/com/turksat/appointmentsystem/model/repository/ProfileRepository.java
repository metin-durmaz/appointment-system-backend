package com.turksat.appointmentsystem.model.repository;

import com.turksat.appointmentsystem.model.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

    Profile findByUserUsername(String username);

}

