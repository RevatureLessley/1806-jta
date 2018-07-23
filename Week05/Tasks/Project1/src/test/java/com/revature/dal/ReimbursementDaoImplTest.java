package com.revature.dal;

import com.revature.beans.EmployeeBean;
import com.revature.beans.ReimbursementBean;
import org.junit.*;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class ReimbursementDaoImplTest {
    private final double DOUBLE_PRECISION = 0.001;
    private static LocalDate date = LocalDate.now();
    private static EmployeeBean ebean = new EmployeeBean(100, "Test", "Dude", "pass", 1111111111L, "testemail@test.email",
            0,0, 200, 300, 400);
    private  ReimbursementBean rbean = new ReimbursementBean(100, ebean, date, "TestLocation", "TestDescription", 175.00, "Format?", 1, 0, "");
    private static ReimbursementDaoImpl rdao = new ReimbursementDaoImpl();
    private static EmployeeDaoImpl edao = new EmployeeDaoImpl();

    private static boolean abort = false;
    @BeforeClass
    public static void beforeClass(){
        if (!edao.insertEmployee(ebean)) abort = true;

    }

    @Before
    public void before(){
        if (!rdao.insertReimbursementForm(rbean, ebean.getId())) abort = true;
        rbean = rdao.retrieveReimbursementsByEmployee(ebean.getId()).get(0);
        //Since the Database reassigns an ID to the request, rbean.getId() was not valid
    }

    @Test
    public void retrieveReimbursementFormById() {
        if (abort) fail();
        ReimbursementBean bean = rdao.retrieveReimbursementFormById(rbean.getId());
        assertNotNull(bean);
        assertEquals(rbean.getId(), bean.getId());
        assertEquals(rbean.getEmployee().getId(), bean.getEmployee().getId());
        assertEquals(rbean.getDate(), bean.getDate());
        assertEquals(rbean.getLocation(), bean.getLocation());
        assertEquals(rbean.getDescription(), bean.getDescription());
        assertEquals(rbean.getReimbursement(), bean.getReimbursement(), DOUBLE_PRECISION);
        assertEquals(rbean.getGradingFormat(), bean.getGradingFormat());
        assertEquals(rbean.getEventType(), bean.getEventType());
        assertEquals(rbean.getStatus(), bean.getStatus());
    }

    @Test
    public void insertReimbursementForm() {
        if (abort) fail();
        ReimbursementBean bean = new ReimbursementBean(101, ebean, date, "TestLocation", "TestDescription", 175.00, "Format?", 1, 0, "");

        assertTrue(rdao.insertReimbursementForm(bean, bean.getEmployee().getId()));
        bean = rdao.retrieveReimbursementsByEmployee(bean.getEmployee().getId()).get(0);    //Since the Database reassigns an ID to the request, 101 is not valid
        ReimbursementBean testBean = rdao.retrieveReimbursementFormById(bean.getId());
        assertNotNull(bean);
        assertNotNull(testBean);

        assertEquals(bean.getId(),
        testBean.getId());
        assertEquals(bean.getEmployee().getId(), testBean.getEmployee().getId());
        assertEquals(bean.getDate(), testBean.getDate());
        assertEquals(bean.getLocation(), testBean.getLocation());
        assertEquals(bean.getDescription(), testBean.getDescription());
        assertEquals(bean.getReimbursement(), testBean.getReimbursement(), DOUBLE_PRECISION);
        assertEquals(bean.getGradingFormat(), testBean.getGradingFormat());
        assertEquals(bean.getEventType(), testBean.getEventType());
        assertEquals(bean.getStatus(), testBean.getStatus());

        assertTrue(rdao.deleteReimbursementFormById(bean.getId()));

    }

    @Test
    public void deleteReimbursementFormById() {
        if (abort) fail();
        ReimbursementBean bean = rdao.retrieveReimbursementFormById(rbean.getId());
        assertNotNull(bean);

        assertTrue(rdao.deleteReimbursementFormById(bean.getId()));

        //reinsert for other tests
        assertTrue(rdao.insertReimbursementForm(bean, bean.getEmployee().getId()));
    }

    @After
    public void after(){
        rdao.deleteReimbursementFormById(rbean.getId());
    }

    @AfterClass
    public static void afterClass(){
        edao.deleteEmployeeById(ebean.getId());
    }
}