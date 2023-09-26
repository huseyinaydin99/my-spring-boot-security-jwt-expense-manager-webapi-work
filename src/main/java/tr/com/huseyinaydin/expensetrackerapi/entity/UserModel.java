package tr.com.huseyinaydin.expensetrackerapi.entity;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot.
 *
 */

@Data
public class UserModel {
	
	@NotBlank(message = "Kullanıcı adı boş geçilemez")
	private String name;

	@NotNull(message = "Kullanıcı e-posta adresi boş geçilemez")
	@Email(message = "Kullanıcı e-posta adresi doğru formatta e-posta adresi olmalıdır")
	private String email;
	
	@NotNull(message = "Kullanıcı şifresi boş geçilemez")
	@Size(min = 5, message = "Kullanıcı şifresi 5 karakterden uzun olmalıdır")
	private String password;
	
	private Long age = 0L;
}
