package sanko.suppserver.user;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import sanko.suppserver.util.Returner;

@Mapper
public interface UserDao {
	
	public int createLogin(Returner returner, String username, String password);
	public Integer getUserId(String username);
	public String getPassword(String username);
	
}