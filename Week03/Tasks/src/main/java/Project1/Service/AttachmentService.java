package Project1.Service;

import java.math.BigInteger;
import java.util.HashMap;

import Project1.Beans.*;
import Project1.DAOs.*;

public class AttachmentService {
	private static AttachmentDAOImp adi = new AttachmentDAOImp();
	
	public static HashMap<BigInteger, Attachment> 
		getAttachments(BigInteger foreignKey, String category) {
		
		return adi.selectAttachment(foreignKey, category);
	}
}
