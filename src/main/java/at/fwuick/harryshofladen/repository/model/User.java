package at.fwuick.harryshofladen.repository.model;

import lombok.Data;

@Data
public class User {
	public int id;
	private String name;
	private String password;
	private String email;
	private Boolean admin;
}
