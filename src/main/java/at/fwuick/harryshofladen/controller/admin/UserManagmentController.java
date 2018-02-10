package at.fwuick.harryshofladen.controller.admin;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import at.fwuick.harryshofladen.SecurityContextService;
import at.fwuick.harryshofladen.controller.model.AddUser;
import at.fwuick.harryshofladen.dao.RegularUserDao;
import at.fwuick.harryshofladen.dao.UserDao;
import at.fwuick.harryshofladen.dao.model.Register;
import at.fwuick.harryshofladen.dao.model.User;
import at.fwuick.harryshofladen.service.interfaces.IRegisterService;

@Controller
public class UserManagmentController {

	RegularUserDao regularUserDao;
	UserDao userDao;
	private IRegisterService registerService;
	
	@Autowired
	public UserManagmentController(RegularUserDao regularUserDao, UserDao userDao, IRegisterService registerService){
		this.regularUserDao = regularUserDao;
		this.userDao = userDao;
		this.registerService = registerService;
	}
	
	@RequestMapping("/user-managment")
	public String userManagmentIndex(Model model){
		SecurityContextService.validateAdmin();
		List<User> users = regularUserDao.all();
		model.addAttribute("users", users);
		List<Register> registers = registerService.all();
		model.addAttribute("registers", registers);
		return "admin/userManagment";
	}
	
	@RequestMapping(value="update-password", method=RequestMethod.POST)
	public String updatePassword(
			@RequestParam(value="user", required=true) Long userId, 
			@RequestParam(value="password", required=true)String password){
		SecurityContextService.validateAdmin();
		User user = regularUserDao.get(userId);
		user.setPassword(password);
		userDao.updatePassword(user);		
		return "redirect:/user-managment";
	}
	
	@RequestMapping(value="add-user", method=RequestMethod.GET)
	public String createUser(){
		SecurityContextService.validateAdmin();
		return "admin/createUser";
	}
	
	@RequestMapping(value="confirm-register", method=RequestMethod.POST)
	public String createUser(
			@RequestParam(value = "password", required = true) String password,
			@RequestParam(value = "register", required = true) Long register)
	{
		SecurityContextService.validateAdmin();
		Register registerModel = registerService.get(register);
		AddUser addUser = new AddUser();
		addUser.setEmail(registerModel.getEmail());
		addUser.setName(registerModel.getName());
		addUser.setPassword(password);
		registerService.delete(registerModel.getId());
		return createUser(addUser);
	}
	
	
	@RequestMapping(value="add-user", method=RequestMethod.POST)
	public String createUser(@ModelAttribute("user") @Valid AddUser user){
		SecurityContextService.validateAdmin();
		regularUserDao.insert(user);
		return "redirect:/user-managment";
	}
	
	@RequestMapping(value = "delete-register", method=RequestMethod.POST)
	public String deleteRegister(@RequestParam(value = "register", required = true) Long register){
		SecurityContextService.validateAdmin();
		registerService.delete(register);
		return "redirect:/user-managment";
	}
	
	@RequestMapping(value = "delete-user", method=RequestMethod.POST)
	public String deleteUser(@RequestParam(value = "user", required = true) Long user){
		SecurityContextService.validateAdmin();
		userDao.deactivate(user);
		return "redirect:/user-managment";
	}
}
