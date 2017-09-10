package at.fwuick.harryshofladen.dao;

import java.sql.ResultSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import at.fwuick.harryshofladen.model.Order;

@Repository
public class OrderDao extends AbstractDao<Order>{

	@Autowired
	protected OrderDao(JdbcTemplate jdbcTemplate) {
		super("order", jdbcTemplate);
	}

	@Override
	public RowMapper<Order> rowMapper() {
		return (ResultSet rs, int rowNum) -> {
			Order order = new Order();
			order.setId(rs.getInt("id"));
			order.setProduct(rs.getInt("product"));
			order.setUser(rs.getInt("user"));
			order.setAmount(rs.getInt("amount"));
			return order;
		};
	}

}
