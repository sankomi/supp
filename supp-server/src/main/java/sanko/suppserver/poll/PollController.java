package sanko.suppserver.poll;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.context.request.async.DeferredResult;

@RestController
@CrossOrigin(origins = {"http://localhost"}, allowCredentials = "true")
@RequestMapping(path = "/poll/", produces = "application/json")	
public class PollController {
	
	private PollService pollService;

	public PollController(PollService pollService) {
		this.pollService = pollService;
	}
	
	@GetMapping("/check/")
	public DeferredResult<String> checkTicket(HttpServletRequest request) {
		return pollService.checkTicket(request);
	}
	
}