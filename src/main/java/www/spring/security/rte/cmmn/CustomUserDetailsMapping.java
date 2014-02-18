package www.spring.security.rte.cmmn;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import www.spring.security.improved.customuserdetails.jdbc.CustomUsersByUsernameMapping;
import www.spring.security.improved.userdetails.CustomUserDetails;


public class CustomUserDetailsMapping extends  CustomUsersByUsernameMapping{
	
	
	public CustomUserDetailsMapping(DataSource ds , String usersByUsernameQuery){
		//System.out.println("ds===>" + ds.getClass().getName() + " : query===>" + usersByUsernameQuery);
		super(ds , usersByUsernameQuery);   
		//System.out.println("ds===>" + ds.getClass().getName() + " : query===>" + usersByUsernameQuery );
	}

	@Override
	protected CustomUserDetails mapRow(ResultSet rs, int rownum) throws SQLException {
		// TODO Auto-generated method stub
        String userid = rs.getString("user_id");
        String password = rs.getString("password");
        boolean enabled = rs.getBoolean("enabled");
        String username = rs.getString("user_name");
        
       // logger.info("===============>" + userid + " : " + password + " : " + enabled);
        
        CustomUserDetailsVO  userVO = new CustomUserDetailsVO();
        userVO.setUserId(userid);
        userVO.setPassWord(password);
        userVO.setUserName(username);
        
		return new  CustomUserDetails(userid, password, enabled, userVO);
	}
	
	
}
