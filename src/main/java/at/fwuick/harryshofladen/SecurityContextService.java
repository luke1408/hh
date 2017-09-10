package at.fwuick.harryshofladen;


import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

public class SecurityContextService {

	public static boolean isAdmin(){
		return SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(MyAuthentificationProvider.ADMIN_AUTHORITY);
	}
	
	public static void validateAdmin() throws AccessDeniedException{
		if(!isAdmin()){
			throw new AccessDeniedException("No Admin");
		}
	}
}
