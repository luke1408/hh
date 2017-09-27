package at.fwuick.harryshofladen.view.model;

import java.math.BigDecimal;

import at.fwuick.harryshofladen.dao.model.Product;
import at.fwuick.harryshofladen.dao.model.Unit;
import lombok.Getter;

public class ShopProduct {
	
	private Product product;
	private @Getter String image;
	
	public ShopProduct(Product product, String image){
		this.product = product;
		this.image = image;
	}

	public int getId() {
		return product.getId();
	}

	public Integer getAmount() {
		return product.getAmount();
	}

	public String getDescription() {
		return product.getDescription();
	}

	public String getName() {
		return product.getName();
	}

	public BigDecimal getPrice() {
		return product.getPrice();
	}

	public String getUnit() {
		return product.getUnitObj().getName();
	}
	
	
}
