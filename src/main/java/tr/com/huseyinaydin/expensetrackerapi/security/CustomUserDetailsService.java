package tr.com.huseyinaydin.expensetrackerapi.security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import tr.com.huseyinaydin.expensetrackerapi.entity.User;
import tr.com.huseyinaydin.expensetrackerapi.repository.UserRepository;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot.
 *
 */

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		User existingUser = userRepository
				.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Böyle bir e-posta adresi ile kullanıcı kaydı bulunamamıştır: "+email));
		
		return new org.springframework.security.core.userdetails.User(existingUser.getEmail(), existingUser.getPassword(), new ArrayList<>());
	}

}
