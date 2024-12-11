package pl.dodo.eLunchApp.dto.LoginData;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Embeddable
@Data
public class LoginDataDTOBasic {

	@Size(min = 3)
	private String login;

	@JsonIgnore
	@Pattern(regexp = "(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{6,}$")
	private String password;
}
