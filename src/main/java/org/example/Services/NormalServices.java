package org.example.Services;

import org.example.Entities.Attendence;
import org.example.Entities.Leave;
import org.example.Entities.User;
import org.example.util.LoginRequest;

import java.util.List;

public interface NormalServices {

    User logIn(LoginRequest loginRequest);
    Attendence clockIn(Integer userId);
    Attendence clockOut(Integer userId);

    Leave applyLeave(Leave requestLeave,Integer userId);

    List<Leave> myLeave(Integer userId);

    List<Leave>myTeamLeaves(Integer userId);



}
