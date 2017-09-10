package at.fwuick.harryshofladen.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class OrderableProductDao extends ProductDao{

	@Autowired
	public OrderableProductDao(JdbcTemplate jdbcTemplate, UnitDao unitDao) {
		super(jdbcTemplate, unitDao);
		this.setTableName("orderable_products");
	}

	
}
