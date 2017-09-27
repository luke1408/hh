package at.fwuick.harryshofladen.dao.model;

import lombok.Data;

@Data
public class User extends AbstractIdentifiedEntity{
	private String name;
	private String password;
	private String email;
	private Boolean admin;
}
