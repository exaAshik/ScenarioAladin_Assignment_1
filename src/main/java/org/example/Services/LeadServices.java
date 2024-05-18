package org.example.Services;

import org.example.Entities.Attendence;
import org.example.Entities.Leave;

import java.util.List;

public interface LeadServices {

    Leave Decision(Leave leave,Integer leadId);
    List<Attendence> attendenceReportByUser(Integer userId,Integer leadId);
}
