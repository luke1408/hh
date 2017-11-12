package at.fwuick.harryshofladen.controller.admin;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import at.fwuick.harryshofladen.SecurityContextService;
import at.fwuick.harryshofladen.controller.model.AddUser;
import at.fwuick.harryshofladen.dao.RegularUserDao;
import at.fwuick.harryshofladen.dao.UserDao;
import at.fwuick.harryshofladen.dao.model.User;

@Controller
public class UserManagmentController {

	RegularUserDao regularUserDao;
	UserDao userDao;
	
	@Autowired
	public UserManagmentController(RegularUserDao regularUserDao, UserDao userDao){
		this.regularUserDao = regularUserDao;
		this.userDao = userDao;
	}
	
	@RequestMapping("/user-managment")
	public String userManagmentIndex(Model model){
		SecurityContextService.validateAdmin();
		List<User> users = regularUserDao.all();
		model.addAttribute("users", users);
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
	
	@RequestMapping(value="add-user", method=RequestMethod.POST)
	public String createUser(@ModelAttribute("user") @Valid AddUser user){
		SecurityContextService.validateAdmin();
		regularUserDao.insert(user);
		return "redirect:/user-managment";
	}
}
