package www.spring.security.common.hibernate.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import www.spring.security.common.hibernate.dao.service.HibernateDaoService;


@Repository(value="hibernateDaoService")
public class HibernateDaoServiceImpl extends HibernateBaseDao implements HibernateDaoService  {


	@Override
	public ArrayList<Object> selectListData(String hibernateQueryId, Object param) {
		// TODO Auto-generated method stub
		
		logger.info("query====>" + hibernateQueryId + " : param====>" + param.toString());
		
		//super.getCurrentSession()
		//Transaction tx  = super.getCurrentSession().beginTransaction();
		Query query =	super.getCurrentSession().getNamedQuery(hibernateQueryId);
		List list = query.list();
		System.out.println(list.size());
		System.out.println(list.toString());
		logger.info("================통과===============");
		//tx.commit();
		return null;
	}
	
	
	
}
