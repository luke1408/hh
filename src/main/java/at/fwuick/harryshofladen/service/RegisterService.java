package at.fwuick.harryshofladen.service;

import java.util.List;

import org.springframework.stereotype.Service;

import at.fwuick.harryshofladen.dao.RegisterDao;
import at.fwuick.harryshofladen.dao.model.Register;
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

	@Override
	public List<Register> all() {
		return registerDao.all();
	}

	@Override
	public Register get(long id) {
		return registerDao.get(id);
	}

	@Override
	public void delete(long id) {
		registerDao.delete(id);
		
	}
	
}
