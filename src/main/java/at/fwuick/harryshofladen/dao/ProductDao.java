package at.fwuick.harryshofladen.dao;

import java.sql.ResultSet;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import at.fwuick.harryshofladen.repository.model.Product;

@Repository
public class ProductDao extends AbstractDao<Product>{
	
	UnitDao unitDao;
	
	@Autowired
	public ProductDao(JdbcTemplate jdbcTemplate, UnitDao unitDao){
		super("product", jdbcTemplate);
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
			return product;
		};
	}

}
