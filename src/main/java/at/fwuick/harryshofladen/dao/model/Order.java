package at.fwuick.harryshofladen.dao.model;

import lombok.Data;

@Data
public class Order extends AbstractIdentifiedEntity{
	private long user;
	private long product;
	private int amount;
}
