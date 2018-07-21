import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

import org.junit.Test;

import com.revature.bean.Event;
import com.revature.displaywrapper.EventStatus;
import com.revature.service.EventService;

public class TestStatus {

	@Test
	public void testStatus() {
		Event event;

		event = new Event();
		event.setEventDate(LocalDateTime.now().plusDays(20));

		assertEquals(EventStatus.New, EventService.getEventStatus(event));

		event.setSuperApprove(LocalDateTime.now());
		event.setHeadApprove(LocalDateTime.now());
		assertEquals(EventStatus.Processing, EventService.getEventStatus(event));

		event.setBencoApprove(LocalDateTime.now());
		assertEquals(EventStatus.Approved, EventService.getEventStatus(event));

		event = new Event();
		event.setEventDate(LocalDateTime.now().plusDays(10));
		assertEquals(EventStatus.Urgent, EventService.getEventStatus(event));
		
		event = new Event();
		event.setEventDate(LocalDateTime.now().minusDays(10));
		assertEquals(EventStatus.Pending, EventService.getEventStatus(event));
		
		event.setGrade(1);
		assertEquals(EventStatus.UnConfirmed, EventService.getEventStatus(event));
		
		event.setReimbursementConfirmation(LocalDateTime.now());
		assertEquals(EventStatus.Resolved, EventService.getEventStatus(event));
	}

}
