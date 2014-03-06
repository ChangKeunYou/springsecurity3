package www.spring.security.logic;

import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import www.spring.security.dao.ItemDao;

@Service
//@Transactional
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemDao itemDao;
	
	
	public void deleteItem(Item item) {
		this.itemDao.delete(item);
	}

	public void entryItem(Item item) {
		this.itemDao.create(item);
	}

	public Item getItemByItemId(Integer itemId) {
		return this.itemDao.findByPrimaryKey(itemId);
	}

	public List<Item> getItemByItemName(String itemName) {
		return this.itemDao.findByItemName(itemName);
	}

	public List<Item> getItemList() {
		return this.itemDao.findAll();
	}

	public void updateItem(Item item) {
		
		this.itemDao.udpate(item);
		
		//throw new RuntimeException("transaction테스트!!");
		
	}

	public InputStream getPicture(Integer itemId) {
		return this.itemDao.getPicture(itemId);
	}

}
