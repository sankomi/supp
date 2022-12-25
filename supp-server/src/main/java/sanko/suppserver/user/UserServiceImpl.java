package sanko.suppserver.user;

import java.util.Map;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import sanko.suppserver.util.Returner;

@Service
public class UserServiceImpl implements UserService {
	
	private final UserDao userDao;
	
	public UserServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Override
	public String createLogin(Map<String, Object> map, HttpServletRequest request) {
		String username = (String) map.get("username");
		String password = (String) map.get("password");
		
		if (username == null || username.isEmpty()) return "{\"result\": \"fail\", \"message\": \"no username and/or password\"}";
		if (password == null || password.isEmpty()) return "{\"result\": \"fail\", \"message\": \"no username and/or password\"}";
		
		Integer userId = userDao.getUserId(username);
		
		if (userId != null) return "{\"result\": \"fail\", \"message\": \"username already exists\"}";
		
		Pbkdf2PasswordEncoder encoder = new Pbkdf2PasswordEncoder("muchsecret", 1000, 256);
		String encodedPassword = encoder.encode(password);
		Returner returner = new Returner();
		int inserted = userDao.createLogin(returner, username, encodedPassword);
		
		if (inserted == 0) {
			return "{\"result\": \"fail\", \"message\": \"error\"}";
		} else {
			userId = returner.getId();
			request.getSession().setAttribute("username", username);
			request.getSession().setAttribute("userId", userId);
			
			if (userId == 1) userDao.setSupport(userId);
			
			return "{\"result\": \"success\"}";
		}
	}
	
	@Override
	public String login(Map<String, Object> map, HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		String username = (String) map.get("username");
		String password = (String) map.get("password");
		
		if (username == null || username.isEmpty()) return "{\"result\": \"fail\", \"message\": \"no username and/or password\"}";
		if (password == null || password.isEmpty()) return "{\"result\": \"fail\", \"message\": \"no username and/or password\"}";
		
		boolean login = false;
		if (session.getAttribute("username") == null) {			
			Integer userId = userDao.getUserId(username);
			if (userId == null) return "{\"result\": \"fail\", \"message\": \"username does not exist\"}";
		
			Pbkdf2PasswordEncoder encoder = new Pbkdf2PasswordEncoder("muchsecret", 1000, 256);
			String encodedPassword = userDao.getPassword(username);
			login = encoder.matches(password, encodedPassword);
			if (login) {
				request.getSession().setAttribute("username", username);
				request.getSession().setAttribute("userId", userId);
				return "{\"result\": \"success\", \"username\": \"" + username + "\"}";
			} else {
				return "{\"result\": \"fail\", \"message\": \"incorrect password\"}";
			}
		} else {
			return "{\"result\": \"fail\", \"message\": \"already logged in as " + (String) session.getAttribute("username") + "\"}";
		}
		
	}

	@Override
	public String checkLogin(HttpServletRequest request) {
		String username = (String) request.getSession().getAttribute("username");
		Integer userId = (Integer) request.getSession().getAttribute("userId");
		if (userId == null || userId == 0) {
			return "{\"result\": \"fail\"}";
		} else {
			return "{\"result\": \"success\", \"username\": \"" + username + "\"}";
		}
	}

	@Override
	public String logout(HttpServletRequest request) {
		request.getSession().removeAttribute("username");
		request.getSession().removeAttribute("userId");
		return "{\"result\": \"success\"}";
	}
	
	@Override
	public Map<String, Object> listUsers(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<>();
		
		Integer supportId = (Integer) request.getSession().getAttribute("userId");
		if (supportId == null || supportId == 0) {
			map.put("result", "fail");
			map.put("message", "not logged in");
			return map;
		}
		Integer isSupport = userDao.checkSupport(supportId);
		if (isSupport == null || isSupport == 0) {
			map.put("result", "fail");
			map.put("message", "no permission");
			return map;
		}
		
		map.put("result", "success");
		map.put("users", userDao.listUsers());
		return map;
	}
	
	@Override
	public String setSupport(Map<String, Object> map, HttpServletRequest request) {
		Integer supportId = (Integer) request.getSession().getAttribute("userId");
		if (supportId == null || supportId == 0) return "{\"result\": \"fail\", \"message\": \"not logged in\"}";
		
		Integer userId = Integer.parseInt(map.get("userId").toString());
		Integer support = Integer.parseInt(map.get("support").toString());
		
		Integer isSupport = userDao.checkSupport(supportId);
		if (isSupport == null || isSupport == 0) return "{\"result\": \"fail\", \"message\": \"no permission\"}";
		
		if (support == 0) userDao.unsetSupport(userId);
		else userDao.setSupport(userId);
		
		return "{\"result\": \"success\"}";
	}
	
}