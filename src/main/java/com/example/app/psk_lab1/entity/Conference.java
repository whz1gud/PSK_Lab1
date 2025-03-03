package com.example.app.psk_lab1.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@EqualsAndHashCode(of={"title"})
public class Conference implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String location;

    private LocalDate startDate;

    private LocalDate endDate;

    @Version
    private int optLockVersion;

    @OneToMany(mappedBy = "conference")
    private List<Presentation> presentations;
}
