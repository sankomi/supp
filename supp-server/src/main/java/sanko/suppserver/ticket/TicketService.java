package sanko.suppserver.ticket;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface TicketService {
	
	public String createTicket(Map<String, Object> map, HttpServletRequest request);
	public String addContent(Map<String, Object> map, HttpServletRequest request);

}