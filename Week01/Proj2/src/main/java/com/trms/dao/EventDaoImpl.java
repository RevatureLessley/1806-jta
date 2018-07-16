package com.trms.dao;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import com.trms.beans.Event;
import com.trms.util.SqlBuilder;

public class EventDaoImpl extends GenericDaoImpl<Event> implements Dao<Event, Integer> {
	final String tName = "Event", idField = "eventName";
	final Integer col = 5;
	SqlBuilder<Integer> sb = new SqlBuilder<>();

	Function<Object[], Event> objectReturnBehaviour = 
			item -> new Event(
					Integer.parseInt(item[0].toString()),
				    item[1].toString(),
				    Date.valueOf(item[2].toString()),
				    item[3].toString(),
				    item[4].toString(),
				    item[5].toString()
					);
			
	@Override
	public List<Event> selectAll() {
		return select(sb.sAll(tName), objectReturnBehaviour);
	}

	@Override
	public Event selectByID(Integer id) {
		return select(sb.sWhere(tName, idField, id), objectReturnBehaviour).get(0);
	}

	Function<Event, List<Object>> loadingBehaviour = 
			item -> Arrays.asList(
					item.getEventName(),
					item.getStartDate(),
					item.getEventType(),
					item.getLocation(),
					item.getDescription()
					);
	
	@Override
	public Boolean insertNew(Event t) {
		
		return callProcedure(t, sb.callInsert(tName, col), loadingBehaviour);
	}

	@Override
	public void udpateByGroup(List<Event> ts) {
		ts.forEach(item -> update(item));
	}

	@Override
	public Boolean update(Event t) {
		return callProcedure(t, sb.callUpdate(tName, t.getID(), col), loadingBehaviour);
	}

	@Override
	public Boolean deleteByID(Integer id) {
		return delete(tName, idField, id);
	}

}
