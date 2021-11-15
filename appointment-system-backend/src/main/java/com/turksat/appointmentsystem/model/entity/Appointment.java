package com.turksat.appointmentsystem.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "appointments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode

public class Appointment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="start_date")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @CreatedDate
    private Date startDate;

    @Column(name="finish_date")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @CreatedDate
    private Date finishDate;

    @Column(name="duration")
    private Integer duration;

    @Column(name="appointment_status") // 0->reserved, 1->postponed, 2->cancelled
    private Integer appStatus;

    @JoinColumn(name="owner")
    @ManyToOne(fetch = FetchType.LAZY)
    private User owner;

    @JoinColumn(name="room")
    @ManyToOne(fetch = FetchType.LAZY)
    private Room room;

    @Column(name="capacity")
    private Integer capacity;

}