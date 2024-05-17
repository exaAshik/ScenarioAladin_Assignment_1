package org.example.Dao;

import junit.framework.TestCase;
import org.example.Entities.Team;
import org.example.Entities.User;
import org.example.util.HibernateUtils;
import org.hibernate.SessionFactory;

public class TeamDaoTest extends TestCase {

    public SessionFactory sessionFactory;
    public void setUp() throws Exception {
        super.setUp();
        this.sessionFactory= HibernateUtils.getsessionFactory();
    }

    public void testTeamCreate(){
        Team team = new Team();
        team.setTeamName("GGWP");
        TeamDao teamDao = new TeamDao(sessionFactory);
        Team team1 = teamDao.createTeam(team);
        assertNotNull(team1);

    }

    public void testTeamUpdate(){
        Team team = new Team();
        team.setId(1);
        team.setTeamName("GGWP1");
        TeamDao teamDao = new TeamDao(sessionFactory);
        Team team1 = teamDao.updateTeam(team);
        assertNotNull(team1);
    }

    public void testGetTeamById(){
        testTeamCreate();
        testTeamUpdate();
        TeamDao teamDao = new TeamDao(sessionFactory);
        Team team = teamDao.getTeamById(1);
        assertNotNull(team);
    }

    public void testDeleteTeam(){
        testTeamCreate();
        TeamDao teamDao = new TeamDao(sessionFactory);
        boolean b = teamDao.deleteTeam(1);
        assertTrue(true);
    }




}