package org.example.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User extends BaseEntity{

    private String employeeId;
    private String name;
    @Column(unique = true)
    private String email;
    private String password;
    private String position;
    private  String employmentStatus;
    private String gender;
    private String bloodGroup;
    private LocalDate birthDate;
    private String nidNumber;
    private String tinNumber;
    private String contactNumber;


    @ToString.Exclude
    @ManyToOne(cascade =CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "team_id")
    private Team team;

    @ToString.Exclude
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")}
    )
    private Set<Role>roles = new HashSet<>();



    @Override
    public int hashCode() {
        return Objects.hash(employeeId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        User user = (User) obj;
        return Objects.equals(employeeId, user.employeeId);
    }



}
