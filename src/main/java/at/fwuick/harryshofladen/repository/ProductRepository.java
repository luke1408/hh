package at.fwuick.harryshofladen.repository;

import at.fwuick.harryshofladen.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;


    public List<Product> list() {
        return jdbcTemplate.query("select * from v_product", (resultSet, i) -> {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            float price = resultSet.getFloat("price");
            long quantity = resultSet.getLong("quantity");
            return new Product(id, name, price, quantity);
        });
    }

    public long create(String name, Double price) {
        jdbcTemplate.update("INSERT INTO product(name, price) VALUES (?, ?)", new Object[]{name, price});
        long id = jdbcTemplate.queryForObject("SELECT max(id) from product", Long.class);
        addQuantity(id, 0);
        return id;
    }

    public void updateQuantity(Long product, Long quantity) {
        long currentQuantity = this.getQuantity(product);
        this.addQuantity(product, quantity - currentQuantity);
    }

    public void addQuantity(Long product, long quantity) {
        jdbcTemplate.update("INSERT  INTO product_instance(product, quantity) VALUES (?, ?)", new Object[]{product, quantity});
    }

    private long getQuantity(Long product) {
        return jdbcTemplate.queryForObject("select quantity from v_product where id = ?", new Object[]{product}, Long.class);
    }
}
