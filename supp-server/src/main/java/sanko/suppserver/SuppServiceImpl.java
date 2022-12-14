package sanko.suppserver;

import org.springframework.stereotype.Service;

import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

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
	
	@Override
	public void createLogin(String username, String password) {
		Pbkdf2PasswordEncoder encoder = new Pbkdf2PasswordEncoder("muchsecret", 1000, 256);
		String encodedPassword = encoder.encode(password);
		suppDao.createLogin(username, encodedPassword);
	}
	
	@Override
	public boolean checkLogin(String username, String password) {
		Pbkdf2PasswordEncoder encoder = new Pbkdf2PasswordEncoder("muchsecret", 1000, 256);
		String encodedPassword = suppDao.getPassword(username);
		return encoder.matches(password, encodedPassword);
	}

}
