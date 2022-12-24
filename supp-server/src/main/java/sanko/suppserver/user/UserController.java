package sanko.suppserver.user;

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
@RequestMapping(path = "/user/", produces = "application/json")	
public class UserController {
	
	private UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping("/create/")
	public String createLogin(@RequestBody Map<String, Object> map, HttpServletRequest request) {
		return userService.createLogin(map, request);
	}

	@PostMapping("/login/")
	public String login(@RequestBody Map<String, Object> map, HttpServletRequest request) {
		return userService.login(map, request);
	}
	
	@GetMapping("/check/")
	public String checkLogin(HttpServletRequest request) {
		return userService.checkLogin(request);
	}
	
	@DeleteMapping("/logout/")
	public String logout(HttpServletRequest request) {
		return userService.logout(request);
	}

}