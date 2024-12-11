package pl.dodo.eLunchApp.service;

import pl.dodo.eLunchApp.dto.Discount.DiscountCodeDTOBasic;
import pl.dodo.eLunchApp.dto.Discount.DiscountCodeDTOExtended;
import pl.dodo.eLunchApp.exceptions.Result;

import java.util.List;
import java.util.UUID;

public interface DiscountCodeService {
    List<DiscountCodeDTOBasic> getAll();
    Result<Void> put(UUID uuid, DiscountCodeDTOExtended dtoExtended);
    Result<Void> delete(UUID uuid);
    Result<DiscountCodeDTOExtended> getByUuid(UUID uuid);
}
