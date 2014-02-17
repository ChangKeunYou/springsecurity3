package www.spring.security;

import java.lang.reflect.Constructor;

import javax.sql.DataSource;

import egovframework.rte.fdl.string.EgovObjectUtil;


public class ClassC {
	
	
	public ClassC(){
		
	}
	
	
	public static void main(String[]args){
		
		//ClassA classA = new ClassB();
		ClassC classC = new ClassC();
		String mapClass = "www.spring.security.ClassB";
		
		//classA.print();
		try{
			
			Class<?> clazz = classC.getLoadClass(mapClass);
			Constructor<?> constructor = clazz.getConstructor(new Class[]{String.class});
			Object[] params = new Object[] { " hello invoke Object " };
			ClassA a = (ClassA)constructor.newInstance(params);       
			
			a.test();
			//System.out.println(clazz.getClass().getName()); test  !!test-_-
			
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}finally{}
		
		
		/*
        Class<?> clazz = EgovObjectUtil.loadClass(this.mapClass);
        //jdbcUserService 설정한 곳에서 mapClass DI받아 
        Constructor<?> constructor = clazz.getConstructor(new Class[] {DataSource.class, String.class });
        Object[] params = new Object[] { getDataSource(), getUsersByUsernameQuery() };
        
        
        this.usersByUsernameMapping = (EgovUsersByUsernameMapping) constructor.newInstance(params);
		*/
		
		
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
