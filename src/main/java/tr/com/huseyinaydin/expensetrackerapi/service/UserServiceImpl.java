package tr.com.huseyinaydin.expensetrackerapi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;


import org.springframework.stereotype.Service;

import tr.com.huseyinaydin.expensetrackerapi.entity.User;
import tr.com.huseyinaydin.expensetrackerapi.entity.UserModel;
import tr.com.huseyinaydin.expensetrackerapi.exceptions.ItemExistsException;
import tr.com.huseyinaydin.expensetrackerapi.exceptions.ResourceNotFoundException;
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
public class UserServiceImpl implements UserService {

	/*
	@Autowired
	private PasswordEncoder bcryptEncoder;
	*/

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	@Override
	public User createUser(UserModel user) {
		if (userRepository.existsByEmail(user.getEmail())) {
			throw new ItemExistsException("Kayıt yapmak istediğin kullanıcı zaten var. Aynı e-posta kaydı var. Aha dayıya sor: "+user.getEmail());
		}
		User newUser = new User();
		BeanUtils.copyProperties(user, newUser);
		newUser.setPassword(bcryptEncoder.encode(newUser.getPassword()));
		return userRepository.save(newUser);
	}

	@Override
	public User readUser() {
		Long userId = getLoggedInUser().getId();
		//Long userId = 3L;
		return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Bu ID'de kullanıcı bulunamadı: :"+userId));
	}

	@Override
	public User updateUser(UserModel user) {
		User existingUser = readUser();
		existingUser.setName(user.getName() != null ? user.getName() : existingUser.getName());
		existingUser.setEmail(user.getEmail() != null ? user.getEmail() : existingUser.getEmail());
		existingUser.setPassword(user.getPassword() != null ? bcryptEncoder.encode(user.getPassword()) : existingUser.getPassword());
		//existingUser.setPassword(user.getPassword() != null ? user.getPassword() : existingUser.getPassword());
		existingUser.setAge(user.getAge() != null ? user.getAge() : existingUser.getAge());
		return userRepository.save(existingUser);
	}

	@Override
	public void deleteUser() {
		User existingUser = readUser();
		userRepository.delete(existingUser);
	}

	@Override
	public User getLoggedInUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		String email = authentication.getName();
		
		return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Böyle bir e-postaya ait kullanıcı bulunamadı."+email));
	}
}
