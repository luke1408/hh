package at.fwuick.harryshofladen.controller.model;

import javax.validation.constraints.NotNull;

import at.fwuick.harryshofladen.dao.model.User;

public class AddUser extends User{

	@Override
	@NotNull
	public String getEmail() {
		// TODO Auto-generated method stub
		return super.getEmail();
	}

	@Override
	@NotNull
	public String getName() {
		// TODO Auto-generated method stub
		return super.getName();
	}

	@Override
	@NotNull
	public String getPassword() {
		// TODO Auto-generated method stub
		return super.getPassword();
	}

	
}
