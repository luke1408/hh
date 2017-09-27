package at.fwuick.harryshofladen.dao.model;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Product extends AbstractIdentifiedEntity{
	String name;
	Integer amount;
	Integer unit;
	Unit unitObj;
	String description;
	BigDecimal price;
}