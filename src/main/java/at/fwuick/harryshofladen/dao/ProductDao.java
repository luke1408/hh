package at.fwuick.harryshofladen.dao;

import java.io.IOException;
import java.io.InputStream;

import java.sql.ResultSet;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Repository;

import at.fwuick.harryshofladen.dao.model.Product;
import at.fwuick.harryshofladen.utils.SQLQueryUtils;

@Repository
public class ProductDao extends AbstractPopulatedDao<Product> {

	private static final String SELECT_COLUMNS = "id, amount, description, name, unit, price::numeric";
	UnitDao unitDao;

	@Autowired
	public ProductDao(JdbcTemplate jdbcTemplate, UnitDao unitDao) {
		super("product", jdbcTemplate, insertParameter);
		this.unitDao = unitDao;
	}

	public Product persist(Product product) {
		product.setUnitObj(unitDao.get(product.getUnit()));
		return product;
	}

	@Override
	public RowMapper<Product> rowMapper() {
		return (ResultSet rs, int rowNum) -> {
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
	
	

	@Override
	public List<Product> all() {
		return this.allSpecificColumns(SELECT_COLUMNS);
	}
	
	@Override
	public Product get(long id) {
		return this.getSpecifiedColumns(id, SELECT_COLUMNS);
	}





	static final String[] insertParameter = new String[] { "name", "price", "description", "amount", "unit" };

	@Override
	protected Object[] mapForInsert(Product e) {
		return params(e.getName(), e.getPrice(), e.getDescription(), e.getAmount(), e.getUnit());
	}

	public List<Product> filterByName(String[] searchTerms) {
		String sql = query("select %columns from %table where");
		sql = resolveColumns(sql, SELECT_COLUMNS);
		String whereClause = SQLQueryUtils.concatPartsWithAnd(Arrays.stream(searchTerms)
				.map(s -> "upper(name) like '%" + s.toUpperCase() + "%'").toArray(String[]::new));
		sql = SQLQueryUtils.concatParts(sql, whereClause);
		return jdbcTemplate.query(sql, rowMapper());

	}

	public byte[] getImage(long productId) {
		String sql = query("select image from product where id = ?");
		return jdbcTemplate.queryForObject(sql, byte[].class, new Object[] { productId });
	}

	public String getImageBase64(long productId) {
		byte[] blob = getImage(productId);
		if (blob == null)
			return null;
		return new String(Base64.encode(blob));
	}

	public void insertImage(long productId, InputStream image) throws DataAccessException, IOException {
		jdbcTemplate.update("update product set image = ? where id = ?", new Object[] { IOUtils.toByteArray(image), productId });
	}
	

}
