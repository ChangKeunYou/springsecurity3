package www.spring.security.common.hibernate.dao.service;

import java.util.ArrayList;

public interface HibernateDaoService {
	
	public ArrayList<Object> selectListData(String hibernateQueryId , Object param);
	
	
	
}
