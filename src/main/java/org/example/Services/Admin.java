package org.example.Services;

import org.example.Entities.Leave;

import java.util.List;

public interface Admin {

    List<Leave>getAllLeave(Integer adminId);
    Leave modifyLeave(Integer AdminId);
}
