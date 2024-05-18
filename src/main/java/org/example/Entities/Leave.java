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
@Entity(name = "user_leave")
public class Leave extends BaseEntity{

    private LocalDate leaveDate;
    private String leaveReason;
    private String leaveStatus;
    private String decisionReason;
    private Instant decisionAt;
    private Integer decisionBy;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;


}
