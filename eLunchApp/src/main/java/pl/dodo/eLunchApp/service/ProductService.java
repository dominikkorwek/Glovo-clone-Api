package pl.dodo.eLunchApp.service;

import pl.dodo.eLunchApp.dto.Product.ProductDTOBasic;
import pl.dodo.eLunchApp.dto.Product.ProductDTOExtended;
import pl.dodo.eLunchApp.exceptions.Result;
import pl.dodo.eLunchApp.model.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService extends ValidationService<Product> {
    List<ProductDTOBasic> getAll();
    Result<Void> put(UUID uuid, ProductDTOExtended dtoExtended);
    Result<Void> delete(UUID uuid);
    Result<ProductDTOExtended> getByUuid(UUID uuid);
}
