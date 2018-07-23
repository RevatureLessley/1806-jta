package com.revature.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.revature.bean.Event;
import com.revature.bean.GradeValue;
import com.revature.dao.EventDaoImpl;
import com.revature.dao.NotificationDao;
import com.revature.displaywrapper.EventDisplay;
import com.revature.displaywrapper.EventPhase;
import com.revature.displaywrapper.EventStatus;
import com.revature.utils.MasterLogger;
import com.revature.utils.StringManip;

public class EventService {

	// public static EventDisplay getEventWrapper(Event event) {
	// return new EventDisplay(event);
	// }

	/**
	 * Get a JSON string containing displayable data for all events associated with
	 * a given user
	 * 
	 * @param userId
	 * @return
	 */
	public static String selectUserEvents(Integer userId) {
		List<Event> events = new EventDaoImpl().selectUserEvents(userId);
		List<EventDisplay> wrappedEvents = new ArrayList<>();

		for (Event ev : events)
			wrappedEvents.add(new EventDisplay(ev));

		return StringManip.getJSONString(wrappedEvents);
	}

	/**
	 * Get a JSON string containing displayable data for all events associated with
	 * a given user. The events are filtered out by status
	 * 
	 * @param userId
	 * @return
	 */
	public static String selectUserEvents(Integer userId, String filter) {
		List<Event> events = new EventDaoImpl().selectUserEvents(userId);
		List<EventDisplay> wrappedEvents = new ArrayList<>();

		for (Event ev : events)
			wrappedEvents.add(new EventDisplay(ev));

		EventService.eventDisplayCheckUpdatesFor(wrappedEvents, userId);

		Predicate<EventDisplay> p = getFilterPredicate(filter);
		wrappedEvents = (List<EventDisplay>) wrappedEvents.stream().filter(p).collect(Collectors.toList());

		return StringManip.getJSONString(wrappedEvents);
	}

	/**
	 * Get a JSON string containing displayable data a specific event belonging to a
	 * specific user
	 * 
	 * @param userId
	 * @return
	 */
	public static String selectUserEvent(Integer userId, Integer eventId) {
		Event event = new EventDaoImpl().selectById(eventId);

		EventDisplay evD = new EventDisplay(event);

		return StringManip.getJSONString(evD);
	}

	/**
	 * Get a JSON string containing displayable data for all events belonging to
	 * subordinatinates of a given user
	 * 
	 * @param userId
	 * @return
	 */
	public static String selectSubordinateEvents(Integer userId) {
		List<Event> events = new EventDaoImpl().selectSubordinateEvents(userId);
		List<EventDisplay> wrappedEvents = new ArrayList<>();

		for (Event ev : events)
			wrappedEvents.add(new EventDisplay(ev));

		return StringManip.getJSONString(wrappedEvents);
	}

	/**
	 * Get a JSON string containing displayable data for all events belonging to
	 * subordinatinates of a given user. Events are filtered out by status
	 * 
	 * @param userId
	 * @param filter
	 * @return
	 */
	public static String selectSubordinateEvents(Integer userId, String filter) {
		List<Event> events = new EventDaoImpl().selectSubordinateEvents(userId);
		List<EventDisplay> wrappedEvents = new ArrayList<>();

		for (Event ev : events)
			wrappedEvents.add(new EventDisplay(ev));

		EventService.eventDisplayCheckUpdatesFor(wrappedEvents, userId);

		Predicate<EventDisplay> p = getFilterPredicate(filter);
		wrappedEvents = (List<EventDisplay>) wrappedEvents.stream().filter(p).collect(Collectors.toList());

		return StringManip.getJSONString(wrappedEvents);
	}

	/**
	 * Obtains a predicate for filtering out events based on status
	 * 
	 * @param filter
	 * @return
	 */
	private static Predicate<EventDisplay> getFilterPredicate(String filter) {
		Predicate<EventDisplay> p;

		switch (filter.toLowerCase()) {
		case "preapproval":
			p = e -> (e.getPhase() == EventPhase.Approval);
			break;
		case "unconfirmed":
			p = e -> (e.getPhase() == EventPhase.Confirmation);
			break;
		case "resolved":
			p = e -> (e.getPhase() == EventPhase.Resolved);
			break;
		case "updated":
			p = (e -> e.isUpdated());
			break;
		default:
			p = e -> (e.getPhase() != EventPhase.Resolved);
		}

		return p;
	}

