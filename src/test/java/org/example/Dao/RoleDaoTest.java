package org.example.Dao;

import junit.framework.TestCase;
import org.example.Entities.Role;
import org.example.Entities.Team;
import org.example.util.HibernateUtils;
import org.hibernate.SessionFactory;

public class RoleDaoTest extends TestCase {

    public SessionFactory sessionFactory;

    public void setUp() throws Exception {
        super.setUp();
        this.sessionFactory= HibernateUtils.getsessionFactory();
    }

    public void testCreateRole() {
        Role role = new Role();
        role.setRoleName("Normal");
        RoleDao roleDao = new RoleDao(sessionFactory);
        Role role1 = roleDao.createRole(role);
        assertNotNull(role1);
    }

    public void testUpdateRole() {
        Role role = new Role();
        role.setId(1);
        role.setRoleName("Normal");
        RoleDao roleDao = new RoleDao(sessionFactory);
        Role role1 = roleDao.updateRole(role);
        assertNotNull(role1);
    }

    public void testGetRoleById() {
        testCreateRole();
        RoleDao roleDao = new RoleDao(sessionFactory);
        Role roleById = roleDao.getRoleById(1);
        assertNotNull(roleById);
    }

    public void testDeleteRole() {
        testCreateRole();
        RoleDao roleDao = new RoleDao(sessionFactory);
        roleDao.deleteRole(1);
        assertTrue(true);
    }
}