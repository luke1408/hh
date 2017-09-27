package at.fwuick.harryshofladen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import at.fwuick.harryshofladen.dao.UserDao;
import at.fwuick.harryshofladen.dao.model.User;

@Controller
public class LoginController {
	
	UserDao userDao;
	
	@Autowired
	public LoginController(UserDao userDao){
		this.userDao = userDao;
	}
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String login(@RequestParam(value="error", required = false) String error, Model model){
		model.addAttribute("error", error);
		return "login";
	}
	
//	@RequestMapping(value="/login", method = RequestMethod.POST)
//	public String login(@RequestParam(value="password", required = true) String password){
//		User user = userDao.findByPassword(password);
//		return "redirect:/";
//	}

}
