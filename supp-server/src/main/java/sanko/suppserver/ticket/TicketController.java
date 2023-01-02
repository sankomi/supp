package sanko.suppserver.ticket;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@CrossOrigin(origins = {"http://localhost:3001"}, allowCredentials = "true")
@RequestMapping(path = "/ticket/", produces = "application/json")	
public class TicketController {
	
	private TicketService ticketService;

	public TicketController(TicketService ticketService) {
		this.ticketService = ticketService;
	}
	
	@PostMapping("/create/")
	public String createTicket(@RequestBody Map<String, Object> map, HttpServletRequest request) {
		return ticketService.createTicket(map, request);
	}
	
	@PostMapping("/add/")
	public String addContent(@RequestBody Map<String, Object> map, HttpServletRequest request) {
		return ticketService.addContent(map, request);
	}
	
	@GetMapping("/list/")
	public Map<String, Object> listTickets(HttpServletRequest request) {
		return ticketService.listTickets(request);
	}
	
	@GetMapping("/content/")
	public Map<String, Object> listContents(
		@RequestParam(value = "ticketId") int ticketId,
		HttpServletRequest request
	) {
		return ticketService.listContents(ticketId, request);
	}
	
	@PutMapping("/close/")
	public String closeTicket(@RequestBody Map<String, Object> map, HttpServletRequest request) {
		return ticketService.closeTicket(map, request);
	}
	
}
