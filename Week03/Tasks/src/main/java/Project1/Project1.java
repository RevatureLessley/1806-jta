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
		adi.insertAttachment("EVENT", "C:\\Users\\Swilery\\Documents\\Walter\\Revature\\FromFS\\Poems.pdf", "970411");
		adi.insertAttachment("APPROVAL", "C:\\Users\\Swilery\\Documents\\Walter\\Revature\\FromFS\\Poems.pdf", "970411");
		adi.insertAttachment("ADDITIONAL_INFO", "C:\\Users\\Swilery\\Documents\\Walter\\Revature\\FromFS\\Poems.pdf", "970411");
		adi.insertAttachment("GRADING_FORMAT", "C:\\Users\\Swilery\\Documents\\Walter\\Revature\\FromFS\\Poems.pdf", "970411");
		adi.insertAttachment("EVENT", "C:\\Users\\Swilery\\Documents\\Walter\\Revature\\FromFS\\Journey to the West Vocabulary.odt", "24044");
		adi.insertAttachment("APPROVAL", "C:\\Users\\Swilery\\Documents\\Walter\\Revature\\FromFS\\Journey to the West Vocabulary.odt", "24044");
		adi.insertAttachment("ADDITIONAL_INFO", "C:\\Users\\Swilery\\Documents\\Walter\\Revature\\FromFS\\Journey to the West Vocabulary.odt", "24044");
		adi.insertAttachment("GRADING_FORMAT", "C:\\Users\\Swilery\\Documents\\Walter\\Revature\\FromFS\\Journey to the West Vocabulary.odt", "24044");
		adi.insertAttachment("EVENT", "C:\\Users\\Swilery\\Documents\\Walter\\Revature\\FromFS\\10369913_267485166779577_5573839803579672783_n.jpg", "47906");
		adi.insertAttachment("APPROVAL", "C:\\Users\\Swilery\\Documents\\Walter\\Revature\\FromFS\\10369913_267485166779577_5573839803579672783_n.jpg", "47906");
		adi.insertAttachment("ADDITIONAL_INFO", "C:\\Users\\Swilery\\Documents\\Walter\\Revature\\FromFS\\10369913_267485166779577_5573839803579672783_n.jpg", "47906");
		adi.insertAttachment("GRADING_FORMAT", "C:\\Users\\Swilery\\Documents\\Walter\\Revature\\FromFS\\10369913_267485166779577_5573839803579672783_n.jpg", "47906");
//		gfdi.updateGradingFormatProof();
		adi.selectAttachment(new BigInteger("1"), "EVENT");
	}

}