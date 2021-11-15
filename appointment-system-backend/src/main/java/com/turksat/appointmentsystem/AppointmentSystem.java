package com.turksat.appointmentsystem;

import com.turksat.appointmentsystem.model.entity.Role;
import com.turksat.appointmentsystem.model.repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing

public class AppointmentSystem {

    public static void main(String[] args) {
        SpringApplication.run(AppointmentSystem.class, args);
    }

    @Bean
    public ModelMapper getModelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper;
    }

    @Bean
    public CommandLineRunner demoData(RoleRepository repo) {
        return args -> {
            repo.save(new Role(1L,"USER"));
            repo.save(new Role(2L,"ADMIN"));
        };
    }
}
