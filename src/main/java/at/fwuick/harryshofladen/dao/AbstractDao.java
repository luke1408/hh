package at.fwuick.harryshofladen.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import at.fwuick.harryshofladen.dao.interfaces.IAllDao;
import at.fwuick.harryshofladen.dao.interfaces.IDao;
import at.fwuick.harryshofladen.dao.interfaces.IGetDao;
import lombok.Setter;

public abstract class AbstractDao<T> implements IDao, IAllDao<T>, IGetDao<T>{

	private @Setter String tableName;
	protected JdbcTemplate jdbcTemplate;
	@Override
	public String tableName() {
		return tableName;
	}
	protected AbstractDao(String tableName, JdbcTemplate jdbcTemplate){
		this.tableName = tableName;
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<T> all() {
		String query = "select * from %table";
		query = resolveTableName(query);
		return jdbcTemplate.query(query, rowMapper());
	}
	
	public int lastId(){
		String query = "select max(id) from %table";
		query =  resolveTableName(query);
		return jdbcTemplate.queryForObject(query, Integer.class);
	}
	
	@Override
	public T get(long id) {
		String query = "select * from %table where id = ?";
		query =  resolveTableName(query);
		return jdbcTemplate.queryForObject(query, params(id), this.rowMapper());
	}
	
	@Override
	public boolean exists(long id) {
		String query = "select count(1) from %table where id = ?";
		query =  resolveTableName(query);
		int count = jdbcTemplate.queryForObject(query, new Object[]{id}, Integer.class);
		if(count > 0){
			if (count == 1){
				return true;
			}else{
				throw new RuntimeException(this.tableName() + " allows duplicated ids and does not qualify for an AbstractDao implementation");
			}
		}else{
			return false;
		}
	}
	
	
	
	
}
