package at.fwuick.harryshofladen.model;

import lombok.Data;

@Data
public class Order {
	private int id;
	private int user;
	private int product;
	private int amount;
}
