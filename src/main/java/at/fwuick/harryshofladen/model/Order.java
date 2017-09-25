package at.fwuick.harryshofladen.model;

import at.fwuick.harryshofladen.repository.model.AbstractIdentifiedEntity;
import lombok.Data;

@Data
public class Order extends AbstractIdentifiedEntity{
	private int user;
	private int product;
	private int amount;
}
