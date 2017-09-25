package at.fwuick.harryshofladen.dao.interfaces;

import org.springframework.jdbc.core.RowMapper;

public interface IDefaultResultMapperDao<T> {
	
	public RowMapper<T> rowMapper();
}
