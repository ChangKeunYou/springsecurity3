package www.spring.security.improved.userdetails;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class CustomUserDetails extends User  {
	
	private static final long serialVersionUID = 1L;
	
	private Object customVO;
	
	public CustomUserDetails(String username, String password, boolean enabled,boolean accountNonExpired, boolean credentialsNonExpired,
			boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities,
			Object customVO) {
		
		super(username, password, enabled, accountNonExpired, credentialsNonExpired,accountNonLocked, authorities);
		// TODO Auto-generated constructor stub
		this.customVO = customVO;
		
	}
	
	public CustomUserDetails(String username, String password, boolean enabled, Object customVO)throws IllegalArgumentException{
		/*
    	this(username, password, enabled, true, true, true,  Arrays.asList(new GrantedAuthority[] {new SimpleGrantedAuthority("HOLDER")}), customVO);
    	*/
    	this(username, password, enabled, true, true, true, 
    			Arrays.asList(new GrantedAuthority[] {new SimpleGrantedAuthority("HOLDER")}), customVO);
	}
	
	/**
	 * 
	 * @return 사용자VO 객체
	 */
	public Object getCustomVO() {
		return customVO;
	}
	
	/**
	 * 
	 * @param 사용자 VO 객체
	 */
	public void setCustomVO(Object customVO) {
		this.customVO = customVO;
	}
	
	
	
	
}
