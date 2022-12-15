package sanko.suppserver;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SuppDao {
	
	public int createLogin(String username, String password);
	public Integer getUserId(String username);
	public String getPassword(String username);
	
}
