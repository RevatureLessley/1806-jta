package com.revature.dal;

import com.revature.beans.EventTypeBean;

public interface ReimbursementTypeDao {
    EventTypeBean selectEventType(int type);
}
