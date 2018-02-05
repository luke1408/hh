package at.fwuick.harryshofladen.dao;

import java.sql.ResultSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import at.fwuick.harryshofladen.dao.model.Order;

@Repository
public class OrderDao extends AbstractPopulatedDao<Order>{

	@Autowired
	protected OrderDao(JdbcTemplate jdbcTemplate) {
		super("o_order", jdbcTemplate, insertParameter);
	}

	@Override
	public RowMapper<Order> rowMapper() {
		return (ResultSet rs, int rowNum) -> {
			Order order = new Order();
			order.setId(rs.getInt("id"));
			order.setProduct(rs.getInt("product"));
			order.setUser(rs.getInt("user"));
			order.setAmount(rs.getInt("amount"));
			order.setActive(rs.getBoolean("active"));
			return order;
		};
	}
	
	static String[] insertParameter = "product,\"user\",amount".split(",");
	@Override
	protected Object[] mapForInsert(Order e) {
		return params(e.getProduct(), e.getUser(), e.getAmount());
	}
	
	public void setActive(long id, boolean active){
		String sql = resolveTableName("update %table set active = ? where id = ?");
		jdbcTemplate.update(sql, params(active, id));
	}
	
	public List<Order> selectByUser(long user){
		String sql = resolveTableName("select * from %table where \"user\" = ?");
		return jdbcTemplate.query(sql, params(user), rowMapper());
	}
	

}
