package com.turksat.appointmentsystem.model.entity;

import lombok.*;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "periods")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode

public class Period extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "slice")
    private String slice;

    @JoinColumn(name="room")
    @ManyToOne(fetch = FetchType.LAZY)
    private Room room;

}