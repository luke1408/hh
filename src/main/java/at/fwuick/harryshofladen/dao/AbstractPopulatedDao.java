package at.fwuick.harryshofladen.dao;

import java.util.Collections;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import at.fwuick.harryshofladen.dao.interfaces.IInsertDao;
import at.fwuick.harryshofladen.repository.model.AbstractIdentifiedEntity;

public abstract class AbstractPopulatedDao<T extends AbstractIdentifiedEntity> extends AbstractDao<T> implements IInsertDao<T>{

	protected String[] insertParameter;
	
	protected AbstractPopulatedDao(String tableName, JdbcTemplate jdbcTemplate, String[] insertParameter) {
		super(tableName, jdbcTemplate);
		this.insertParameter = insertParameter;
	}
	
	@Override
	public T insert(T e) {
		String sql = query("insert into %table (%s) values( %s)");
		sql = sql.format(sql, parameterString(), jdbcQuestionMarkString());
		jdbcTemplate.update(sql, mapForInsert(e));
		e.setId(this.lastId());
		return e;
	}

	protected abstract Object[] mapForInsert(T e);

	private String jdbcQuestionMarkString() {
		return String.join(", ", Collections.nCopies(insertParameter.length, "?"));
	}

	private String parameterString() {
		return String.join(", ", insertParameter);
	}
	
	
	

}
