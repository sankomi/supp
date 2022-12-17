package sanko.suppserver;

import java.util.Map;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

@Service
public class SuppServiceImpl implements SuppService {
	
	private final SuppDao suppDao;
	
	public SuppServiceImpl(SuppDao suppDao) {
		this.suppDao = suppDao;
	}
	
	@Override
	public String createLogin(Map<String, Object> user, HttpServletRequest request) {
		String username = (String) user.get("username");
		String password = (String) user.get("password");
		
		if (username == null || username.isEmpty()) return "{\"result\": \"fail\", \"message\": \"no username and/or password\"}";
		if (password == null || password.isEmpty()) return "{\"result\": \"fail\", \"message\": \"no username and/or password\"}";
		
		Integer userId = suppDao.getUserId(username);
		
		if (userId != null) return "{\"result\": \"fail\", \"message\": \"username already exists\"}";
		
		Pbkdf2PasswordEncoder encoder = new Pbkdf2PasswordEncoder("muchsecret", 1000, 256);
		String encodedPassword = encoder.encode(password);
		suppDao.createLogin(username, encodedPassword);
		
		request.getSession().setAttribute("login", username);
		return "{\"result\": \"success\"}";
	}
	
	@Override
	public String login(Map<String, Object> user, HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		String username = (String) user.get("username");
		String password = (String) user.get("password");
		
		if (username == null || username.isEmpty()) return "{\"result\": \"fail\", \"message\": \"no username and/or password\"}";
		if (password == null || password.isEmpty()) return "{\"result\": \"fail\", \"message\": \"no username and/or password\"}";
		
		boolean login = false;
		if (session.getAttribute("login") == null) {			
			Integer userId = suppDao.getUserId(username);
			if (userId == null) return "{\"result\": \"fail\", \"message\": \"username does not exist\"}";
		
			Pbkdf2PasswordEncoder encoder = new Pbkdf2PasswordEncoder("muchsecret", 1000, 256);
			String encodedPassword = suppDao.getPassword(username);
			login = encoder.matches(password, encodedPassword);
			if (login) {
				request.getSession().setAttribute("login", username);
				return "{\"result\": \"success\"}";
			} else {
				return "{\"result\": \"fail\", \"message\": \"incorrect password\"}";
			}
		} else {
			return "{\"result\": \"fail\", \"message\": \"already logged in as " + (String) session.getAttribute("login") + "\"}";
		}
		
	}

	@Override
	public String checkLogin(HttpServletRequest request) {
		String username = (String) request.getSession().getAttribute("login");
		if (username == null || username.isEmpty()) {
			return "{\"result\": \"fail\"}";
		} else {
			return "{\"result\": \"success\", \"username\": \"" + username + "\"}";
		}
	}

	@Override
	public String logout(HttpServletRequest request) {
		request.getSession().removeAttribute("login");
		return "{\"result\": \"success\"}";
	}
	
}
