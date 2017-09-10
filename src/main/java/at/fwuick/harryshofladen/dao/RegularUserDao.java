package at.fwuick.harryshofladen.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import at.fwuick.harryshofladen.repository.model.User;

@Repository
public class RegularUserDao extends AbstractDao<User>{

	UserDao userDao;
	
	@Autowired
	public RegularUserDao(UserDao userDao, JdbcTemplate jdbcTemplate){
		super("regular_user", jdbcTemplate);
		this.userDao = userDao;
	}

	@Override
	public RowMapper<User> rowMapper() {
		return userDao.rowMapper();
	}

	public User insert(User user) {
		user.setAdmin(false);
		return userDao.insert(user);
		
	}
}
