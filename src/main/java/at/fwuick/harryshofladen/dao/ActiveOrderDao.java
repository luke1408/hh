package at.fwuick.harryshofladen.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import at.fwuick.harryshofladen.model.Order;

@Repository
public class ActiveOrderDao extends AbstractDao<Order>{

	OrderDao orderDao;
	
	@Autowired
	protected ActiveOrderDao(JdbcTemplate jdbcTemplate, OrderDao orderDao) {
		super("active_order", jdbcTemplate);
		this.orderDao = orderDao;
	}

	@Override
	public RowMapper<Order> rowMapper() {
		return orderDao.rowMapper();
	}

}
