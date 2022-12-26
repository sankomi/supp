package sanko.suppserver.user;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import sanko.suppserver.util.Returner;

@Mapper
public interface UserDao {
	
	public int createLogin(Returner returner, String username, String password);
	public Map<String, Object> getUser(String username);
	public Map<String, Object>[] listUsers();
	public Integer checkSupport(int userId);
	public void setSupport(int userId);
	public void unsetSupport(int userId);
	
}