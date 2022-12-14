package sanko.suppserver;

public interface SuppService {
	
	public String doSomething();
	public void createLogin(String username, String password);
	public boolean checkLogin(String username, String password);

}
