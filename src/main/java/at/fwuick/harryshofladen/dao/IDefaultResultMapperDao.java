package at.fwuick.harryshofladen.dao;

import org.springframework.jdbc.core.RowMapper;

public interface IDefaultResultMapperDao<T> {
	
	public RowMapper<T> rowMapper();
}
