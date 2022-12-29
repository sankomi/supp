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
		
		PollList.addTicket(defer);
		
		defer.onTimeout(() -> PollList.setTicket(defer, false, "timeout", true));
		defer.onError((Throwable t) -> PollList.setTicket(defer, false, "error", true));
		
		Integer supportId = (Integer) request.getSession().getAttribute("userId");
		if (supportId == null || supportId == 0) PollList.setTicket(defer, false, "not logged in", true);
			
		Integer isSupport = (Integer) request.getSession().getAttribute("support");
		if (isSupport == null || isSupport == 0) PollList.setTicket(defer, false, "no permission", true);
		
		return defer;	
	}
	
	@Override
	public DeferredResult<String> checkContent(int ticketId, HttpServletRequest request) {
		DeferredResult<String> defer = new DeferredResult<>(60000l);
		
		PollList.addContent(ticketId, defer);
		
		defer.onTimeout(() -> PollList.setContent(ticketId, defer, false, "timeout", true));
		defer.onError((Throwable t) -> PollList.setContent(ticketId, defer, false, "error", true));
		
		Integer supportId = (Integer) request.getSession().getAttribute("userId");
		if (supportId == null || supportId == 0) PollList.setContent(ticketId, defer, false, "not logged in", true);
		
		return defer;	
	}
	
}