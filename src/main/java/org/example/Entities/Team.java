package org.example.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Team extends BaseEntity{

    private String teamName;

    @OneToOne
    @JoinColumn(name = "lead_id")
    private User teamLead;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Team team = (Team) o;
        return Objects.equals(teamName, team.teamName) && Objects.equals(teamLead, team.teamLead);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), teamName, teamLead);
    }



}
