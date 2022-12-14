package sanko.suppserver;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SuppDao {
	
	public void createLogin(String username, String password);
	public String getPassword(String username);
	
}
