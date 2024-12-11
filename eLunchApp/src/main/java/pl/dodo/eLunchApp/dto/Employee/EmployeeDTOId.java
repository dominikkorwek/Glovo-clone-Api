package pl.dodo.eLunchApp.dto.Employee;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class EmployeeDTOId {
//	public static class View {
//		public interface Id {}
//		public interface Basic extends Id {}
//		public interface Extended extends Basic {}
//	}
	@NotNull
	private UUID uuid;
}

