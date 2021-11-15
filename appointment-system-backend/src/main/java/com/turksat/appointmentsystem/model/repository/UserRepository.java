package com.turksat.appointmentsystem.model.repository;

import com.turksat.appointmentsystem.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    List<User> getAllByRoleName(String role);

    User findByEmail(String email);

    User getByEmail(String email);
    User getByPhone(String phone);

}
