package Project1.Beans.Approval;

import java.util.*;
import Project1.*;
import Project1.Beans.*;

public class Approval {
	private String type;
	private boolean isApproved;
	private String reason;
	private HashMap<String, Attachment> attachments;
	private HashMap<String, Attachment> additionalInfo;
	
	/**
	 * @param type
	 * @param gradingFormat
	 * @param isApproved
	 * @param reason
	 */
	public Approval(String type, boolean isApproved, String reason) {
		this.type = type;
		this.isApproved = isApproved;
		this.reason = reason;
		attachments = new HashMap<>();
		additionalInfo = new HashMap<>();
	}

	public String getType() {
		return type;
	}

	public void setType(Integer index) {
		type = ApprovalType.retrieveType(index);
	}

	public boolean isApproved() {
		return isApproved;
	}

	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
	public Attachment retrieveAttachment(String name) {
		return attachments.get(name);
	}

	public Attachment insertAttachment(String name, Attachment attachment) {
		return attachments.put(name, attachment);
	}
	
	public Attachment retrieveAdditionalInfo(String name) {
		return additionalInfo.get(name);
	}

	public Attachment insertAdditionalInfo(String name, 
										   Attachment attachment) {
		return additionalInfo.put(name, attachment);
	}
}