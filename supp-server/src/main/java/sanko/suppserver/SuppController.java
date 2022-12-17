package sanko.suppserver;

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
@RequestMapping(path = "/", produces = "application/json")	
public class SuppController {
	
	private SuppService suppService;

	public SuppController(SuppService suppService) {
		this.suppService = suppService;
	}
	
	@PostMapping("/create")
	public String createLogin(@RequestBody Map<String, Object> user, HttpServletRequest request) {
		return suppService.createLogin(user, request);
	}

	@PostMapping("/login")
	public String login(@RequestBody Map<String, Object> user, HttpServletRequest request) {
		return suppService.login(user, request);
	}
	
	@GetMapping("/check")
	public String checkLogin(HttpServletRequest request) {
		return suppService.checkLogin(request);
	}
	
	@DeleteMapping("/logout")
	public String logout(HttpServletRequest request) {
		return suppService.logout(request);
	}

}
