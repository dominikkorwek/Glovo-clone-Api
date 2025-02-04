package pl.dodo.eLunchApp.service;

import pl.dodo.eLunchApp.dto.Discount.DiscountCodeDTOBasic;
import pl.dodo.eLunchApp.dto.Discount.DiscountCodeDTOExtended;
import pl.dodo.eLunchApp.exceptions.eLunchError;
import pl.dodo.eLunchApp.model.DiscountCode;

import java.util.List;
import java.util.UUID;

public interface DiscountCodeService extends ValidationService<DiscountCode> {
    List<DiscountCodeDTOBasic> getAll();
    void add(DiscountCodeDTOExtended dtoExtended);
    void edit(UUID uuid, DiscountCodeDTOExtended dtoExtended) throws eLunchError.InvalidUuid, eLunchError.ObjectNotFound;
    void delete(UUID uuid) throws eLunchError.ObjectNotFound;
    DiscountCodeDTOExtended getByUuid(UUID uuid) throws eLunchError.ObjectNotFound;
}
