package pl.dodo.eLunchApp.dto.Employee;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Data;
import pl.dodo.eLunchApp.validator.GroupsValidator;

import java.util.UUID;

@Data
public class EmployeeDTOId {
//	public static class View {
//		public interface Id {}
//		public interface Basic extends Id {}
//		public interface Extended extends Basic {}
//	}
	@NotNull
	@Null(groups = GroupsValidator.NewObjectValid.class)
	private UUID uuid;
}

