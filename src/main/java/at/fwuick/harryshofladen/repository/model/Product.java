package at.fwuick.harryshofladen.repository.model;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Product {
	String name;
	Integer amount;
	Integer unit;
	Unit unitObj;
	String description;
	int id;
	BigDecimal price;
}