package com.revature.dal;

import com.revature.beans.EmployeeBean;
import com.revature.beans.ReimbursementBean;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class ReimbursementDaoImplTest {
    private final double DOUBLE_PRECISION = 0.001;
    private static LocalDate date = LocalDate.now();
    private static EmployeeBean ebean = new EmployeeBean(100, "Test", "Dude", "pass", 1111111111L, "testemail@test.email",
            0,0, 200, 300, 400);
    private static ReimbursementBean rbean = new ReimbursementBean(100, ebean, date, "TestLocation", "TestDescription", 175.00, "Format?", 1, false, "");
    private static ReimbursementDaoImpl rdao = new ReimbursementDaoImpl();
    private static EmployeeDaoImpl edao = new EmployeeDaoImpl();

    @BeforeClass
    public static void beforeClass(){
        edao.insertEmployee(ebean);
        rdao.insertReimbursementForm(rbean);
    }


    @Test
    public void retrieveReimbursementFormById() {
        ReimbursementBean bean = rdao.retrieveReimbursementFormById(rbean.getId());

        assertEquals(rbean.getId(), bean.getId());
        assertEquals(rbean.getEmployee().getId(), bean.getEmployee().getId());
        assertEquals(rbean.getDate(), bean.getDate());
        assertEquals(rbean.getLocation(), bean.getLocation());
        assertEquals(rbean.getDescription(), bean.getDescription());
        assertEquals(rbean.getReimbursement(), bean.getReimbursement(), DOUBLE_PRECISION);
        assertEquals(rbean.getGradingFormat(), bean.getGradingFormat());
        assertEquals(rbean.getEventType(), bean.getEventType());
        assertEquals(rbean.isApproved(), bean.isApproved());
    }

    @Test
    public void insertReimbursementForm() {
        ReimbursementBean bean = new ReimbursementBean(101, ebean, date, "TestLocation", "TestDescription", 175.00, "Format?", 1, false, "");

        assertTrue(rdao.insertReimbursementForm(bean));
        ReimbursementBean testBean = rdao.retrieveReimbursementFormById(bean.getId());

        assertEquals(bean.getId(), testBean.getId());
        assertEquals(bean.getEmployee().getId(), testBean.getEmployee().getId());
        assertEquals(bean.getDate(), testBean.getDate());
        assertEquals(bean.getLocation(), testBean.getLocation());
        assertEquals(bean.getDescription(), testBean.getDescription());
        assertEquals(bean.getReimbursement(), testBean.getReimbursement(), DOUBLE_PRECISION);
        assertEquals(bean.getGradingFormat(), testBean.getGradingFormat());
        assertEquals(bean.getEventType(), testBean.getEventType());
        assertEquals(bean.isApproved(), testBean.isApproved());

        assertTrue(rdao.deleteReimbursementFormById(bean.getId()));

    }

    @Test
    public void deleteReimbursementFormById() {
        ReimbursementBean bean = rdao.retrieveReimbursementFormById(rbean.getId());

        assertTrue(rdao.deleteReimbursementFormById(bean.getId()));

        //reinsert for other tests
        assertTrue(rdao.insertReimbursementForm(bean));
    }

    @AfterClass
    public static void afterClass(){
        rdao.deleteReimbursementFormById(rbean.getId());
        edao.deleteEmployeeById(ebean.getId());
    }
}