package sanko.suppserver.poll;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.async.DeferredResult;

public interface PollService {
	
	public DeferredResult<String> checkTicket(HttpServletRequest request);

}