package at.fwuick.harryshofladen.dao;

import java.sql.ResultSet;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import at.fwuick.harryshofladen.model.Product;

@Repository
public class ProductDao extends AbstractPopulatedDao<Product>{
	
	
	UnitDao unitDao;
	
	@Autowired
	public ProductDao(JdbcTemplate jdbcTemplate, UnitDao unitDao){
		super("product", jdbcTemplate, insertParameter);
		this.unitDao = unitDao;
	}
	
	public Product persist(Product product){
		product.setUnitObj(unitDao.get(product.getUnit()));
		return product;
	}

	@Override
	public RowMapper rowMapper() {
		return (ResultSet rs, int rowNum)-> {
			Product product = new Product();
			product.setId(rs.getInt("id"));
			product.setAmount(rs.getInt("amount"));
			product.setDescription(rs.getString("description"));
			product.setName(rs.getString("name"));
			product.setUnit(rs.getInt("unit"));
			product.setPrice(rs.getBigDecimal("price"));
			return product;
		};
	}
	
	
	static final String[] insertParameter = new String[]{"name", "price", "description", "amount", "unit"};
	@Override
	protected Object[] mapForInsert(Product e) {
		return params(e.getName(), e.getPrice(), e.getDescription(), e.getAmount(), e.getUnit());
	}

}
