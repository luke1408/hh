package at.fwuick.harryshofladen.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import at.fwuick.harryshofladen.SecurityContextService;

@Controller
public class IndexController {
	
	@RequestMapping("/")
	public String index(){
		if(SecurityContextService.isAdmin()){
			return "redirect:/user-managment";
		}
		return "index";
	}
	
}
