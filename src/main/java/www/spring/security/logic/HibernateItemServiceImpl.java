package www.spring.security.logic;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import www.spring.security.common.hibernate.dao.service.HibernateDaoService;
//import org.hibernate.Transaction;



@Service
public class HibernateItemServiceImpl implements HibernateItemService {
	
	
	@Autowired
	private HibernateDaoService hibernateDaoService; 
	
	
	@Override
	public List<Item> getItemList(String hibernateQueryId) {
		// TODO Auto-generated method stub

		
		HashMap paramMap = new HashMap();
		
		paramMap.put("param1","test1");
		paramMap.put("param2","test2");
		
		
		List dataList = this.hibernateDaoService.selectListData(hibernateQueryId,paramMap);
		
		
		
		
		
		return dataList;
	}
	
	
	
}
