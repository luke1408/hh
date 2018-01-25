package at.fwuick.harryshofladen.service;

import org.springframework.stereotype.Service;

import at.fwuick.harryshofladen.dao.RegisterDao;
import at.fwuick.harryshofladen.model.Register;
import at.fwuick.harryshofladen.service.interfaces.IRegisterService;

@Service
public class RegisterService implements IRegisterService {

	private RegisterDao registerDao;
	
	public RegisterService(RegisterDao registerDao) {
		super();
		this.registerDao = registerDao;
	}

	@Override
	public void add(Register register) {
		registerDao.insert(register);
	}
	
}
