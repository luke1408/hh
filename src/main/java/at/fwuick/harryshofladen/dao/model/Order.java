package at.fwuick.harryshofladen.dao.model;

import lombok.Data;

@Data
public class Order extends AbstractIdentifiedEntity{
	private int user;
	private int product;
	private int amount;
}
