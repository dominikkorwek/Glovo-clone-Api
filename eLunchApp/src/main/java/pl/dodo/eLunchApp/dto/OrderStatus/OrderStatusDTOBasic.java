package pl.dodo.eLunchApp.dto.OrderStatus;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import pl.dodo.eLunchApp.validator.GroupsValidator;

import javax.annotation.Nullable;

import java.time.Instant;

@Embeddable
@Data
public class OrderStatusDTOBasic {

	@NotNull
	private Instant orderTime;

	@NotNull
	private Boolean isPaid;

	@NotNull(groups = GroupsValidator.GaveOutStatusValid.class)
	@Nullable
	private Instant giveOutTime;

	@NotNull(groups = GroupsValidator.DeliveryValid.class)
	@Nullable
	private Instant deliveryTime;
}