package at.fwuick.harryshofladen;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import at.fwuick.harryshofladen.dao.ActiveUserDao;
import at.fwuick.harryshofladen.dao.UserDao;
import at.fwuick.harryshofladen.dao.model.User;

@Component
public class MyAuthentificationProvider implements AuthenticationProvider{

	public static final GrantedAuthority ADMIN_AUTHORITY = ()->{
		return "admin";
	};
	private ActiveUserDao userDao;

	@Autowired
	public MyAuthentificationProvider(ActiveUserDao userDao){
		this.userDao = userDao;
	}
	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		String password = auth.getCredentials().toString();
		User user = userDao.findByPassword(password);
		if(user != null){
			ArrayList<GrantedAuthority> authorities = new ArrayList<>();
			if(user.getAdmin()){
				authorities.add(ADMIN_AUTHORITY);
			}
			return new UsernamePasswordAuthenticationToken(
		              user, password, authorities);
		}
		return null;
		
	}

	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return arg0.equals(UsernamePasswordAuthenticationToken.class);
	}

}
