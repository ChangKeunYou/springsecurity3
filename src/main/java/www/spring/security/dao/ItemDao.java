package www.spring.security.dao;

import java.io.InputStream;
import java.util.List;

import www.spring.security.logic.Item;

public interface ItemDao {

	List<Item> findAll();

	Item findByPrimaryKey(Integer itemId);

	List<Item> findByItemName(String itemName);

	void create(Item item);

	void udpate(Item item);

	void delete(Item item);  
	
	InputStream getPicture(Integer itemId);
}
