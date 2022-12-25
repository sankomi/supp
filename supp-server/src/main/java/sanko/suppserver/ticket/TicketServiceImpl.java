package sanko.suppserver.ticket;

import java.util.Map;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import sanko.suppserver.util.Returner;

@Service
public class TicketServiceImpl implements TicketService {
	
	private final TicketDao ticketDao;
	
	public TicketServiceImpl(TicketDao ticketDao) {
		this.ticketDao = ticketDao;
	}
	
	@Override
	public String createTicket(Map<String, Object> map, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Integer userId = (Integer) session.getAttribute("userId");
		if (userId == null || userId == 0) return "{\"result\": \"fail\", \"message\": \"not logged in\"}";
		
		String title = (String) map.get("title");
		String content = (String) map.get("content");
		
		if (title == null || title.isEmpty()) return "{\"result\": \"fail\", \"message\": \"no title\"}";
		if (content == null || content.isEmpty()) return "{\"result\": \"fail\", \"message\": \"no content\"}";
		
		Returner returner = new Returner();
		int inserted = ticketDao.createTicket(returner, title, userId);
		if (inserted == 0) return "{\"result\": \"fail\", \"message\": \"error\"}";
		
		int ticketId = returner.getId();
		inserted = ticketDao.addContent(returner, ticketId, userId, content);
		if (inserted == 0) return "{\"result\": \"fail\", \"message\": \"error\"}";
		return "{\"result\": \"success\", \"ticketId\": " + ticketId + "}";
	}
	
	@Override
	public String addContent(Map<String, Object> map, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Integer userId = (Integer) session.getAttribute("userId");
		if (userId == null || userId == 0) return "{\"result\": \"fail\", \"message\": \"not logged in\"}";
		
		Integer ticketId = Integer.parseInt(map.get("ticketId").toString());
		String content = (String) map.get("content");
		
		if (ticketId == null || ticketId == 0) return "{\"result\": \"fail\", \"message\": \"no ticket id\"}";
		if (content == null || content.isEmpty()) return "{\"result\": \"fail\", \"message\": \"no content\"}";
		
		Integer check = ticketDao.checkAccess(ticketId, userId);
		if (check == null || check == 0) return "{\"result\": \"fail\", \"message\": \"incorrect ticket id\"}";
		
		Returner returner = new Returner();
		int inserted = ticketDao.addContent(returner, ticketId, userId, content);
		if (inserted == 0) return "{\"result\": \"fail\", \"message\": \"error\"}";
		return "{\"result\": \"success\"}";
	}
	
	@Override
	public Map<String, Object> listTickets(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Integer userId = (Integer) session.getAttribute("userId");
		
		Map<String, Object> map = new HashMap<>();
		if (userId == null || userId == 0) {
			map.put("result", "fail");
			map.put("message", "not logged in");
			return map;
		}
		
		map.put("result", "success");
		map.put("tickets", ticketDao.listTickets(userId));
		return map;
	}
	
	@Override
	public Map<String, Object> listContents(int ticketId, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<>();
		if (ticketId == 0) {
			map.put("result", "fail");
			map.put("message", "incorrect ticket id");
			return map;
		}
		
		HttpSession session = request.getSession();
		Integer userId = (Integer) session.getAttribute("userId");
		
		if (userId == null || userId == 0) {
			map.put("result", "fail");
			map.put("message", "not logged in");
			return map;
		}
		
		Integer check = ticketDao.checkAccess(ticketId, userId);
		if (check == null || check == 0) {
			map.put("result", "fail");
			map.put("message", "incorrect ticket id");
			return map;
		}
		
		map.put("result", "success");
		map.put("title", ticketDao.getTitle(ticketId));
		map.put("contents", ticketDao.listContents(ticketId));
		return map;
	}
	
}