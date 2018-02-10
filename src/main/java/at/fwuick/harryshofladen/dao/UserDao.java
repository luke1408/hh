package at.fwuick.harryshofladen.dao;

import java.sql.ResultSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import at.fwuick.harryshofladen.dao.model.User;

@Repository
public class UserDao extends AbstractPopulatedDao<User>{

	
	@Autowired
	protected UserDao(JdbcTemplate jdbcTemplate) {
		super("\"user\"", jdbcTemplate, insertParameter);
	}
	
	public void deactivate(Long userId) {
		jdbcTemplate.update("update \"user\" set status = 0 where id = ?", userId);
	}
	
	public boolean passwordExists(String password){
		String query = "select count(1) from %table where password = ?";
		resolveTableName(query);
		return jdbcTemplate.queryForObject(query, params(password), Integer.class) > 0;
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
	
	public User updatePassword(User user){
		String query = "update %table set password = ? where id = ?";
		query = resolveTableName(query);
		jdbcTemplate.update(query, params(user.getPassword(), user.getId()));
		return user;
	}

	static String[] insertParameter = "name, email, password, admin".split(",");
	@Override
	protected Object[] mapForInsert(User e) {
		return params(e.getName(), e.getEmail(), e.getPassword(), e.getAdmin());
	}

	public String getName(Long userid) {
		String query = resolveTableName("select name from %table where id = ?");
		return jdbcTemplate.queryForObject(query, params(userid), String.class);
	}
	

}
