package at.fwuick.harryshofladen.dao.model;

import lombok.Data;

@Data
public class Register extends AbstractIdentifiedEntity{
	private String email;
	private String name;
}
