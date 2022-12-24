package sanko.suppserver.ticket;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import sanko.suppserver.util.Returner;

@Mapper
public interface TicketDao {
	
	public int createTicket(Returner returner, String title, int userId);
	public int addContent(Returner returner, int ticketId, int userId, String content);
	public Integer checkCreator(int ticketId, int userId);
	
}