package Project1.Service;

import java.math.*;
import java.sql.*;
import Project1.Beans.*;
import Project1.DAOs.*;

public class ReimbursementService {
	private static ReimbursementDAOImp rdi = new ReimbursementDAOImp();
	
	public static Reimbursement submitReimbursement(String username,
													String type,
													BigDecimal cost,
													Timestamp datetime, 
													String location,
													String workMissed,
													Double cutoff,
													String description,
													String justification) {
		
		return rdi.insertReimbursement(username, type, cost, datetime,
									   location, workMissed, cutoff, 
									   description, justification);
	}
}
