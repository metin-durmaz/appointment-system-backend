package com.turksat.appointmentsystem.model.repository;

import com.turksat.appointmentsystem.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String roleName);


}
