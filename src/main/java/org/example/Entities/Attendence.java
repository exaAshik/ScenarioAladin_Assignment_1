package org.example.Entities;


import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Attendence extends BaseEntity{

    private Instant clockIn;
    private Instant clockOut;
    private LocalDate date;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}
