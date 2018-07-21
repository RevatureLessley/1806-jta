package com.revature.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.revature.bean.Event;
import com.revature.dao.EventDaoImpl;
import com.revature.displaywrapper.EventDisplay;
import com.revature.displaywrapper.EventPhase;
import com.revature.displaywrapper.EventStatus;
import com.revature.utils.StringManip;

public class EventService {

	public static EventDisplay getEventWrapper(Event event) {
		return new EventDisplay(event);
	}

	public static String selectUserEvents(Integer userId) {
		List<Event> events = new EventDaoImpl().selectUserEvents(userId);
		List<EventDisplay> wrappedEvents = new ArrayList<>();

		for (Event ev : events)
			wrappedEvents.add(new EventDisplay(ev));

		return StringManip.getJSONString(wrappedEvents);
	}

	public static String selectUserEvent(Integer userId, Integer eventId) {
		Event event = new EventDaoImpl().selectById(eventId);

		EventDisplay evD = new EventDisplay(event);

		return StringManip.getJSONString(evD);
	}

	public static String selectSubordinateEvents(Integer userId) {
		List<Event> events = new EventDaoImpl().selectSubordinateEvents(userId);
		List<EventDisplay> wrappedEvents = new ArrayList<>();

		for (Event ev : events)
			wrappedEvents.add(new EventDisplay(ev));

		return StringManip.getJSONString(wrappedEvents);
	}

	public static void eventUpdateApprovalFrom(Integer eventId, Integer userId) {
		EventDaoImpl eventDao = new EventDaoImpl();
		eventDao.eventUpdateApprovalFrom(eventId, userId);
	}

	public static EventStatus getEventStatus(Event event) {

		EventStatus status = EventStatus.New;

		if (event.getEventDate().isBefore(LocalDateTime.now())) {
			// event has passed
			if (event.getReimbursementConfirmation() != null) {
				status = EventStatus.Resolved;
			} else if (event.getGrade() != null) {
				status = EventStatus.UnConfirmed;
			} else {
				status = EventStatus.Pending;
			}
		} else {
			// event is approaching
			if (event.getBencoApprove() != null) {
				status = EventStatus.Approved;
			} else if (LocalDateTime.now().isAfter(event.getEventDate().minusDays(14))) {
				status = EventStatus.Urgent;
			} else if (event.getSuperApprove() != null || event.getHeadApprove() != null) {
				status = EventStatus.Processing;
			}
		}

		return status;
	}

	public static EventPhase getPhase(Event event) {
		EventStatus status = getEventStatus(event);

		switch (status) {
		case Resolved:
			return EventPhase.Resolved;
		case UnConfirmed:
		case Pending:
			return EventPhase.Confirmation;
		case Approved:
			return EventPhase.Wait;
		default:
			return EventPhase.Approval;
		}
	}

}
