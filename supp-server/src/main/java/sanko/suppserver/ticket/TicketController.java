package sanko.suppserver.ticket;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@CrossOrigin(origins = {"http://localhost"}, allowCredentials = "true")
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
	
}