package pl.dodo.eLunchApp.dto.OrderItem;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class OrderItemDTOBasic {
	@NotNull
	private UUID uuid;
}

