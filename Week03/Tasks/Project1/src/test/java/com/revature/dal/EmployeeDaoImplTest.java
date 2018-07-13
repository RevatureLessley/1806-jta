package com.revature.dal;

import com.revature.beans.EmployeeBean;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class EmployeeDaoImplTest {
    private final double DOUBLE_PRECISION = 0.0001f;
    private static final int ID = 100, SID = 200, DHID = 300, BCID = 400;   //guarenteed to not be used in normal execution
    private static EmployeeBean employee, supervisor, deptHead, benCo;
    private EmployeeDaoImpl dao;

    @BeforeClass
    public static void beforeClass(){
        employee = new EmployeeBean(ID,"Test","Dude",1112223333L,"tdude@email.com",
                156.07,0,SID,DHID,BCID);
        supervisor = new EmployeeBean(SID, "Super", "Visor", 2223334444L, "svisor@email.com",
                0,950.28,SID,DHID,BCID);
        deptHead = new EmployeeBean(DHID, "Dept", "Head", 3334445555L, "dhead@email.com",
                0,0,DHID,DHID, BCID);
        benCo = new EmployeeBean(BCID, "Ben", "Co", 4445556666L, "bCo@email.com",
                0,0,BCID, BCID, BCID);

        EmployeeDaoImpl dao = new EmployeeDaoImpl();
        dao.insertEmployee(employee);
        dao.insertEmployee(supervisor);
        dao.insertEmployee(deptHead);
        dao.insertEmployee(benCo);
    }

    @Before
    public void before(){
        dao = new EmployeeDaoImpl();
    }

    @Test
    public void retrieveEmployeeById() {
        //employee
        EmployeeBean bean = dao.retrieveEmployeeById(employee.getId());
        assertEquals(employee.getId(), bean.getId());
        assertEquals(employee.getFirstName(), bean.getFirstName());
        assertEquals(employee.getLastName(), bean.getLastName());
        assertEquals(employee.getPhone(), bean.getPhone());
        assertEquals(employee.getEmail(), bean.getEmail());
        assertEquals(employee.getPendingReimbursements(), bean.getPendingReimbursements(), DOUBLE_PRECISION);
        assertEquals(employee.getAwardedReimbursements(), bean.getAwardedReimbursements(), DOUBLE_PRECISION);
        assertEquals(employee.getSupervisorId(), bean.getSupervisorId());
        assertEquals(employee.getDeptHeadId(), bean.getDeptHeadId());
        assertEquals(employee.getBenCoId(), bean.getBenCoId());
        //supervisor
        bean = dao.retrieveEmployeeById(supervisor.getId());
        assertEquals(supervisor.getId(), bean.getId());
        assertEquals(supervisor.getFirstName(), bean.getFirstName());
        assertEquals(supervisor.getLastName(), bean.getLastName());
        assertEquals(supervisor.getPhone(), bean.getPhone());
        assertEquals(supervisor.getEmail(), bean.getEmail());
        assertEquals(supervisor.getPendingReimbursements(), bean.getPendingReimbursements(), DOUBLE_PRECISION);
        assertEquals(supervisor.getAwardedReimbursements(), bean.getAwardedReimbursements(), DOUBLE_PRECISION);
        assertEquals(supervisor.getSupervisorId(), bean.getSupervisorId());
        assertEquals(supervisor.getDeptHeadId(), bean.getDeptHeadId());
        assertEquals(supervisor.getBenCoId(), bean.getBenCoId());
        //deptHead
        bean = dao.retrieveEmployeeById(deptHead.getId());
        assertEquals(deptHead.getId(), bean.getId());
        assertEquals(deptHead.getFirstName(), bean.getFirstName());
        assertEquals(deptHead.getLastName(), bean.getLastName());
        assertEquals(deptHead.getPhone(), bean.getPhone());
        assertEquals(deptHead.getEmail(), bean.getEmail());
        assertEquals(deptHead.getPendingReimbursements(), bean.getPendingReimbursements(), DOUBLE_PRECISION);
        assertEquals(deptHead.getAwardedReimbursements(), bean.getAwardedReimbursements(), DOUBLE_PRECISION);
        assertEquals(deptHead.getSupervisorId(), bean.getSupervisorId());
        assertEquals(deptHead.getDeptHeadId(), bean.getDeptHeadId());
        assertEquals(deptHead.getBenCoId(), bean.getBenCoId());
        //benCo
        bean = dao.retrieveEmployeeById(benCo.getId());
        assertEquals(benCo.getId(), bean.getId());
        assertEquals(benCo.getFirstName(), bean.getFirstName());
        assertEquals(benCo.getLastName(), bean.getLastName());
        assertEquals(benCo.getPhone(), bean.getPhone());
        assertEquals(benCo.getEmail(), bean.getEmail());
        assertEquals(benCo.getPendingReimbursements(), bean.getPendingReimbursements(), DOUBLE_PRECISION);
        assertEquals(benCo.getAwardedReimbursements(), bean.getAwardedReimbursements(), DOUBLE_PRECISION);
        assertEquals(benCo.getSupervisorId(), bean.getSupervisorId());
        assertEquals(benCo.getDeptHeadId(), bean.getDeptHeadId());
        assertEquals(benCo.getBenCoId(), bean.getBenCoId());
    }

    @Test
    public void insertEmployee() {
        int id = -10;
        assertNull(dao.retrieveEmployeeById(id));
        EmployeeBean bean = new EmployeeBean(id, "test", "name", 9876543210L, "test@email.com",
                90, 830, SID, DHID, BCID);
        dao.insertEmployee(bean);
        
        assertEquals(bean.getId(), dao.retrieveEmployeeById(id).getId());
        assertEquals(bean.getFirstName(), dao.retrieveEmployeeById(id).getFirstName());
        assertEquals(bean.getLastName(), dao.retrieveEmployeeById(id).getLastName());
        assertEquals(bean.getPhone(), dao.retrieveEmployeeById(id).getPhone());
        assertEquals(bean.getEmail(), dao.retrieveEmployeeById(id).getEmail());
        assertEquals(bean.getPendingReimbursements(), dao.retrieveEmployeeById(id).getPendingReimbursements(), DOUBLE_PRECISION);
        assertEquals(bean.getAwardedReimbursements(), dao.retrieveEmployeeById(id).getAwardedReimbursements(), DOUBLE_PRECISION);
        assertEquals(bean.getSupervisorId(), dao.retrieveEmployeeById(id).getSupervisorId());
        assertEquals(bean.getDeptHeadId(), dao.retrieveEmployeeById(id).getDeptHeadId());
        assertEquals(bean.getBenCoId(), dao.retrieveEmployeeById(id).getBenCoId());

        //cleanup database
        dao.deleteEmployeeById(id);
    }

    @Test
    public void deleteEmployeeById() {
        //precheck
        //employee
        EmployeeBean bean = dao.retrieveEmployeeById(employee.getId());
        assertEquals(employee.getId(), bean.getId());
        assertEquals(employee.getFirstName(), bean.getFirstName());
        assertEquals(employee.getLastName(), bean.getLastName());
        assertEquals(employee.getPhone(), bean.getPhone());
        assertEquals(employee.getEmail(), bean.getEmail());
        assertEquals(employee.getPendingReimbursements(), bean.getPendingReimbursements(), DOUBLE_PRECISION);
        assertEquals(employee.getAwardedReimbursements(), bean.getAwardedReimbursements(), DOUBLE_PRECISION);
        assertEquals(employee.getSupervisorId(), bean.getSupervisorId());
        assertEquals(employee.getDeptHeadId(), bean.getDeptHeadId());
        assertEquals(employee.getBenCoId(), bean.getBenCoId());
        //supervisor
        bean = dao.retrieveEmployeeById(supervisor.getId());
        assertEquals(supervisor.getId(), bean.getId());
        assertEquals(supervisor.getFirstName(), bean.getFirstName());
        assertEquals(supervisor.getLastName(), bean.getLastName());
        assertEquals(supervisor.getPhone(), bean.getPhone());
        assertEquals(supervisor.getEmail(), bean.getEmail());
        assertEquals(supervisor.getPendingReimbursements(), bean.getPendingReimbursements(), DOUBLE_PRECISION);
        assertEquals(supervisor.getAwardedReimbursements(), bean.getAwardedReimbursements(), DOUBLE_PRECISION);
        assertEquals(supervisor.getSupervisorId(), bean.getSupervisorId());
        assertEquals(supervisor.getDeptHeadId(), bean.getDeptHeadId());
        assertEquals(supervisor.getBenCoId(), bean.getBenCoId());
        //deptHead
        bean = dao.retrieveEmployeeById(deptHead.getId());
        assertEquals(deptHead.getId(), bean.getId());
        assertEquals(deptHead.getFirstName(), bean.getFirstName());
        assertEquals(deptHead.getLastName(), bean.getLastName());
        assertEquals(deptHead.getPhone(), bean.getPhone());
        assertEquals(deptHead.getEmail(), bean.getEmail());
        assertEquals(deptHead.getPendingReimbursements(), bean.getPendingReimbursements(), DOUBLE_PRECISION);
        assertEquals(deptHead.getAwardedReimbursements(), bean.getAwardedReimbursements(), DOUBLE_PRECISION);
        assertEquals(deptHead.getSupervisorId(), bean.getSupervisorId());
        assertEquals(deptHead.getDeptHeadId(), bean.getDeptHeadId());
        assertEquals(deptHead.getBenCoId(), bean.getBenCoId());
        //benCo
        bean = dao.retrieveEmployeeById(benCo.getId());
        assertEquals(benCo.getId(), bean.getId());
        assertEquals(benCo.getFirstName(), bean.getFirstName());
        assertEquals(benCo.getLastName(), bean.getLastName());
        assertEquals(benCo.getPhone(), bean.getPhone());
        assertEquals(benCo.getEmail(), bean.getEmail());
        assertEquals(benCo.getPendingReimbursements(), bean.getPendingReimbursements(), DOUBLE_PRECISION);
        assertEquals(benCo.getAwardedReimbursements(), bean.getAwardedReimbursements(), DOUBLE_PRECISION);
        assertEquals(benCo.getSupervisorId(), bean.getSupervisorId());
        assertEquals(benCo.getDeptHeadId(), bean.getDeptHeadId());
        assertEquals(benCo.getBenCoId(), bean.getBenCoId());

        assertTrue(dao.deleteEmployeeById(ID));
        assertTrue(dao.deleteEmployeeById(SID));
        assertTrue(dao.deleteEmployeeById(DHID));
        assertTrue(dao.deleteEmployeeById(BCID));

        assertNull(dao.retrieveEmployeeById(ID));
        assertNull(dao.retrieveEmployeeById(SID));
        assertNull(dao.retrieveEmployeeById(DHID));
        assertNull(dao.retrieveEmployeeById(BCID));

        //preserve database state between tests
        dao.insertEmployee(employee);
        dao.insertEmployee(supervisor);
        dao.insertEmployee(deptHead);
        dao.insertEmployee(benCo);
    }

    @AfterClass
    public static void afterClass(){
        EmployeeDaoImpl dao = new EmployeeDaoImpl();
        dao.deleteEmployeeById(ID);
        dao.deleteEmployeeById(SID);
        dao.deleteEmployeeById(DHID);
        dao.deleteEmployeeById(BCID);
    }

}