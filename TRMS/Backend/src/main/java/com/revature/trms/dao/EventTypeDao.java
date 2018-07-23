package com.revature.trms.dao;

import com.revature.trms.bean.EventType;
import java.util.List;

public interface EventTypeDao {

  List<EventType> selectAllEventTypes();
}
