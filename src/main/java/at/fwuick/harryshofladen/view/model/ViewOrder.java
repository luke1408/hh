package at.fwuick.harryshofladen.view.model;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ViewOrder {
	private String username;
	private Long userid;
	private String productname;
	private Long productid;
	private Integer amount;
	private Long id;
	private String unit;
	private BigDecimal price;
}
