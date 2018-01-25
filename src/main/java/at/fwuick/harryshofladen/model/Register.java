package at.fwuick.harryshofladen.model;

import at.fwuick.harryshofladen.dao.model.AbstractIdentifiedEntity;
import lombok.Data;

@Data
public class Register extends AbstractIdentifiedEntity{
	private String email;
	private String name;
}