	/**
	 * Grants approval to an event from the given user
	 * 
	 * @param eventId
	 * @param userId
	 */
	public static void eventUpdateApprovalFrom(Integer eventId, Integer userId) {
		EventDaoImpl eventDao = new EventDaoImpl();
		eventDao.eventUpdateApprovalFrom(eventId, userId);

		MasterLogger.info(EventService.class, "event " + eventId + " has been approved by " + userId);
	}

	/**
	 * Returns the status of a given event based on whether the event has been
	 * approved, how close it is to the date (or whether it is after the event date
	 * has occurred), whether final information has been provided, and whether it
	 * has been ultimately confirmed.
	 * 
	 * @param event
	 * @return
	 */
	public static EventStatus getEventStatus(Event event) {

		EventStatus status = EventStatus.New;

		if (event.getEventDate().isBefore(LocalDateTime.now())) {
			// event date has passed
			if (event.getBencoApprove() == null) {
				status = EventStatus.Urgent;
			} else if (event.getReimbursementConfirmation() != null) {
				status = EventStatus.Resolved;
			} else if (event.getGrade() != null && event.getGrade() != 0) {
				status = EventStatus.UnConfirmed;
			} else {
				status = EventStatus.Pending;
			}
		} else {
			// event date is approaching
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

	/**
	 * Gets the phase of an event; there are several main phases that are based on
	 * the event's status
	 * 
	 * @param event
	 * @return
	 */
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

	/**
	 * Change the award amount of a specific event by a given user to a specified
	 * amount
	 * 
	 * @param userId
	 * @param eventId
	 * @param message
	 * @param amount
	 */
	public static void eventChangeAward(Integer userId, Integer eventId, String message, Double amount) {

		new EventDaoImpl().eventChangeAward(eventId, amount);

		NotificationService.eventAddNotification(eventId, userId,
				"The awarded amount has been changed to " + StringManip.formatCurrency(amount) + " for event "
						+ EventService.getEventName(eventId) + " -- " + message);

		MasterLogger.info(EventService.class,
				"event " + eventId + " reimbursement amount changed to " + amount + " by employee " + userId);
	}

	/**
	 * Sets the grade of an event
	 * 
	 * @param userId
	 * @param eventId
	 * @param grade
	 */
	public static void eventSubmitGrade(Integer userId, Integer eventId, Integer grade) {
		new EventDaoImpl().eventSubmitGrade(userId, eventId, grade);

		MasterLogger.info(EventService.class, "event " + eventId + " grade set to "
				+ FixedDataService.getGradeValue(grade).getName() + " by employee " + userId);
	}

	/**
	 * Confirms an event thus setting its status to resolved
	 * 
	 * @param eventId
	 * @param userId
	 */
	public static void eventConfirm(Integer eventId, Integer userId) {
		new EventDaoImpl().eventConfirm(eventId, userId);

		MasterLogger.info(EventService.class, "event " + eventId + " confirmed by employee " + userId);
	}

	/**
	 * Gets the name of a grade for an event
	 * 
	 * @param grade
	 * @return
	 */
	public static String getEventGrade(Integer grade) {

		GradeValue gv = FixedDataService.getGradeValue(grade);
		if (gv == null)
			return "ungraded";
		else
			return gv.getName();

	}

	/**
	 * Gets the name of a given event
	 * 
	 * @param eventId
	 * @return
	 */
	public static String getEventName(Integer eventId) {
		return new EventDaoImpl().selectById(eventId).getName();
	}

	/**
	 * Iterates through a list of events and determines whether the events have
	 * updates. If they do, the display objects are modified.
	 * 
	 * @param ls
	 * @param userId
	 */
	public static void eventDisplayCheckUpdatesFor(List<EventDisplay> ls, Integer userId) {
		Map<Integer, Integer> evNotifMap = new NotificationDao().selectUserEventUpdatedMap(userId);

		for (EventDisplay e : ls) {

			if (evNotifMap.get(e.getId()) == 1)
				e.setUpdated(true);

		}

	}

}
