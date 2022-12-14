package sanko.suppserver;

import java.util.Map;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class SuppController {
	
	private SuppService suppService;

	public SuppController(SuppService suppService) {
		this.suppService = suppService;
	}
	
	@GetMapping("/")
	public String something(HttpServletRequest request) {
		System.out.println(request.getSession().getAttribute("hello"));
		request.getSession().setAttribute("hello", "world");
		return suppService.doSomething();
	}
	
	@GetMapping(path = "/{id}/{number}")
    public SomeObject read(
		@PathVariable String id,
		@PathVariable float number,
		@RequestParam(value = "integer", required = false, defaultValue = "0") int integer //defaultValue is String
	) {
        return new SomeObject(id, number, integer);
    }
	
	@PostMapping("/create")
	public String createLogin(@RequestBody Map<String, Object> user) {
		suppService.createLogin((String) user.get("username"), (String) user.get("password"));
		
		return "";
	}

	@PostMapping("/login")
	public Map<String, Object> login(@RequestBody Map<String, Object> user) {
		boolean login = suppService.checkLogin((String) user.get("username"), (String) user.get("password"));
		
		Map<String, Object> map = new HashMap<>();
		map.put("result", login? "success": "fail");
		return map;
	}

}
