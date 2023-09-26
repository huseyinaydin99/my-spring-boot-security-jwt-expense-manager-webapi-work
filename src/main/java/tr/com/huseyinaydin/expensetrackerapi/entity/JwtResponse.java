package tr.com.huseyinaydin.expensetrackerapi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot.
 *
 */

@Getter
@AllArgsConstructor
public class JwtResponse {
	
	
	private final String jwtToken;
}
