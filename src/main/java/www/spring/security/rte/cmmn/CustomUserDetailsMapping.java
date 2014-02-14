package www.spring.security.rte.cmmn;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import www.spring.security.improved.customuserdetails.jdbc.CustomUsersByUsernameMapping;
import www.spring.security.improved.userdetails.CustomUserDetails;


public class CustomUserDetailsMapping extends  CustomUsersByUsernameMapping{
	
	private static int k = 1;
	
	public CustomUserDetailsMapping(DataSource ds , String usersByUsernameQuery){
		//System.out.println("ds===>" + ds.getClass().getName() + " : query===>" + usersByUsernameQuery);
		super(ds , usersByUsernameQuery);   
		System.out.println("ds===>" + ds.getClass().getName() + " : query===>" + usersByUsernameQuery + "count=>" + CustomUserDetailsMapping.k);
		CustomUserDetailsMapping.k++;
	}

	@Override
	protected CustomUserDetails mapRow(ResultSet rs, int rownum) throws SQLException {
		// TODO Auto-generated method stub
		System.out.println("userid======>" + rs.getString("user_id"));
		return null;
	}
	
}
