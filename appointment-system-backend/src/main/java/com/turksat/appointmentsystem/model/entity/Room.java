package com.turksat.appointmentsystem.model.entity;

import lombok.*;
import java.util.*;
import javax.persistence.*;

@Entity
@Table(name = "rooms")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode

public class Room extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    private Collection<Appointment> appointments;

    @OneToMany(cascade = CascadeType.ALL)
    private Collection<Period> periods;

    @Column(name="room_status")
    private Boolean roomStatus;

    @Column(name="free_days")
    private String freeDays;

}