package www.spring.security.common.hibernate.dao.impl;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import www.spring.security.controller.ItemController;


@Repository
public class HibernateBaseDao {
	
	
	private SessionFactory sessionFactory;
		
	protected final Log logger = LogFactory.getLog(this.getClass());
	
	@Autowired(required=true)
	@Resource(name = "sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) {  
		logger.info("======Hibernate DI Class=======>" + sessionFactory.getClass().getName());
		this.sessionFactory = sessionFactory;
		/*
		if(this.sessionFactory == null){
			logger.info("null=>" + this.sessionFactory.hashCode());
		}else{
			logger.info("not null=>" + this.sessionFactory.hashCode());
		}
		*/
	}

	protected Session getCurrentSession(){
		return this.sessionFactory.getCurrentSession();
	}
	
	
	
	
}
