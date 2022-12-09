package sanko.suppserver;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class SuppController {
	
	private SuppService suppService;

	public SuppController(SuppService suppService) {
		this.suppService = suppService;
	}
	
	@GetMapping("/")
	public String something() {
		return suppService.doSomething();
	}

}
