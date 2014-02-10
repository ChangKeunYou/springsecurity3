package www.spring.security.improved.customuserdetails.jdbc;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.sql.DataSource;

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
	
	
	private CustomUserDetails customUserDetails = null;
    private CustomUsersByUsernameMapping customUsersByUsernameMapping;
    
    private String mapClass;
    private RoleHierarchy roleHierarchy = null;
    
	public void setMapClass(String mapClass) {
		this.mapClass = mapClass;
	}
	
	public void setRoleHierarchy(RoleHierarchy roleHierarchy) {
		this.roleHierarchy = roleHierarchy;
	}
	
    
    
    
}
