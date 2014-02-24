package www.spring.security.logic;

import java.util.List;

public interface HibernateItemService {
	
	public List<Item> getItemList(String hibernateQueryId);
	
}
