package tr.com.huseyinaydin.expensetrackerapi.entity;

import java.util.Date;

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
public class ErrorObject {
	private Integer statusCode;
	private String message;
	private Date timestamp;
}
