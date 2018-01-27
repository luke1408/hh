package at.fwuick.harryshofladen.dao;


import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import at.fwuick.harryshofladen.dao.model.Register;

@Repository
public class RegisterDao extends AbstractPopulatedDao<Register>{

	private static final String[] INSERT_PARAMETER = new String[]{"name", "email"};
	private static final String TABLE_NAME = "register";

	protected RegisterDao(JdbcTemplate jdbcTemplate) {
		super(TABLE_NAME, jdbcTemplate, INSERT_PARAMETER);
	}

	@Override
	public RowMapper<Register> rowMapper() {
		return new RowMapper<Register>() {

			@Override
			public Register mapRow(ResultSet rs, int rowNum) throws SQLException {
				Register register = new Register();
				register.setEmail(rs.getString("email"));
				register.setName(rs.getString("name"));
				register.setId(rs.getInt("id"));
				return register;
			}
		};
	}

	@Override
	protected Object[] mapForInsert(Register e) {
		return new Object[]{e.getName(), e.getEmail()};
	}
}
