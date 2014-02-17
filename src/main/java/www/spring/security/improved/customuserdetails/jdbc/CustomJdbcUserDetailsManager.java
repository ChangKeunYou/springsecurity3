package www.spring.security.improved.customuserdetails.jdbc;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.sql.DataSource;

import org.hibernate.metamodel.domain.Superclass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextException;
import org.springframework.dao.DataAccessException;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import egovframework.rte.fdl.string.EgovObjectUtil;
import www.spring.security.improved.userdetails.CustomUserDetails;


public class CustomJdbcUserDetailsManager extends JdbcUserDetailsManager {
	
	
	private CustomUserDetails customUserDetails;
	
    private CustomUsersByUsernameMapping customUsersByUsernameMapping;
    
    private String mapClass;
    
    private RoleHierarchy roleHierarchy;

	private Class<?> parameterTypes;
    
    
    
    @Autowired
	public void setMapClass(String mapClass) {
    	//www.spring.security.rte.cmmn.CustomUserDetailsMapping
    	System.out.println("mapCLass=>" + mapClass);
		this.mapClass = mapClass;//
	}
    
    @Autowired
	public void setRoleHierarchy(RoleHierarchy roleHierarchy) {
    	System.out.println("roleHierarchy=>" + roleHierarchy.getClass().getName());
		this.roleHierarchy = roleHierarchy;//secuirty.xml 설정해야함 아직 설정 안되어있음..
	}
    
    //JdbcUserDetailsManager 슈퍼클래스에서 initDao 오버라이딩
    @Override
    protected void initDao()throws ApplicationContextException{
    	super.initDao();
    	try{
    		initMappingSqlQueries();
    	}catch(Exception e){
    		logger.error("initDaoException : " + e.getMessage());
    	}
    }

	private void initMappingSqlQueries() throws InvocationTargetException,
    														IllegalAccessException, InstantiationException,
    														NoSuchMethodException, ClassNotFoundException, Exception{
		// TODO Auto-generated method stub
		
		Class<?> clazz = this.getLoadClass(this.mapClass);
		Constructor<?> constructor = clazz.getConstructor(new Class[]{DataSource.class,String.class});
		Object[] params = new Object[]{super.getDataSource() , super.getUsersByUsernameQuery()};
		//git허브 테스트
		//다형성에 의거한 객체로더 생성   -_-
		this.customUsersByUsernameMapping = (CustomUsersByUsernameMapping)constructor.newInstance(params);
		
		//logger.info("loader class==>" + ((CustomUsersByUsernameMapping)constructor.newInstance(params)).getClass().getName() );
		
		
	}

	//클래스 로더
	private Class<?> getLoadClass(String mapClass) throws ClassNotFoundException,Exception {
		// TODO Auto-generated method stub
		
		Class<?> clazz = null;
		try{
			clazz =  Thread.currentThread().getContextClassLoader().loadClass(mapClass);//security.xml에서 넘어온 매핑클래스 객체를 받는다.
			
	        if (clazz == null) {
	            clazz = Class.forName(mapClass);
	        }
		}catch(ClassNotFoundException e){
			throw new ClassNotFoundException();
		}catch(Exception e){
			throw new Exception(e);
		}finally{}
		return clazz;
	}
    
    
    
    
}
