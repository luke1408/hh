package at.fwuick.harryshofladen.dao;

import java.sql.ResultSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import at.fwuick.harryshofladen.dao.model.User;

@Repository
public class ActiveUserDao extends AbstractDao<User>{

	@Autowired
	protected ActiveUserDao(JdbcTemplate jdbcTemplate) {
		super("active_users", jdbcTemplate);
	}

	@Override
	public RowMapper<User> rowMapper() {
		return (ResultSet rs, int rowNum)->{
			User user = new User();
			user.setName(rs.getString("name"));
			user.setId(rs.getInt("id"));
			user.setPassword(rs.getString("password"));
			user.setEmail(rs.getString("email"));
			user.setAdmin(rs.getBoolean("admin"));
			return user;
		};
	}
	
	public User findByPassword(String password){
		String query = "select * from %table where password = ?";
		query = resolveTableName(query);
		try{
			return jdbcTemplate.queryForObject(query, params(password), rowMapper());
		}catch(EmptyResultDataAccessException e){
			return null;
		}
	}

}
