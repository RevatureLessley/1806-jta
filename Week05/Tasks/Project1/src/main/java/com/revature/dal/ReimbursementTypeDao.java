package com.revature.dal;

import com.revature.beans.EventTypeBean;

/**
 * Interface for accessing the database on behalf of Event Types
 */
public interface ReimbursementTypeDao {
    EventTypeBean selectEventType(int type);
}
