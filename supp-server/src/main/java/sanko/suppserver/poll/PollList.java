package sanko.suppserver.poll;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import org.springframework.web.context.request.async.DeferredResult;

public class PollList {
	
	private static List<DeferredResult<String>> tickets = new ArrayList<>();
	private static Map<Integer, List<DeferredResult<String>>> contents = new HashMap<>();
	
	public static void addTicket(DeferredResult<String> defer) {
		tickets.add(defer);
	}
	
	public static void setTicket(DeferredResult<String> defer, boolean success, String message, boolean remove) {
		if (success) {
			defer.setResult("{\"result\": \"success\", \"message\": \"" + message + "\"}");
		} else {
			defer.setErrorResult("{\"result\": \"fail\", \"message\": \"" + message + "\"}");
		}
		if (remove) tickets.remove(defer);
	}
	
	private static void setTicket(DeferredResult<String> defer, boolean success, String message) {
		setTicket(defer, success, message, false);
	}
	
	public static void setTickets() {
		for (DeferredResult<String> defer : tickets) {
			setTicket(defer, true, "new ticket");
		}
		tickets.clear();
	}
	
	public static void addContent(int ticketId, DeferredResult<String> defer) {
		if (!contents.containsKey(ticketId)) contents.put(ticketId, new ArrayList<>());
		contents.get(ticketId).add(defer);
	}
	
	public static void removeContent(int ticketId, DeferredResult<String> defer) {
		if (contents.containsKey(ticketId)) contents.get(ticketId).remove(defer);
	}
	
	public static void clearContent(int ticketId) {
		if (contents.containsKey(ticketId)) contents.get(ticketId).clear();
	}
	
	public static void setContent(int ticketId, DeferredResult<String> defer, boolean success, String message, boolean remove) {
		if (success) {
			defer.setResult("{\"result\": \"success\", \"message\": \"" + message + "\"}");
		} else {
			defer.setErrorResult("{\"result\": \"fail\", \"message\": \"" + message + "\"}");
		}
		if (remove) removeContent(ticketId, defer);
	}
	
	private static void setContent(int ticketId, DeferredResult<String> defer, boolean success, String message) {
		setContent(ticketId, defer, success, message, false);
	}
	
	public static void setContents(int ticketId) {
		if (!contents.containsKey(ticketId)) return;
		
		for (DeferredResult<String> defer : contents.get(ticketId)) {
			setContent(ticketId, defer, true, "new content");
		}
		clearContent(ticketId);
	}
	
}