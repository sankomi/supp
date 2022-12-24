package sanko.suppserver.user;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface UserService {
	
	public String createLogin(Map<String, Object> map, HttpServletRequest request);
	public String login(Map<String, Object> map, HttpServletRequest request);
	public String checkLogin(HttpServletRequest request);
	public String logout(HttpServletRequest request);

}