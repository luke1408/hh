package at.fwuick.harryshofladen;


import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;

import at.fwuick.harryshofladen.dao.model.User;

public class SecurityContextService {

	public static boolean isAdmin(){
		return SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(MyAuthentificationProvider.ADMIN_AUTHORITY);
	}
	
	public static boolean isLoggedIn() {
		return SecurityContextHolder.getContext().getAuthentication().isAuthenticated() && !SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser");
	}
	
	public static void validateAdmin() throws AccessDeniedException{
		if(!isAdmin()){
			throw new AccessDeniedException("No Admin");
		}
	}
	
	public static User getUser(){
		return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
}
