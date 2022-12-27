package sanko.suppserver.ticket;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import sanko.suppserver.util.Returner;

@Mapper
public interface TicketDao {
	
	public int createTicket(Returner returner, String title, int userId);
	public int addContent(Returner returner, int ticketId, int userId, String content);
	public Integer checkAccess(int ticketId, int userId);
	public Map<String, Object>[] listTickets(int userId);
	public Map<String, Object>[] listContents(int ticketId);
	public Map<String, Object> getInfo(int ticketId);
	public int openTicket(int ticketId);
	public int closeTicket(int ticketId);
	
}