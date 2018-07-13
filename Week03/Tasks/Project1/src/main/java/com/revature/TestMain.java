package com.revature;

import com.revature.beans.EmployeeBean;
import com.revature.dal.EmployeeDaoImpl;
import com.revature.dal.GenericDaoImpl;

public class TestMain {
    public static void main(String[] args){
//        GenericDaoImpl<EmployeeBean> dao = new GenericDaoImpl<>();
//        EmployeeBean bean = new EmployeeBean(3, "f", "l", 123, "email", 0,0,0,0,0);
//        EmployeeBean notBean;
//        //notBean = dao.select(bean, "", 3);
//
//        System.out.println(bean.toString() + "\n");
//        //System.out.println(notBean.toString());
        EmployeeDaoImpl dao = new EmployeeDaoImpl();
        System.out.println(dao.deleteEmployeeById(1774));
    }
}
