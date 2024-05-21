package org.example.Services.Implementation;

import junit.framework.TestCase;
import org.example.Dao.LeaveDao;
import org.example.Entities.Attendence;
import org.example.Entities.Leave;
import org.example.Services.LeadServices;
import org.example.util.HibernateUtils;
import org.hibernate.SessionFactory;

import java.time.Instant;
import java.util.List;

public class ImplementationLeadTest extends TestCase {


    public SessionFactory sessionFactory;

    public void setUp() throws Exception {
        super.setUp();
        this.sessionFactory= HibernateUtils.getsessionFactory();
    }




    public void testDecision() {
        LeaveDao leaveDao = new LeaveDao(sessionFactory);
        Leave leaveById = leaveDao.getLeaveById(1);
        leaveById.setDecisionBy(1);
        leaveById.setLeaveStatus("Approved");
        leaveById.setDecisionAt(Instant.now());
        leaveById.setDecisionReason("ewhufewkh");

        LeadServices implementationLead = new ImplementationLead(sessionFactory);

        Leave decision = implementationLead.Decision(leaveById, 1);
        assertNotNull(decision);

    }

    public void testAttendenceReportByUser() {

        LeadServices implementationLead = new ImplementationLead(sessionFactory);
        List<Attendence> attendences = implementationLead.attendenceReportByUser(52, 1);
        System.out.println(attendences);
        assertTrue(true);
    }
}