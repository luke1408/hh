package at.fwuick.harryshofladen.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class OrderableProductDao extends ProductDao{

	@Autowired
	public OrderableProductDao(JdbcTemplate jdbcTemplate, UnitDao unitDao) {
		super(jdbcTemplate, unitDao);
		this.setTableName("orderable_product");
	}

	
}
