package Project1;

import java.math.*;
import java.sql.*;

import Project1.DAOs.*;

public class Project1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Connection connection = DatabaseConnection.connect();
		EmployeeDAOImp edi = new EmployeeDAOImp();
		ReimbursementDAOImp rdi = new ReimbursementDAOImp();
		AttachmentDAOImp adi = new AttachmentDAOImp();
		GradingFormatDAOImp gfdi = new GradingFormatDAOImp();
		rdi.insertReimbursement();
		adi.insertAttachment("ApprovalAdditionalInfo");
		adi.insertAttachment("ApprovalAttachment");
		adi.insertAttachment("EventAttachment");
		gfdi.updateGradingFormatProof();
		adi.selectAttachment("Event", "eve_att_event", new BigInteger("1"));
	}

}
