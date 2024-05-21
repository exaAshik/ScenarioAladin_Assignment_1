package org.example.Entities;


import jakarta.persistence.*;
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

    private LocalDate leaveStartDate;
    private LocalDate leaveEndDate;
    private String leaveReason;
    private String leaveType;
    private String leaveStatus;
    private String decisionReason;
    private Instant decisionAt;
    private Integer decisionBy;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;


}
