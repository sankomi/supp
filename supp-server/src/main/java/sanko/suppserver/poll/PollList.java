package sanko.suppserver.poll;

import java.util.List;
import java.util.ArrayList;

import org.springframework.web.context.request.async.DeferredResult;

public class PollList {
	
	private static List<DeferredResult<String>> defers = new ArrayList<>();
	
	public static void add(DeferredResult<String> defer) {
		defers.add(defer);
	}
	
	public static void setSingleResult(DeferredResult<String> defer, boolean success, String message, boolean remove) {
		if (success) {
			defer.setResult("{\"result\": \"success\", \"message\": \"" + message + "\"}");
		} else {
			defer.setErrorResult("{\"result\": \"fail\", \"message\": \"" + message + "\"}");
		}
		if (remove) defers.remove(defer);
	}
	
	private static void setResult(DeferredResult<String> defer, boolean success, String message) {
		setSingleResult(defer, success, message, false);
	}
	
	public static void setResults() {
		for (DeferredResult<String> defer : defers) {
			setResult(defer, true, "new ticket");
		}
		defers.clear();
	}
	
}