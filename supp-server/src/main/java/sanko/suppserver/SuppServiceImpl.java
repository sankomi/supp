package sanko.suppserver;

import org.springframework.stereotype.Service;

@Service
public class SuppServiceImpl implements SuppService {
	
	private final SuppDao suppDao;
	
	public SuppServiceImpl(SuppDao suppDao) {
		this.suppDao = suppDao;
	}
	
	@Override
	public String doSomething() {
		return "service";
	}

}
