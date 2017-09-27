package at.fwuick.harryshofladen.dao;

import java.sql.ResultSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import at.fwuick.harryshofladen.model.Unit;

@Repository
public class UnitDao extends AbstractDao<Unit>{

	
	@Autowired
	public UnitDao(JdbcTemplate jdbcTemplate){
		super("unit", jdbcTemplate);
	}
	
	public Unit get(int id){
		String query = "select * from %table where id = ?";
		query = resolveTableName(query);
		return jdbcTemplate.queryForObject(query, params(id), rowMapper());
	}

	@Override
	public RowMapper<Unit> rowMapper() {
		return (ResultSet rs, int rowNum)->{
			Unit unit = new Unit();
			unit.setId(rs.getInt("id"));
			unit.setName(rs.getString("name"));
			return unit;
		};
	}

}
