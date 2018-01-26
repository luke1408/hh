package at.fwuick.harryshofladen.service.interfaces;

import java.util.List;

import at.fwuick.harryshofladen.dao.model.Register;

public interface IRegisterService {
	public void add(Register register);

	public List<Register> all();
}
