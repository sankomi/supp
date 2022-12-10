package sanko.suppserver;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

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

}
