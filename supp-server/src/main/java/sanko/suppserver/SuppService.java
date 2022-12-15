package sanko.suppserver;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface SuppService {
	
	public String createLogin(Map<String, Object> user);
	public String login(Map<String, Object> user, HttpServletRequest request);
	public String checkLogin(HttpServletRequest request);
	public String logout(HttpServletRequest request);

}
