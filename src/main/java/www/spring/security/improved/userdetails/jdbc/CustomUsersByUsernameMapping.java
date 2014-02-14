package www.spring.security.improved.userdetails.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;

import www.spring.security.improved.userdetails.CustomUserDetails;


public abstract class CustomUsersByUsernameMapping extends MappingSqlQuery<CustomUserDetails> {
	
	public CustomUsersByUsernameMapping(DataSource ds, String usersByUsernameQuery){
        super(ds, usersByUsernameQuery);
        declareParameter(new SqlParameter(Types.VARCHAR));
        compile();
	}
	
	
	@Override
	protected abstract CustomUserDetails mapRow(ResultSet rs, int rownum) throws SQLException;
	
}
