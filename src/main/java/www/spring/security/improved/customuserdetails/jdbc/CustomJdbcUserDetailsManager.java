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
		//생성자 부분에 쿼리랑 데이터베이스 객체를 던진다 
		this.customUsersByUsernameMapping = (CustomUsersByUsernameMapping)constructor.newInstance(params);
		
		//logger.info("loader class==>" + ((CustomUsersByUsernameMapping)constructor.newInstance(params)).getClass().getName() );
	
		
	}
	/**
	 * 유저에 대한 정보를 가져와서 ArryList에 담는다 이때 형변환은 제너릭은 
	 * UserDetails인데 CustomUserDetails 클래스 상속 User 위에 UserDetails클래스 정의되어있음
	 */
	@Override
	protected List<UserDetails> loadUsersByUsername(String userid){
		//ArrayList<CustomUserDetails> list = this.customUsersByUsernameMapping.execute(username);
		
		//logger.info("username222=>" + userid);
		
		List<CustomUserDetails> list = this.customUsersByUsernameMapping.execute(userid);
		
		ArrayList<UserDetails> newList = new ArrayList<UserDetails>();
		
		for(CustomUserDetails user : list){
			newList.add(user);
		}
		return newList;
	}
	
	/**
	 * 받아온 사용자의 정보를 가지고 권한 및 하위권한등등 정보등을 이용하여 오브젝트 생성
	 */
	@Override
	public CustomUserDetails loadUserByUsername(String userid) throws UsernameNotFoundException, DataAccessException{
		
		//logger.info("userid1111=>" + userid);
		//로그인한 유저의 정보를 여기서 다시 얻는다
		List<UserDetails> users = this.loadUsersByUsername(userid);
		
		//logger.info(users.size() + " : " + users.toString());
        if (users.size() == 0) {
            logger.debug("Query returned no results for user '" + userid + "'");
            
            throw new UsernameNotFoundException(
            		messages.getMessage("EgovJdbcUserDetailsManager.notFound", new Object[]{userid}, "Username {0} not found"));
        }
		
		UserDetails obj = users.get(0);
		
		this.customUserDetails = (CustomUserDetails)obj;
		
		//logger.info(this.customUserDetails.getCustomVO().toString());
		
		Set<GrantedAuthority> dbAuthsSet = new HashSet<GrantedAuthority>();
		//로그인한 유저의 권한을 가져온다..
		
		//logger.info("===userid==>" + this.customUserDetails. getUsername());
		
		List<GrantedAuthority> authList = super.loadUserAuthorities(this.customUserDetails. getUsername());//userid삽입
		dbAuthsSet.addAll(authList);//해당 유저의 권한을 들고온 LIST를 SET에 삽입(ROLE_USER , ROLE_ADMIN)
		List<GrantedAuthority> dbAuths = new ArrayList<GrantedAuthority>(dbAuthsSet);
		
		//로그인한 유저의 id와 권한을 삽입한다
		addCustomAuthorities( this.customUserDetails.getUsername()	 , dbAuths);
		
		
        if (dbAuths.size() == 0) {
            throw new UsernameNotFoundException(messages.getMessage(
                "EgovJdbcUserDetailsManager.noAuthority",
                new Object[] {userid }, "User {0} has no GrantedAuthority"));
        }
        
        //RoleHierarchyImpl 에서 저장한 Role Hierarchy 정보가 저장된다.
        //가져온 유저의 권한 및 하위 권한까지 가져 올수있도록 해당 메서드 통해서 실행 하위 권한은 xml설정 디비를 통해서 가져온다
        Collection<? extends GrantedAuthority> authorities = this.roleHierarchy.getReachableGrantedAuthorities(dbAuths);
		
		
		for(GrantedAuthority authData: authList){
			System.out.println("=============================" + authData.getAuthority() + "==========================");
		}
		
		
        //로그인한 유저의 아이디 패스워드 사용여부 권한 등등..해서 오브젝트 생성
		return new CustomUserDetails(this.customUserDetails.getUsername(), this.customUserDetails.getPassword(),
				this.customUserDetails.isEnabled(),
				true, true, true, 
				authorities, this.customUserDetails.getCustomVO());
		
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
