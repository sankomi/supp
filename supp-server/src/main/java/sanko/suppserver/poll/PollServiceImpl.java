package sanko.suppserver.poll;

import java.util.List;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

@Service
public class PollServiceImpl implements PollService {
	
	@Override
	public DeferredResult<String> checkTicket(HttpServletRequest request) {
		DeferredResult<String> defer = new DeferredResult<>(60000l);
		
		PollList.add(defer);
		
		defer.onTimeout(() -> PollList.setSingleResult(defer, false, "timeout", true));
		defer.onError((Throwable t) -> PollList.setSingleResult(defer, false, "error", true));
		
		Integer supportId = (Integer) request.getSession().getAttribute("userId");
		if (supportId == null || supportId == 0) PollList.setSingleResult(defer, false, "not logged in", true);
			
		Integer isSupport = (Integer) request.getSession().getAttribute("support");
		if (isSupport == null || isSupport == 0) PollList.setSingleResult(defer, false, "no permission", true);
		
		return defer;	
	}
	
}