package com.revature.trms.service;

import com.revature.trms.bean.EventType;
import com.revature.trms.dao.EventTypeDao;
import com.revature.trms.dao.EventTypeDaoJDBC;
import java.util.List;
import org.codehaus.jackson.map.ObjectMapper;

public class EventTypeService {

  public static String getAllEventTypes() {
    List<EventType> eventTypes = getEventTypes();
    ObjectMapper mapper = new ObjectMapper();
    try {
      return mapper.writeValueAsString(eventTypes);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return null;
  }

  private static List<EventType> getEventTypes() {
    EventTypeDao etd = new EventTypeDaoJDBC();
    return etd.selectAllEventTypes();
  }
}
