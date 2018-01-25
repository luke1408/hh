package at.fwuick.harryshofladen.controller;

import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import at.fwuick.harryshofladen.dao.model.Register;
import at.fwuick.harryshofladen.service.interfaces.IRegisterService;

@Controller
public class SignupController {
	
	private IRegisterService registerService;

	public SignupController(IRegisterService registerService) {
		super();
		this.registerService = registerService;
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String singup(Model model){
		return "signup";
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String singup(@RequestParam("name") String name, @RequestParam("email") String email){
		Register register = new Register();
		register.setEmail(email);
		register.setName(name);
		registerService.add(register);
		return "signupConfirmed";
	}
	
	
}
