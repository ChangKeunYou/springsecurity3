package www.spring.security.logic;

import java.util.List;

import javax.transaction.Transactional;

public interface HibernateItemService {
	
	@Transactional
	public List<Item> getItemList(String hibernateQueryId);
	
}
